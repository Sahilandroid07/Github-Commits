package com.example.githubcommits.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.githubcommits.data.GitApiService
import com.example.githubcommits.domain.response.GithubCommitsRemoteResponseItem
import com.example.githubcommits.domain.wrapper.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GithubApiRepositoryImpl @Inject constructor(
    private val service: GitApiService
) : GithubApiRepository {
    override suspend fun getCommits(
        owner: String?,
        repo: String?
    ): Flow<PagingData<GithubCommitsRemoteResponseItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                prefetchDistance = NETWORK_PAGE_SIZE,
                initialLoadSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = { GithubCommitsPagingSource(service, owner, repo) }
        ).flow.flowOn(Dispatchers.IO)
    }
}