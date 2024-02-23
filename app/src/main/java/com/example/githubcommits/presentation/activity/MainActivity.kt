package com.example.githubcommits.presentation.activity

import android.view.LayoutInflater
import com.example.githubcommits.databinding.ActivityMainBinding
import com.example.githubcommits.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutBinding(layoutInflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setUp() {

    }

}