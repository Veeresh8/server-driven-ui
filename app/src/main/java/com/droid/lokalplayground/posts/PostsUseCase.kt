package com.droid.lokalplayground.posts

import android.content.Context
import com.droid.lokalplayground.Result
import com.droid.lokalplayground.di.Type
import com.droid.lokalplayground.feed.LokalAPIService
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import maestro.Maestro
import maestro_android.MaestroAndroid
import javax.inject.Inject

class PostsUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    @Type.LokalAPIService val lokalAPIService: LokalAPIService,
) {

    suspend fun getPosts(): Result<List<Post>> {
        return try {
            val postList = lokalAPIService.fetchData("https://feeds.free.beeceptor.com/posts")
            Result.Success(postList)
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }
}
