package com.example.githubuser.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuser.R
import com.example.githubuser.databinding.SearchResultBinding
import com.example.githubuser.model.User
import com.example.githubuser.ui.explore.DetailActivity

class SearchResultAdapter : RecyclerView.Adapter<SearchResultAdapter.ResultViewHolder>() {
    private val user= ArrayList<User>()
    fun addUser(items: ArrayList<User>) {
        user.clear()
        user.addAll(items)
        notifyDataSetChanged()
    }
    inner class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = SearchResultBinding.bind(itemView)
        fun bind(user: User){
            binding.username.text = user.username
            Glide.with(itemView.context)
                    .load(user.avatarUrl)
                    .into(binding.image)
            binding.follow.setOnClickListener {
                Toast.makeText(itemView.context,"Soon to be implemented",Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        return ResultViewHolder(
                LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.search_result,parent,false))
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.bind(user[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA,user[position])
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return user.size
    }
}