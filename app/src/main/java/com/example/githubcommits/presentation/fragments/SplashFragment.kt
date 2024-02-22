package com.example.githubcommits.presentation.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.githubcommits.databinding.FragmentSplashBinding
import com.example.githubcommits.presentation.base.BaseFragment


class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    override fun getLayoutBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater, container, false)
    }

    override fun setUp() {
    }

}