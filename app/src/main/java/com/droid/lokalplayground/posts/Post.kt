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

    @SerialName("banner_meta") val bannerMeta: BannerMeta? = null,

    @SerialName("banner_data") val bannerData: List<BannerData> = listOf()
) : Post() {
    @Serializable
    data class BannerMeta(
        val interval: Long = 4,
        @SerialName("enable_dots") val enableDots: Boolean = true,
    ) {

        fun getBannerDelay(): Long {
            return interval * 1000
        }
    }

    @Serializable
    data class BannerData(
        @SerialName("id") val id: Long,

        @SerialName("image_url") val imageUrl: String? = null,
        val action: String? = null,
        @SerialName("is_ad") val isAdvert: Boolean = false
    )
}

@Serializable
@SerialName("QUICK_ACCESS")
data class QuickAccess(
    override val id: Long,

    @SerialName("quick_access_meta") val quickAccessMeta: QuickAccessMeta? = null,
    @SerialName("quick_access_data") val quickAccessData: List<QuickAccessData> = listOf()

) : Post() {

    @Serializable
    data class QuickAccessMeta(
        @SerialName("title") val title: String? = null,
        @SerialName("justify") val layoutJustify: String? = null
    )

    @Serializable
    data class QuickAccessData(
        @SerialName("id") val id: Long,

        @SerialName("image_url") val imageUrl: String? = null,
        @SerialName("title") val title: String? = null,
        @SerialName("action") val action: String? = null
    )
}

@Serializable
@SerialName("CAROUSEL")
data class Carousel(
    override val id: Long,

    @SerialName("carousel_meta") val carouselMeta: CarouselMeta? = null,

    @SerialName("carousel_data") val carouselData: List<CarouselData> = listOf()

) : Post() {

    @Serializable
    data class CarouselMeta(
        @SerialName("title") val title: String? = null,
        @SerialName("icon") val icon: String? = null,
        @SerialName("sub_title") val subTitle: String? = null
    )

    @Serializable
    data class CarouselData(
        @SerialName("id") val id: Long,

        @SerialName("image_url") val imageUrl: String? = null,
        @SerialName("content") val content: String? = null,
        @SerialName("action") val action: String? = null
    )
}

@Serializable
@SerialName("QUICK_NOTIFICATION")
data class QuickNotification(
    override val id: Long,

    @SerialName("quick_notification_meta") val quickNotificationMeta: QuickNotificationMeta? = null
) : Post() {

    @Serializable
    data class QuickNotificationMeta(
        @SerialName("icon") val icon: String? = null,
        @SerialName("title") val title: String? = null,
        @SerialName("button_text") val buttonText: String? = null,
        @SerialName("sub_title") val subTitle: String? = null,
        @SerialName("action") val action: String? = null
    )
}

@Serializable
@SerialName("FORM")
data class Form(
    override val id: Long,

    @SerialName("form_meta") val formMeta: FormMeta? = null,
    @SerialName("form_items") val formItems: List<FormItem> = listOf()

) : Post() {
    @Serializable
    data class FormMeta(
        @SerialName("title") val title: String? = null,
        @SerialName("button_text") val buttonText: String? = null,
    )

    @Serializable
    data class FormItem(
        @SerialName("hint") val hint: String? = null,
        @SerialName("type") val type: String? = null,
        @SerialName("text_limit") val textLimit: Int = 10,
        @SerialName("action") val action: String? = null,
    )
}

@Serializable
@SerialName("ARTICLE_FULLSCREEN")
data class ArticleFullScreen(
    override val id: Long,

    @SerialName("article_meta") val articleMeta: ArticleMeta? = null,
    @SerialName("article_items") val articleItem: List<ArticleItem> = listOf()

) : Post() {

    @Serializable
    data class ArticleMeta(
        @SerialName("title") val title: String? = null,
    )

    @Serializable
    data class ArticleItem(
        @SerialName("id") val id: Long,
        var isLastItem: Boolean = false,
        @SerialName("image_url") val imageUrl: String? = null,
        @SerialName("title") val title: String? = null,
        @SerialName("content") val content: String? = null,
        @SerialName("action") val action: String? = null,
    )
}

@Serializable
@SerialName("ARTICLE")
data class Article(
    override val id: Long,
    @SerialName("article_meta") val articleMeta: ArticleMeta? = null,
    @SerialName("article_items") val articleItem: List<ArticleItem> = listOf()

): Post() {
    @Serializable
    data class ArticleMeta(
        @SerialName("title") val title: String? = null,
    )

    @Serializable
    data class ArticleItem(
        @SerialName("id") val id: Long,

        @SerialName("image_url") val imageUrl: String? = null,
        @SerialName("title") val title: String? = null,
        @SerialName("content") val content: String? = null,
        @SerialName("action") val action: String? = null,
    )
}

@Serializable
@SerialName("TOOLBAR")
data class Toolbar(
    override val id: Long,

    @SerialName("toolbar_meta") val toolbarMeta: ToolbarMeta? = null,
): Post() {
    @Serializable
    data class ToolbarMeta(
        @SerialName("title") val title: String? = null
    )
}


sealed class HomeState {
    data class Success(val posts: List<Post>) : HomeState()
    data class Loading(val message: String) : HomeState()
    data class Error(val exception: Throwable) : HomeState()
}