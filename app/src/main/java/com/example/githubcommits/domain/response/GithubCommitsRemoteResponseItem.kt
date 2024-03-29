package com.example.githubcommits.domain.response

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

data class GithubCommitsRemoteResponseItem(
    val author: AuthorX,
    val comments_url: String,
    val commit: Commit,
    val committer: CommitterX,
    val html_url: String,
    val node_id: String,
    val parents: List<Parent>,
    val sha: String,
    val url: String
) {
    data class AuthorX(
        val avatar_url: String,
        val events_url: String,
        val followers_url: String,
        val following_url: String,
        val gists_url: String,
        val gravatar_id: String,
        val html_url: String,
        val id: Int,
        val login: String,
        val node_id: String,
        val organizations_url: String,
        val received_events_url: String,
        val repos_url: String,
        val site_admin: Boolean,
        val starred_url: String,
        val subscriptions_url: String,
        val type: String,
        val url: String
    )

    data class Author(
        val date: String,
        val email: String,
        val name: String
    )

    data class Commit(
        val author: Author,
        val comment_count: Int,
        val committer: Committer,
        val message: String,
        val tree: Tree,
        val url: String,
        val verification: Verification
    )

    data class Committer(
        val date: String,
        val email: String,
        val name: String
    )

    data class CommitterX(
        val avatar_url: String,
        val events_url: String,
        val followers_url: String,
        val following_url: String,
        val gists_url: String,
        val gravatar_id: String,
        val html_url: String,
        val id: Int,
        val login: String,
        val node_id: String,
        val organizations_url: String,
        val received_events_url: String,
        val repos_url: String,
        val site_admin: Boolean,
        val starred_url: String,
        val subscriptions_url: String,
        val type: String,
        val url: String
    )

    data class Parent(
        val sha: String,
        val url: String
    )

    data class Tree(
        val sha: String,
        val url: String
    )

    data class Verification(
        val payload: Any,
        val reason: String,
        val signature: Any,
        val verified: Boolean
    )

    @RequiresApi(Build.VERSION_CODES.O)
    fun formattedDate(): String {
        val zonedTime = ZonedDateTime.parse(commit.committer.date)
        return zonedTime.format(DateTimeFormatter.ofPattern("EEEE-dd-MMMM-u HH:mm"))
    }
}