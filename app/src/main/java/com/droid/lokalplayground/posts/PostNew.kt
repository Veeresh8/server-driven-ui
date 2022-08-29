//package com.droid.lokalplayground.posts
//
//import kotlinx.serialization.ExperimentalSerializationApi
//import kotlinx.serialization.Polymorphic
//import kotlinx.serialization.SerialName
//import kotlinx.serialization.Serializable
//import kotlinx.serialization.json.JsonClassDiscriminator
//
//@Polymorphic
//@Serializable
//abstract class PostResponse {
//    abstract val lastUpdated: Long
//    abstract val id: Long
//}
//
//@Serializable
//@SerialName("article")
//data class ArticleResponse(
//    override val lastUpdated: Long,
//    override val id: Long,
//
//    val title: String? = null,
//    val description: String? = null,
//    val imageURL: String? = null,
//    val likes: Long? = null,
//    val shares: Long? = null,
//) : PostResponse()
//
//@Serializable
//@SerialName("video")
//data class VideoResponse(
//    override val lastUpdated: Long,
//    override val id: Long,
//
//    val title: String? = null,
//    val videoURL: String? = null,
//) : PostResponse()
//
//@Serializable
//@SerialName("advertisement")
//data class AdvertisementResponse(
//    override val lastUpdated: Long,
//    override val id: Long,
//
//    val adImage: String? = null,
//    val redirectURL: String? = null,
//) : PostResponse()