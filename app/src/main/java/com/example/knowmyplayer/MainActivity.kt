package com.example.knowmyplayer

import android.content.Context
import android.hardware.input.InputManager
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.knowmyplayer.databinding.ActivityMainBinding
import com.example.knowmyplayer.fragments.InfoFragment
import com.example.knowmyplayer.fragments.ProfileFragment
import com.example.knowmyplayer.utils.Utils
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentList = arrayListOf(
            ProfileFragment(),
            InfoFragment()
        )

        val adapter = ViewPagerAdapter(this, fragmentList)
        binding.viewpager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
            when (position) {
                0 -> tab.text = this.getString(R.string.profile)
                1 -> tab.text = this.getString(R.string.info)
            }
        }.attach()

        binding.searchViewSearchPlayer.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                lifecycleScope.launch {
                    query?.let { viewModel.getPlayerStatsByName(it) }
                    Utils.closeKeyboard(this@MainActivity)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        viewModel.playerStats.observe(this) {
            if (it != null) {
                binding.imageView.load(it.strFanart1)
                binding.imageViewAvatar.load(it.strCutout)
                binding.textViewPlayerName.text = it.strPlayer
                binding.textViewPlayerCountry.text = it.strNationality
                binding.textViewPlayerSport.text = it.strSport
            }
        }
    }
}