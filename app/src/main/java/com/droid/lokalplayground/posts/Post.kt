package com.droid.lokalplayground.posts

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Polymorphic
@Serializable
abstract class Post {
    abstract val id: Long
}

@Polymorphic
@Serializable
abstract class Style {
    abstract val width: Int
    abstract val height: Int
}

@Polymorphic
@Serializable
abstract class LokalView {
    abstract val width: Int
    abstract val height: Int
}

@Polymorphic
@Serializable
abstract class LokalViewType {
    abstract val id: Long
}

@Serializable
@SerialName("BANNER")
data class BannerType(
    override val id: Long,
    @SerialName("style") val bannerStyle: BannerStyle,
    @SerialName("banner_meta") val bannerMeta: BannerMeta? = null,
    @SerialName("banner_data") val bannerData: List<BannerData> = listOf()
) : LokalViewType()

@Serializable
@SerialName("CARDS")
data class CardType(
    override val id: Long,
    @SerialName("config") val cardConfig: CardConfig? = null,
    @SerialName("children") val children: List<Post>,
) : LokalViewType() {
    @Serializable
    data class CardConfig(
        @SerialName("snap_enabled") val snapEnabled: Boolean = false
    )
}


// =========================== VIEWS ===============================

@Serializable
@SerialName("TEXT_VIEW")
data class LokalTextView(
    override val width: Int,
    override val height: Int,
    @SerialName("text_size") val textSize: Int,
    @SerialName("drawable_padding") val drawablePadding: Int? = null,
    @SerialName("text") val text: String,
    @SerialName("url") val url: String? = null,
    @SerialName("gravity") val gravity: String,
    @SerialName("text_style") val textStyle: String,
    @SerialName("text_color") val textColor: String,
    @SerialName("padding") val padding: List<Int> = arrayListOf(),
    @SerialName("margin") val margin: List<Int> = arrayListOf(),
) : LokalView()

@Serializable
@SerialName("IMAGE_VIEW")
data class LokalImageView(
    override val width: Int,
    override val height: Int,
    @SerialName("url") val url: String,
    @SerialName("corner_radius") val cornerRadius: Int,
    @SerialName("scale_type") val scaleType: String,
    @SerialName("margin") val margin: List<Int> = arrayListOf(),
) : LokalView()

// =========================== VIEWS ===============================


@Serializable
@SerialName("QUICK_NOTIFICATION")
data class QuickNotification(
    override val id: Long,
    @SerialName("style") val style: QuickNotificationStyle? = null,
    @SerialName("children") val children: List<LokalView>? = null,
    val quickNotificationMeta: QuickNotificationMeta? = null
) : Post() {

    @Serializable
    data class QuickNotificationMeta(
        val isLastItem: Boolean = false
    )

    @Serializable
    @SerialName("QUICK_NOTIFICATION_STYLE")
    data class QuickNotificationStyle(
        override val width: Int,
        override val height: Int,
        @SerialName("min_height") val minHeight: Int = 0,
        @SerialName("corner_radius") val cornerRadius: Int = 0,
        @SerialName("background_color") val backgroundColor: String? = null,
        @SerialName("margin") val margin: List<Int> = arrayListOf(),
        @SerialName("padding") val padding: List<Int> = arrayListOf()
    ) : Style()
}


@Serializable
@SerialName("BANNER_STYLE")
data class BannerStyle(
    override val width: Int,
    override val height: Int,
    @SerialName("scale_type") val scaleType: String,
    @SerialName("margin") val margin: List<Int> = arrayListOf()
) : Style()


@Serializable
@SerialName("TOOLBAR_STYLE")
data class ToolbarStyle(
    override val width: Int,
    override val height: Int,
    @SerialName("text_size") val textSize: Int = 16,
    @SerialName("background_color") val backgroundColor: String,
    @SerialName("text_color") val textColor: String,
    @SerialName("gravity") val gravity: String,
    @SerialName("margin") val margin: List<Int> = arrayListOf(),
    @SerialName("padding") val padding: List<Int> = arrayListOf(),
) : Style()

@Serializable
data class BannerMeta(
    @SerialName("interval") val interval: Long = 4,
    @SerialName("type") val type: String? = null,
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
        @SerialName("id") val id: Long,

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

) : Post() {
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
) : Post() {
    @Serializable
    data class ToolbarMeta(
        @SerialName("style") val toolbarStyle: ToolbarStyle,
        @SerialName("title") val title: String? = null
    )
}

@Serializable
@SerialName("HEADER")
data class Header(
    override val id: Long,
    @SerialName("title") val title: String,
    @SerialName("style") val style: HeaderStyle,
) : Post() {
    @Serializable
    data class HeaderStyle(
        override val width: Int,
        override val height: Int,
        @SerialName("text_color") val textColor: String,
        @SerialName("text_size") val textSize: Int,
        @SerialName("gravity") val gravity: String,
        @SerialName("text_style") val textStyle: String,
        @SerialName("padding") val padding: List<Int> = arrayListOf(),
        @SerialName("margin") val margin: List<Int> = arrayListOf()
    ) : Style()
}

sealed class HomeState {
    data class Success(val posts: List<LokalViewType>) : HomeState()
    data class Loading(val message: String) : HomeState()
    data class Error(val exception: Throwable) : HomeState()
}