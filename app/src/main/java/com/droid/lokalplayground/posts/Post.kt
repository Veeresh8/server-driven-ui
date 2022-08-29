package com.droid.lokalplayground.posts

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Polymorphic
@Serializable
abstract class Post {
    abstract val lastUpdated: Long
    abstract val id: Long
    abstract val itemType: ItemType

}

@Serializable
@SerialName("1")
data class Article(
    override val lastUpdated: Long,
    override val id: Long,
    override val itemType: ItemType = ItemType.ARTICLE,

    val title: String? = null,
    val description: String? = null,
    val imageURL: String? = null,
    val likes: Long? = null,
    val shares: Long? = null,
) : Post()


@Serializable
@SerialName("2")
data class Video(
    override val lastUpdated: Long,
    override val id: Long,
    override val itemType: ItemType = ItemType.VIDEO,

    val videoURL: String? = null,
    val thumbnail: String? = null

): Post()


@Serializable
@SerialName("3")
data class Advert(
    override val lastUpdated: Long,
    override val id: Long,
    override val itemType: ItemType = ItemType.ADVERT,

    val adImage: String? = null,
    val redirectURL: String? = null

): Post()


@Serializable
@SerialName("4")
data class JobPost(
    override val lastUpdated: Long,
    override val id: Long,
    override val itemType: ItemType = ItemType.JOBPOST,

    val jobTitle: String? = null,
    val jobDescription: String? = null,
    val image: String? = null

): Post()


sealed class HomeState {
    data class Success(val posts: List<Post>): HomeState()
    data class Loading(val message: String): HomeState()
    data class Error(val exception: Throwable): HomeState()
}

enum class ItemType {
    ARTICLE, VIDEO, ADVERT, JOBPOST
}