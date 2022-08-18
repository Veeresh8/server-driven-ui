package com.droid.lokalplayground

object PayloadBuilder {

    fun buildFeedPayload(): FeedPayload {
        return FeedPayload(
            cursor = "",
            locationID = "9",
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
            locationID = "9",
            microLocationID = "-1"
        )
    }
}