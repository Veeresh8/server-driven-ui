package com.droid.lokalplayground

data class FeedPayload(
    val cursor: String,
    val locationID: String,
    val categoryID: String,
    val postType: String,
    val tagID: String,
    val nudge: Int,
    val config: String,
    val microLocationID: String
)

data class AdPayload(
    val placementType: Int,
    val locationID: String,
    val categoryID: String,
    val microLocationID: String
)
