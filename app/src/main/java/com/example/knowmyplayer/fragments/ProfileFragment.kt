package com.example.knowmyplayer.fragments

import android.os.Build
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import com.example.knowmyplayer.MainActivityViewModel
import com.example.knowmyplayer.R
import com.example.knowmyplayer.databinding.FragmentProfileBinding
import com.example.knowmyplayer.utils.convertDOBToAge


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel: MainActivityViewModel by activityViewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)
        viewModel.playerStats.observe(viewLifecycleOwner) {

            it?.let { it1 ->
                binding.labelAndFieldPosition.setValue(it1.strPosition)
                binding.labelAndFieldHeight.setValue(it1.strHeight)
                binding.labelAndFieldWeight.setValue(it1.strWeight)
                binding.labelAndFieldAge.setValue(it1.dateBorn.convertDOBToAge())
                binding.labelAndFieldTeam.setValue(it1.strTeam)
                binding.labelAndFieldNumber.setValue(it1.strNumber)
                binding.labelAndFieldAgent.setValue(it1.strAgent)
                binding.labelAndFieldWage.setValue(it1.strWage)
                binding.labelAndFieldKit.setValue(it1.strKit)
            }

        }

    }


}