package com.droid.lokalplayground

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class PostResponse(
    val count: Int?,
    val next: String?,
    val previous: String?,

    @SerialName("results")
    val posts: List<Post>?
) {
    @kotlinx.serialization.Serializable
    data class Post(
        @SerialName("id")
        val postID: Int = -1,

        @SerialName("title")
        val title: String?,

        @SerialName("content")
        val content: String?,

        @SerialName("images")
        val images: List<PostImage>?
    ) {
        @kotlinx.serialization.Serializable
        data class PostImage(
            @SerialName("image")
            val image: String?,

            @SerialName("thumb_url")
            val thumbnailURL: String?,
        )
    }
}

@kotlinx.serialization.Serializable
data class AdResponse(
    @SerialName("results")
    val adsList: List<Advert>?
) {
    @kotlinx.serialization.Serializable
    data class Advert(
        @SerialName("id")
        val id: Int = -1,

        @SerialName("creatives")
        val creatives: List<AdCreative>?,
    ) {
        @kotlinx.serialization.Serializable
        data class AdCreative(
            @SerialName("thumb_url")
            val thumbnailURL: String?,
        )
    }
}

data class FeedResponse(
    var posts: List<PostResponse.Post>? = null,
    var advertList: List<AdResponse.Advert>? = null
)