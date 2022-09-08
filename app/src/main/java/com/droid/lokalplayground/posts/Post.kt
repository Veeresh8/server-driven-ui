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
    data class BannerMeta(val interval: Long = 4) {

        fun getBannerDelay(): Long {
            return interval * 1000
        }
    }

    @Serializable
    data class BannerData(
        @SerialName("id")
        val id: Long,

        @SerialName("image_url")
        val imageUrl: String? = null,
        val action: String? = null,
        @SerialName("is_ad")
        val isAdvert: Boolean = false
    )
}

@Serializable
@SerialName("QUICK_ACCESS")
data class QuickAccess(
    override val id: Long,

    @SerialName("quick_access_meta")
    val quickAccessMeta: QuickAccessMeta? = null,
    @SerialName("quick_access_data")
    val quickAccessData: List<QuickAccessData> = listOf()

) : Post() {

    @Serializable
    data class QuickAccessMeta(
        @SerialName("title")
        val title: String? = null
    )

    @Serializable
    data class QuickAccessData(
        @SerialName("id")
        val id: Long,

        @SerialName("image_url")
        val imageUrl: String? = null,
        @SerialName("title")
        val title: String? = null,
        @SerialName("action")
        val action: String? = null
    )
}

@Serializable
@SerialName("CAROUSEL")
data class Carousel(
    override val id: Long,

    @SerialName("carousel_meta")
    val carouselMeta: CarouselMeta? = null,

    @SerialName("carousel_data")
    val carouselData: List<CarouselData> = listOf()

) : Post() {

    @Serializable
    data class CarouselMeta(
        @SerialName("title")
        val title: String? = null,
        @SerialName("icon")
        val icon: String? = null,
        @SerialName("sub_title")
        val subTitle: String? = null
    )

    @Serializable
    data class CarouselData(
        @SerialName("id")
        val id: Long,

        @SerialName("image_url")
        val imageUrl: String? = null,
        @SerialName("content")
        val content: String? = null,
        @SerialName("action")
        val action: String? = null
    )
}

@Serializable
@SerialName("QUICK_NOTIFICATION")
data class QuickNotification(
    override val id: Long,

    @SerialName("quick_notification_meta")
    val quickNotificationMeta: QuickNotificationMeta? = null
) : Post() {

    @Serializable
    data class QuickNotificationMeta(
        @SerialName("icon")
        val icon: String? = null,
        @SerialName("title")
        val title: String? = null,
        @SerialName("button_text")
        val buttonText: String? = null,
        @SerialName("sub_title")
        val subTitle: String? = null,
        @SerialName("action")
        val action: String? = null
    )
}

sealed class HomeState {
    data class Success(val posts: List<Post>) : HomeState()
    data class Loading(val message: String) : HomeState()
    data class Error(val exception: Throwable) : HomeState()
}