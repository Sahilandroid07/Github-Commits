package com.example.githubcommits.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.githubcommits.data.GitApiService
import com.example.githubcommits.domain.response.GithubCommitsRemoteResponseItem
import okio.IOException
import retrofit2.HttpException

private const val GITHUB_STARTING_PAGE_INDEX = 1
const val NETWORK_PAGE_SIZE = 30
class GithubCommitsPagingSource(
    private val service: GitApiService,
    private val owner: String?,
    private val repo: String?
) : PagingSource<Int, GithubCommitsRemoteResponseItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubCommitsRemoteResponseItem> {
        val position = params.key ?: GITHUB_STARTING_PAGE_INDEX
        return try {
            val response = service.getCommits(owner, repo, NETWORK_PAGE_SIZE.toString(), position.toString())
            if (response.isSuccessful) {
                response.body()?.let {
                    LoadResult.Page(
                        data = it,
                        prevKey = if (position == GITHUB_STARTING_PAGE_INDEX) null else position - 1,
                        nextKey = if (it.isNotEmpty()) position + (params.loadSize / NETWORK_PAGE_SIZE) else null
                    )
                } ?: LoadResult.Error(Exception("Data is null"))
            }else {
                LoadResult.Error(Exception("SomeError Occurred"))
            }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GithubCommitsRemoteResponseItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}