package com.droid.lokalplayground

import com.droid.lokalplayground.PayloadBuilder.buildAdvertPayload
import com.droid.lokalplayground.PayloadBuilder.buildFeedPayload
import kotlinx.coroutines.CoroutineDispatcher
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

    suspend fun fetchHomeFeed(): Flow<Result<FeedResponse>> {
        return flow<Result<FeedResponse>> {
            val feedResponse = FeedResponse()

            supervisorScope {
                val posts = async { fetchPosts() }
                val adverts = async { fetchAdverts() }

                runCatching {
                    feedResponse.posts = posts.await().posts
                }.onFailure {
                    println("Exception getting posts: $it")
                }

                runCatching {
                    feedResponse.advertList = adverts.await().adsList
                }.onFailure {
                    println("Exception getting adverts: $it")
                }

                emit(Result.Success(feedResponse))
            }
        }.catch {
            emit(Result.Error(it))
        }
    }

    private suspend fun fetchPosts(feedPayload: FeedPayload = buildFeedPayload()): PostResponse {
        return lokalAPIService.fetchPosts(
            cursor = feedPayload.cursor,
            location_id = feedPayload.locationID,
            category_id = feedPayload.categoryID,
            post_type = feedPayload.postType,
            tagId = feedPayload.tagID,
            category_nudge_pos = feedPayload.nudge,
            config = feedPayload.config,
            microlocation_id = feedPayload.microLocationID
        )
    }

    private suspend fun fetchAdverts(adPayload: AdPayload = buildAdvertPayload()): AdResponse {
        return lokalAdAPIService.fetchAds(
            placement_type = adPayload.placementType,
            category_id = adPayload.categoryID,
            location_id = adPayload.locationID,
            micro_location_id = adPayload.microLocationID
        )
    }
}