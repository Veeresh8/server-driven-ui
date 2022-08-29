package com.droid.lokalplayground.feed

object PayloadBuilder {

    fun buildFeedPayload(): FeedPayload {
        return FeedPayload(
            cursor = "",
            locationID = "626",
            categoryID = "1",
            postType = "all",
            tagID = "",
            nudge = 1,
            microLocationID = "-1",
            config = """{"article_page":1,"video_page":1,"trending_page":0}"""
        )
    }

    fun buildAdvertPayload(): AdPayload {
        return AdPayload(
            placementType = 2,
            categoryID = "1",
            locationID = "626",
            microLocationID = "-1"
        )
    }
}