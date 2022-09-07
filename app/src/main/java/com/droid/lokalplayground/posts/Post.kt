package com.droid.lokalplayground.posts

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Polymorphic
@Serializable
abstract class Post {
    abstract val id: Long
}

@Serializable
@SerialName("BANNER")
data class Banner(
    override val id: Long,

    @SerialName("banner_meta")
    val bannerMeta: BannerMeta? = null,

    @SerialName("banner_data")
    val bannerData: List<BannerData> = listOf()
) : Post() {
    @Serializable
    data class BannerMeta(val interval: Int = 4)

    @Serializable
    data class BannerData(
        @SerialName("image_url")
        val imageUrl: String? = null,
        val action: String? = null,
        @SerialName("is_ad")
        val isAdvert: Boolean = false
    )
}

sealed class HomeState {
    data class Success(val posts: List<Post>): HomeState()
    data class Loading(val message: String): HomeState()
    data class Error(val exception: Throwable): HomeState()
}