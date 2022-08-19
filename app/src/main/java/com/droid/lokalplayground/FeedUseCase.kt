package com.droid.lokalplayground

import com.droid.lokalplayground.PayloadBuilder.buildAdvertPayload
import com.droid.lokalplayground.PayloadBuilder.buildFeedPayload
import com.droid.lokalplayground.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

class FeedUseCase @Inject constructor(
    @Type.LokalAPIService val lokalAPIService: LokalAPIService,
    @Type.LokalAdsAPIService val lokalAdAPIService: AdsAPIService
) {
    suspend fun fetchPosts(feedPayload: FeedPayload = buildFeedPayload()): Result<PostResponse> {
        return try {
            val result = lokalAPIService.fetchPosts(
                cursor = feedPayload.cursor,
                location_id = feedPayload.locationID,
                category_id = feedPayload.categoryID,
                post_type = feedPayload.postType,
                tagId = feedPayload.tagID,
                category_nudge_pos = feedPayload.nudge,
                config = feedPayload.config,
                microlocation_id = feedPayload.microLocationID
            )
            Result.Success(result)
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }

    suspend fun fetchAdverts(adPayload: AdPayload = buildAdvertPayload()): Result<AdResponse> {
        return try {
            val result = lokalAdAPIService.fetchAds(
                placement_type = adPayload.placementType,
                category_id = adPayload.categoryID,
                location_id = adPayload.locationID,
                micro_location_id = adPayload.microLocationID
            )
            Result.Success(result)
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }
}