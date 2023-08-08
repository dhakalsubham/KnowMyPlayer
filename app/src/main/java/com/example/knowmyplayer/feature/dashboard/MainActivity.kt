package com.example.knowmyplayer.feature.dashboard

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.knowmyplayer.R
import com.example.knowmyplayer.databinding.ActivityMainBinding
import com.example.knowmyplayer.fragments.InfoFragment
import com.example.knowmyplayer.fragments.ProfileFragment
import com.example.knowmyplayer.remote.NetworkUtils
import com.example.knowmyplayer.utils.Utils
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAnalytics = Firebase.analytics
        setUpUI()
        setUpObserver()
    }

    private fun setUpUI() {
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
                    firebaseAnalytics.logEvent("player_name_entered") {
                        param("player_name", query.toString())
                    }
                    Utils.closeKeyboard(this@MainActivity)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun setUpObserver() {
        viewModel.playerStats.observe(this) {
            when (it) {
                is NetworkUtils.Error -> {
                    binding.relativeLayout.visibility = View.GONE
                    binding.textViewEmpty.visibility = View.VISIBLE
                    binding.viewpager.visibility = View.GONE
                    binding.tabLayout.visibility = View.GONE

                    firebaseAnalytics.logEvent("player_name_response") {
                        param("successful", "false")
                    }
                }

                is NetworkUtils.Loading -> {
                    binding.relativeLayout.visibility = View.GONE
                    binding.textViewEmpty.visibility = View.VISIBLE
                    binding.textViewEmpty.text = this.getString(R.string.loading_player_information)
                    binding.viewpager.visibility = View.GONE
                    binding.tabLayout.visibility = View.GONE
                }

                is NetworkUtils.Success -> {
                    binding.relativeLayout.visibility = View.VISIBLE
                    binding.textViewEmpty.visibility = View.GONE
                    binding.viewpager.visibility = View.VISIBLE
                    binding.tabLayout.visibility = View.VISIBLE
                    it.data?.let { playerResponse ->
                        if (playerResponse.player.isNullOrEmpty()) {
                            binding.relativeLayout.visibility = View.GONE
                            binding.textViewEmpty.visibility = View.VISIBLE
                            binding.textViewEmpty.text = this.getString(R.string.invalid_player_name)
                            binding.viewpager.visibility = View.GONE
                            binding.tabLayout.visibility = View.GONE
                            firebaseAnalytics.logEvent("player_name_response") {
                                param("successful", "false")
                            }
                        } else {
                            with(playerResponse.player[0]) {
                                binding.imageView.load(it.data?.player?.get(0)?.strFanart1)
                                binding.imageViewAvatar.load(strCutout)
                                binding.textViewPlayerName.text = strPlayer
                                binding.textViewPlayerCountry.text = strNationality
                                binding.textViewPlayerSport.text = strSport
                                firebaseAnalytics.logEvent("player_name_response") {
                                    param("successful", "true")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
