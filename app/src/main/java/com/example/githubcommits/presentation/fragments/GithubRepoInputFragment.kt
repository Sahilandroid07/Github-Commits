package com.example.githubcommits.presentation.fragments


import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.githubcommits.databinding.FragmentGithubRepoInputBinding
import com.example.githubcommits.presentation.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GithubRepoInputFragment : BaseFragment<FragmentGithubRepoInputBinding>(), OnClickListener {
    override fun getLayoutBinding(
        inflater: LayoutInflater,
        container: ViewGroup?): FragmentGithubRepoInputBinding {
        return FragmentGithubRepoInputBinding.inflate(inflater, container, false)
    }

    override fun setUp() {
        binding.listener = this
    }

    override fun onClick(view: View?) {
        when(view){
            binding.btnSubmit -> {
                val owner = binding.etOwner.text?.toString()
                val repo = binding.etRepo.text?.toString()
                if (owner.isNullOrEmpty() && repo.isNullOrEmpty()){
                    Snackbar.make(binding.root, "Owner and repo can not be empty", Snackbar.LENGTH_SHORT).show()
                }else{
                    findNavController().navigate(GithubRepoInputFragmentDirections.
                    actionGithubRepoInputFragmentToGithubCommitsFragment(owner = owner, repo = repo))
                }

            }
        }
    }


}