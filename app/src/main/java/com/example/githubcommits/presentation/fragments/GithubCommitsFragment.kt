package com.example.githubcommits.presentation.fragments

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.githubcommits.databinding.FragmentGithubCommitsBinding
import com.example.githubcommits.domain.utils.FILTER_DATA_KEY
import com.example.githubcommits.domain.utils.FilterData
import com.example.githubcommits.domain.utils.ISO_8601_DATE_FORMAT
import com.example.githubcommits.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

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
        setFragmentResultForFilter()
        binding.recyclerview.adapter = githubCommitsAdapter
        viewModel.getCommits(args.owner, args.repo, author = null, committer = null, since = null, until = null)
    }

    private fun setFragmentResultForFilter() {
        setFragmentResultListener(FILTER_DATA_KEY) { _, bundle ->
            val filterData = if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable(FILTER_DATA_KEY)
            } else {
                bundle.getParcelable(FILTER_DATA_KEY, FilterData::class.java)
            }
            val formatted = SimpleDateFormat(ISO_8601_DATE_FORMAT, Locale.getDefault())
            val fromDate = if (filterData?.fromDate != null) formatted.format(filterData.fromDate) else null
            val toDate =  if (filterData?.toDate != null) formatted.format(filterData.toDate) else null
            viewModel.getCommits(args.owner, args.repo, filterData?.author, filterData?.email, fromDate, toDate)
        }
    }
    private fun onClickListener() {
        binding.setListener {
            when(it) {
                binding.ivBack -> {
                    findNavController().popBackStack()
                }
                binding.filter -> {
                    findNavController().navigate(GithubCommitsFragmentDirections.actionGithubCommitsFragmentToFilterBottomSheetFragment())
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