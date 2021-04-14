package com.example.githubuser.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.githubuser.R
import com.example.githubuser.databinding.FragmentLandingScreenBinding


class LandingScreen : Fragment() {
    private lateinit var binding: FragmentLandingScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLandingScreenBinding.inflate(inflater, container, false)
        binding.btnSignIn.setOnClickListener {
           findNavController().navigate(R.id.action_landingScreen_to_loginScreen)
        }

        return binding.root
    }


}