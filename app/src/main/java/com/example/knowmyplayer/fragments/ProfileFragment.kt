package com.example.knowmyplayer.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import com.example.knowmyplayer.feature.dashboard.MainActivityViewModel
import com.example.knowmyplayer.R
import com.example.knowmyplayer.databinding.FragmentProfileBinding
import com.example.knowmyplayer.remote.NetworkUtils
import com.example.knowmyplayer.utils.convertDOBToAge
import com.example.knowmyplayer.utils.extractValueInsideParentheses
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel: MainActivityViewModel by activityViewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)
        MobileAds.initialize(requireContext())
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
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
                                binding.labelAndFieldPosition.setValue(strPosition)
                                binding.labelAndFieldHeight.setValue(strHeight.extractValueInsideParentheses())
                                binding.labelAndFieldWeight.setValue(strWeight)
                                binding.labelAndFieldAge.setValue(dateBorn.convertDOBToAge())
                                binding.labelAndFieldTeam.setValue(strTeam)
                                binding.labelAndFieldNumber.setValue(strNumber)
                                binding.labelAndFieldAgent.setValue(strAgent)
                                binding.labelAndFieldWage.setValue(strWage)
                                binding.labelAndFieldKit.setValue(strKit)
                            }
                        }

                    }

                }
            }
        }
    }
}