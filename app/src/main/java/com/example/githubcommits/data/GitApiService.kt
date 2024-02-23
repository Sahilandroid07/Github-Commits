package com.example.githubcommits.data


import com.example.githubcommits.domain.response.GithubCommitsRemoteResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitApiService {
    @GET("/repos/{owner}/{repo}/commits")
    suspend fun getCommits(
        @Path("owner") owner: String?,
        @Path("repo") repo: String?,
        @Query("per_page") perPage: String?,
        @Query("page") page: String?
    ): Response<List<GithubCommitsRemoteResponseItem>>
}