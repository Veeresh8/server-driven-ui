//package com.droid.lokalplayground.feed
//
//import kotlinx.serialization.SerialName
//
//@kotlinx.serialization.Serializable
//data class PostResponse(
//    val count: Int? = null,
//    val next: String? = null,
//    val previous: String? = null,
//
//    @SerialName("results")
//    val posts: List<Post>? = null
//) {
//    @kotlinx.serialization.Serializable
//    data class Post(
//        @SerialName("id")
//        val postID: Int = -1,
//
//        @SerialName("title")
//        val title: String? = null,
//
//        @SerialName("content")
//        val content: String? = null,
//
//        @SerialName("images")
//        val images: List<PostImage>? = null
//    ) {
//        @kotlinx.serialization.Serializable
//        data class PostImage(
//            @SerialName("image")
//            val image: String? = null,
//
//            @SerialName("thumb_url")
//            val thumbnailURL: String? = null,
//        )
//    }
//}
//
//@kotlinx.serialization.Serializable
//data class AdResponse(
//    @SerialName("results")
//    val adsList: List<Advert>? = null
//) {
//    @kotlinx.serialization.Serializable
//    data class Advert(
//        @SerialName("id")
//        val id: Int = -1,
//
//        @SerialName("creatives")
//        val creatives: List<AdCreative>? = null,
//    ) {
//        @kotlinx.serialization.Serializable
//        data class AdCreative(
//            @SerialName("thumb_url")
//            val thumbnailURL: String? = null,
//        )
//    }
//}
//
//data class FeedResponse(
//    var posts: List<PostResponse.Post>? = null,
//    var advertList: List<AdResponse.Advert>? = null
//)