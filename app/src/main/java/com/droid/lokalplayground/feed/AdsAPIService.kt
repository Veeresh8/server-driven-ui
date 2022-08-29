package com.droid.lokalplayground.feed

import retrofit2.http.GET
import retrofit2.http.Query

interface AdsAPIService {

    @GET("ads/ads/")
    suspend fun fetchAds(
        @Query("placement_type") placement_type: Int = -1,
        @Query("category_id") category_id: String = "",
        @Query("location_id") location_id: String = "",
        @Query("micro_location_id") micro_location_id: String = ""
    ): Unit
}