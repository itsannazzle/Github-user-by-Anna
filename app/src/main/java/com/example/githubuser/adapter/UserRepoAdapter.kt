package com.example.githubuser.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuser.R
import com.example.githubuser.databinding.UserRepositoriesBinding
import com.example.githubuser.model.UserRepository

class UserRepoAdapter : RecyclerView.Adapter<UserRepoAdapter.URepoViewModel>() {
    private val userRepository = ArrayList<UserRepository>()
    fun setUserData(repo: ArrayList<UserRepository>){
        userRepository.clear()
        userRepository.addAll(repo)
        notifyDataSetChanged()
    }
    inner class URepoViewModel(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = UserRepositoriesBinding.bind(itemView)
        fun bind(repo: UserRepository){
            binding.userRepo.text =repo.name
            binding.userRepodesc.text = repo.description
            binding.userRepoLang.text = repo.language
            binding.userRepoStars.text = repo.stars.toString()
            binding.userRepoBranch.text = repo.forks.toString()

            when{
                repo.language == "Java" -> {
                    binding.userRepoLang.
                    setCompoundDrawablesWithIntrinsicBounds(
                            ContextCompat.
                            getDrawable(
                                    itemView.context, R.drawable.circle_java), null,null,null)
                }
                repo.language == "Kotlin" -> {
                    binding.userRepoLang.
                    setCompoundDrawablesWithIntrinsicBounds(
                            ContextCompat.
                            getDrawable(
                                    itemView.context, R.drawable.circle_kotlin), null,null,null)
                }

                else -> {
                    binding.userRepoLang.
                    setCompoundDrawablesWithIntrinsicBounds(
                            ContextCompat.
                            getDrawable(
                                    itemView.context, R.drawable.circle_html), null,null,null)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): URepoViewModel {
       return URepoViewModel(LayoutInflater.from(parent.context).inflate(R.layout.user_repositories,parent,false))
    }

    override fun onBindViewHolder(holder: URepoViewModel, position: Int) {
        holder.bind(userRepository[position])
    }

    override fun getItemCount(): Int {
        return userRepository.size
    }
}