package com.example.githubcommits.presentation.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.githubcommits.databinding.FragmentGithubCommitsBinding
import com.example.githubcommits.presentation.base.BaseFragment

class GithubCommitsFragment : BaseFragment<FragmentGithubCommitsBinding>() {
    override fun getLayoutBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentGithubCommitsBinding {
        return FragmentGithubCommitsBinding.inflate(inflater, container, false)
    }

    override fun setUp() {
    }

}