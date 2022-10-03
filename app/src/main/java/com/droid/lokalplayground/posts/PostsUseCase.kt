package com.droid.lokalplayground.posts

import android.content.Context
import com.droid.lokalplayground.Result
import com.droid.lokalplayground.di.Type
import com.droid.lokalplayground.feed.LokalAPIService
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class PostsUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    @Type.LokalAPIService val lokalAPIService: LokalAPIService,
    @Type.PostSerializer val json: Json
) {

    fun getPosts(): Result<List<LokalViewType>> {
        return try {
            val result = context.assets.open("response.json").bufferedReader()
                .use { it.readText() }.trimIndent().replace("\n", "")
            val posts: List<LokalViewType> = json.decodeFromString(result)
            Result.Success(posts)
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }

    suspend fun getPostsServer(): Result<List<Post>> {
        return try {
            val posts = lokalAPIService.fetchData("https://feeds.free.beeceptor.com/posts")
            Result.Success(posts)
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }
}
