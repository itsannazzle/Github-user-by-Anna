package com.example.githubuser.welcome

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.githubuser.MainActivity
import com.example.githubuser.databinding.FragmentLoginScreenBinding


class LoginScreen : Fragment() {
    private lateinit var binding: FragmentLoginScreenBinding
    companion object{
        const val EXTRA_USERNAME ="itsannazzle"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginScreenBinding.inflate(inflater, container, false)
        binding.btnSignIn.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            intent.putExtra(LoginScreen.EXTRA_USERNAME,binding.username.text.toString())
            startActivity(intent)
        }
        return binding.root
    }

}