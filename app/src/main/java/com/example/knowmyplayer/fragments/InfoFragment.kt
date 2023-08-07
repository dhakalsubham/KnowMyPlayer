package com.example.knowmyplayer.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.knowmyplayer.R
import com.example.knowmyplayer.databinding.FragmentInfoBinding
import com.example.knowmyplayer.feature.dashboard.MainActivityViewModel
import com.example.knowmyplayer.remote.NetworkUtils

class InfoFragment : Fragment(R.layout.fragment_info) {

    private lateinit var binding: FragmentInfoBinding
    private val viewModel: MainActivityViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInfoBinding.bind(view)

        viewModel.playerStats.observe(viewLifecycleOwner) {

            when (it) {
                is NetworkUtils.Error -> {
                }

                is NetworkUtils.Loading -> {
                }

                is NetworkUtils.Success -> {
                    it.data?.let { playerResponse ->
                        if (playerResponse.player.isNullOrEmpty()) {
                        } else {
                            with(playerResponse.player[0]) {
                                binding.textViewPlayerInfo.text = strDescriptionEN
                            }
                        }

                    }

                }

            }
        }
    }
}
