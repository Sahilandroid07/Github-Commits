package com.example.githubcommits.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T: ViewBinding>: AppCompatActivity() {

    protected lateinit var binding: T
    protected abstract fun getLayoutBinding(layoutInflater: LayoutInflater): T

    protected abstract fun setUp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getLayoutBinding(layoutInflater)
        setContentView(binding.root)
        setUp()
    }
}