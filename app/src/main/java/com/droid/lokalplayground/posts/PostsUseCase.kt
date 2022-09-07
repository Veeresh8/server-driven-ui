package com.droid.lokalplayground.posts

import android.content.Context
import com.droid.lokalplayground.Result
import com.droid.lokalplayground.di.Type
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import maestro.Maestro
import maestro_android.MaestroAndroid
import javax.inject.Inject

class PostsUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    @Type.PostSerializer val json: Json
) {

    fun getPosts(): Result<List<Post>> {
        return try {
            val result = context.assets.open("response.json").bufferedReader()
                .use { it.readText() }.trimIndent().replace("\n", "")
            val posts: List<Post> = json.decodeFromString(result)
            Result.Success(posts)
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }
}
