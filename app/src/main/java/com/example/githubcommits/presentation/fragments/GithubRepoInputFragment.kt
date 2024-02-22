package com.example.githubcommits.presentation.fragments


import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.githubcommits.databinding.FragmentGithubRepoInputBinding
import com.example.githubcommits.presentation.base.BaseFragment

class GithubRepoInputFragment : BaseFragment<FragmentGithubRepoInputBinding>() {
    override fun getLayoutBinding(
        inflater: LayoutInflater,
        container: ViewGroup?): FragmentGithubRepoInputBinding {
        return FragmentGithubRepoInputBinding.inflate(inflater, container, false)
    }

    override fun setUp() {

    }


}