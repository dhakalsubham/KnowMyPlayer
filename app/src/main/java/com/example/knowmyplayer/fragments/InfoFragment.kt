package com.example.knowmyplayer.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.knowmyplayer.MainActivityViewModel
import com.example.knowmyplayer.R
import com.example.knowmyplayer.databinding.FragmentInfoBinding

class InfoFragment : Fragment(R.layout.fragment_info) {

    private lateinit var binding: FragmentInfoBinding
    private val viewModel:MainActivityViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInfoBinding.bind(view)

        viewModel.playerStats.observe(viewLifecycleOwner){
            if (it != null) {
                binding.textViewPlayerInfo.text = it.strDescriptionEN
            }
        }
    }


}