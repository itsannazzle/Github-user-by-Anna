package com.example.githubuser.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.githubuser.R
import com.example.githubuser.databinding.FragmentSplashScreenBinding


class SplashScreen : Fragment() {
    private lateinit var binding: FragmentSplashScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        binding.splash.alpha = 0f
        binding.splash.animate().setDuration(1500).alpha(1f).withEndAction {
            findNavController().navigate(R.id.action_splashScreen_to_landingScreen)
        }

        return binding.root
        }

}