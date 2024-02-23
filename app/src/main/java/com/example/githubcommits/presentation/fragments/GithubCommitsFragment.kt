package com.example.githubcommits.presentation.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.githubcommits.databinding.FragmentGithubCommitsBinding
import com.example.githubcommits.domain.response.GithubCommitsRemoteResponseItem
import com.example.githubcommits.domain.wrapper.DataState
import com.example.githubcommits.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GithubCommitsFragment : BaseFragment<FragmentGithubCommitsBinding>() {

    private val args by navArgs<GithubCommitsFragmentArgs>()
    private val viewModel by viewModels<GithubCommitsViewModel>()
    private val githubCommitsAdapter by lazy { GithubCommitsAdapter() }
    override fun getLayoutBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentGithubCommitsBinding {
        return FragmentGithubCommitsBinding.inflate(inflater, container, false)
    }

    override fun setUp() {
        observeLiveData()
        onClickListener()
        binding.recyclerview.adapter = githubCommitsAdapter
        viewModel.getCommits(args.owner, args.repo)

    }

    private fun onClickListener() {
        binding.setListener {
            when(it) {
                binding.ivBack -> {
                    findNavController().popBackStack()
                }
                binding.filter -> {

                }
            }
        }
    }

    private fun observeLiveData() {
        viewModel.commitsLiveData.observe(viewLifecycleOwner) { pagingData ->
            lifecycleScope.launch {
                githubCommitsAdapter.submitData(pagingData)
            }
        }
    }
}