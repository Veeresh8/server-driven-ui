package com.droid.lokalplayground.feed

import com.droid.lokalplayground.posts.Post
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface LokalAPIService {

    @GET("posts/")
    suspend fun fetchPosts(
        @Query("cursor") cursor: String = "",
        @Query("location_id") location_id: String = "",
        @Query("category_id") category_id: String = "",
        @Query("post_type") post_type: String = "",
        @Query("tag_id") tagId: String = "",
        @Query("classifieds_nudge") category_nudge_pos: Int = -1,
        @Query("config") config: String = "",
        @Query("microlocation_id") microlocation_id: String = ""
    ): Unit

    @GET
    suspend fun fetchData(
      @Url url: String
    ): List<Post>
}