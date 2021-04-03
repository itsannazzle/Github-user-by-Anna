package com.example.githubuser.ui.explore

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.githubuser.R
import com.example.githubuser.databinding.ActivityDetailBinding
import com.example.githubuser.model.User
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val exploreViewModel: ExploreViewModel by viewModels()
    companion object {
        const val EXTRA_DATA = "extra_data"
        @StringRes
        private var TAB_TITLES = intArrayOf(
                R.string.title_repo,
                R.string.followers,
                R.string.following,
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionAdapter = SectionAdapter(this)
        showDetail()
        binding.viewpager2.adapter = sectionAdapter
        TabLayoutMediator(binding.tablayout, binding.viewpager2) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    private fun showDetail() {
        val selectedUser = intent.getParcelableExtra<User>(EXTRA_DATA)
        if (selectedUser != null){
            exploreViewModel.detailUser(selectedUser.username)
            exploreViewModel.showDetailUser.observe(this, {

                binding.detailUsername.text = it.username
                if (it.name.isNullOrEmpty()){
                    binding.detailName.text = StringBuilder("-")
                } else {
                    binding.detailName.text = it.name
                }
                if (it.bio.isNullOrEmpty()){
                    binding.detailBio.text = StringBuilder("-")
                } else{
                    binding.detailBio.text = it.bio
                }
                if (it.company.isNullOrEmpty()){
                    binding.detailOffice.text = StringBuilder("-")
                } else{
                    binding.detailOffice.text = it.company
                }
                if (it.location.isNullOrEmpty()){
                    binding.detailLocation.text = StringBuilder("-")
                } else{
                    binding.detailLocation.text = it.location
                }
                if (it.blog.isNullOrEmpty()){
                    binding.detailBlog.text = StringBuilder("-")
                } else{
                    binding.detailBlog.text = it.blog
                }
                if(it.avatar.isNullOrEmpty()){
                    Glide.with(applicationContext)
                            .load(R.drawable.ic_dashboard)
                            .into(binding.detailImage)
            } else {
                    Glide.with(applicationContext)
                            .load(it.avatar)
                            .into(binding.detailImage)
            }

            })

        }
    }


}