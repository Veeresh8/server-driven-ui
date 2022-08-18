package com.droid.lokalplayground

import retrofit2.http.GET
import retrofit2.http.Query

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
    ): PostResponse
}