package com.example.githubuser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuser.databinding.SearchResultBinding
import com.example.githubuser.model.User

class SearchResultAdapter : RecyclerView.Adapter<SearchResultAdapter.ResultViewHolder>() {
    private val user= ArrayList<User>()
    fun addUser(items: ArrayList<User>) {
        user.clear()
        user.addAll(items)
        notifyDataSetChanged()
    }

    private var onItemCallback : OnItemCallback? = null
    fun setOnItemCallback(onItemCallback: OnItemCallback){
        this.onItemCallback = onItemCallback
    }
    inner class ResultViewHolder(private val binding : SearchResultBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(user: User) {
            itemView.setOnClickListener {
                onItemCallback?.onItemClicked(user)
            }
            binding.username.text = user.username
            Glide.with(itemView.context)
                    .load(user.avatarUrl)
                    .into(binding.image)
            binding.follow.setOnClickListener {
                Toast.makeText(itemView.context, "Soon to be implemented", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        return ResultViewHolder(SearchResultBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.bind(user[position])

    }

    override fun getItemCount(): Int {
        return user.size
    }

    interface OnItemCallback{
        fun onItemClicked(user: User)
    }
}