package com.example.knowmyplayer

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.knowmyplayer.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textview.text =  "jnasjdnj"
        binding.imageView.load("https://www.thesportsdb.com/images/media/player/fanart/ssvqrs1424615496.jpg")
        GlobalScope.launch {
            var respose = viewModel.getPlayerStatsByName("Harry Kane").toString()
            Log.d("Player Result", respose)
        }
    }
}