package com.example.githubcommits.presentation.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.githubcommits.databinding.ViewHolderGithubCommitsBinding
import com.example.githubcommits.domain.response.GithubCommitsRemoteResponseItem


class GithubCommitsAdapter : PagingDataAdapter<GithubCommitsRemoteResponseItem, GithubCommitsAdapter.GithubCommitsViewHolder>(COMPARATOR) {
    companion object{
        val COMPARATOR = object: DiffUtil.ItemCallback<GithubCommitsRemoteResponseItem>() {
            override fun areItemsTheSame(
                oldItem: GithubCommitsRemoteResponseItem,
                newItem: GithubCommitsRemoteResponseItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: GithubCommitsRemoteResponseItem,
                newItem: GithubCommitsRemoteResponseItem
            ): Boolean {
                return oldItem.author == newItem.author
            }

        }
    }
    inner class GithubCommitsViewHolder(
        private val binding: ViewHolderGithubCommitsBinding
    ): ViewHolder(binding.root){

        fun bind(model: GithubCommitsRemoteResponseItem?){
            binding.data = model
            binding.setListener {
                when(it){
                    binding.ivDrop -> {
                        val angle = if (binding.isExpanded) 0f else 180f
                        binding.isExpanded = !binding.isExpanded
                        binding.ivDrop.animate().rotation(angle).setDuration(300).start()
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubCommitsViewHolder {
        return GithubCommitsViewHolder(ViewHolderGithubCommitsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: GithubCommitsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}