package com.droid.lokalplayground

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

abstract class AdListable {
    abstract val id: Int
    abstract val type: Int
    abstract val objectType: Int
}



@Serializable
@Parcelize
data class AdCardPost(
    override val id: Int = 2,
    override val type: Int,
    override val objectType: Int,

    @SerialName("is_active") val isActive: Boolean = false,
    @SerialName("collapse") val collapse: Boolean = false,
    @SerialName("is_premium") val isPremium: Boolean = false,
    @SerialName("video_processed") val videoProcessed: Boolean = false,

    @SerialName("is_liked") val isLiked: Int = 0,
    @SerialName("likes") val likes: Int = 0,
    @SerialName("dislikes") val dislikes: Int = 0,
    @SerialName("shares") val shares: Int = 0,
    @SerialName("fb_shares") val fbShares: Int = 0,
    @SerialName("views") val views: Int = 0,
    @SerialName("info_post_config") val infoPostConfig: Int = 0,
    @SerialName("num_comments") val commentsCount: Int = 0,

    @SerialName("title") val title: String? = null,
    @SerialName("updated_on") val updatedOn: String? = null,
    @SerialName("created_on") val createdOn: String? = null,
    @SerialName("author") val author: String? = null,
    @SerialName("slug") val slug: String? = null,
    @SerialName("content") val content: String? = null,
    @SerialName("custom_link") val customLink: String? = null,
    @SerialName("button_text") val bottomText: String? = null,
    @SerialName("locations") val locations: List<LokalLocation> = listOf(),
    @SerialName("categories") val categories: List<Classified.ClassifiedNudgeList.Category> = listOf(),
    @SerialName("ugc_categories") val ugcCategories: List<Submission.UgcCategory> = listOf(),
    @SerialName("tags") val tags: List<Classified.Tag> = listOf(),
    @SerialName("images") val images: List<ServiceNudgeBanner.ServiceNudgeBannerItem.Image> = listOf(),
    @SerialName("videos") val videos: List<Video> = listOf(),

    @SerialName("reporter") val reporter: Submission.SubmissionPostDetails.Reporter? = null,
    @SerialName("submit") val submit: Submit? = null

) : Parcelable, AdListable()

@Serializable
@Parcelize
data class FilterPost(
    override val id: Int,
    override val type: Int = 18,
    override val objectType: Int,

    @SerialName("title") val title: String? = null,
    @SerialName("thumb_url") val thumbUrl: String? = null,
    @SerialName("updated_on") val updatedOn: String? = null,
    @SerialName("content") val content: String? = null,

    @SerialName("tags") val tags: List<Classified.Tag> = listOf()

) : Parcelable, AdListable()

@Serializable
@Parcelize
data class PollPost(
    override val id: Int,
    override val type: Int = 9,
    override val objectType: Int,

    @SerialName("title") val title: String? = null,
    @SerialName("thumb_url") val thumbUrl: String? = null,


    @SerialName("num_comments") val commentsCount: Int = 0,
    @SerialName("shares") val shares: Int = 0,
    @SerialName("fb_shares") val fbShares: Int = 0,
    @SerialName("total_votes") val totalVotes: Int = 0,
    @SerialName("is_voted") val isVoted: Int = 0,
    @SerialName("is_ended") val isEnded: Boolean = false,

    @SerialName("end_time") val endTime: String? = null,
    @SerialName("updated_on") val updatedOn: String? = null
) : Parcelable, AdListable() {
    @Serializable
    @Parcelize
    data class PollOption(
        @SerialName("id") val id: Int,
        @SerialName("title") val title: String? = null,
        @SerialName("thumb_url") val thumbUrl: String? = null,
        @SerialName("num_votes") val votes: Int = 0,
        @SerialName("is_correct") val isCorrect: Boolean = false,
        @SerialName("poll") val poll: Int = 0
    ) : Parcelable

}

@Serializable
@Parcelize
data class ArticlePost(
    override val id: Int = 2,
    override val type: Int,
    override val objectType: Int,

    @SerialName("is_active") val isActive: Boolean = false,
    @SerialName("collapse") val collapse: Boolean = false,
    @SerialName("is_premium") val isPremium: Boolean = false,
    @SerialName("video_processed") val videoProcessed: Boolean = false,

    @SerialName("is_liked") val isLiked: Int = 0,
    @SerialName("likes") val likes: Int = 0,
    @SerialName("dislikes") val dislikes: Int = 0,
    @SerialName("shares") val shares: Int = 0,
    @SerialName("fb_shares") val fbShares: Int = 0,
    @SerialName("views") val views: Int = 0,
    @SerialName("info_post_config") val infoPostConfig: Int = 0,
    @SerialName("num_comments") val commentsCount: Int = 0,

    @SerialName("title") val title: String? = null,
    @SerialName("updated_on") val updatedOn: String? = null,
    @SerialName("created_on") val createdOn: String? = null,
    @SerialName("author") val author: String? = null,
    @SerialName("slug") val slug: String? = null,
    @SerialName("content") val content: String? = null,
    @SerialName("locations") val locations: List<LokalLocation> = listOf(),
    @SerialName("categories") val categories: List<Classified.ClassifiedNudgeList.Category> = listOf(),
    @SerialName("ugc_categories") val ugcCategories: List<Submission.UgcCategory> = listOf(),
    @SerialName("tags") val tags: List<Classified.Tag> = listOf(),
    @SerialName("images") val images: List<ServiceNudgeBanner.ServiceNudgeBannerItem.Image> = listOf(),
    @SerialName("videos") val videos: List<Video> = listOf(),

    @SerialName("reporter") val reporter: Submission.SubmissionPostDetails.Reporter? = null,
    @SerialName("submit") val submit: Submit? = null

) : Parcelable, AdListable()

@Serializable
@Parcelize
data class SharePost(
    override val id: Int = 5,
    override val type: Int,
    override val objectType: Int,

    @SerialName("is_active") val isActive: Boolean = false,
    @SerialName("collapse") val collapse: Boolean = false,
    @SerialName("is_premium") val isPremium: Boolean = false,
    @SerialName("video_processed") val videoProcessed: Boolean = false,

    @SerialName("is_liked") val isLiked: Int = 0,
    @SerialName("likes") val likes: Int = 0,
    @SerialName("dislikes") val dislikes: Int = 0,
    @SerialName("shares") val shares: Int = 0,
    @SerialName("fb_shares") val fbShares: Int = 0,
    @SerialName("views") val views: Int = 0,
    @SerialName("info_post_config") val infoPostConfig: Int = 0,
    @SerialName("num_comments") val commentsCount: Int = 0,

    @SerialName("title") val title: String? = null,
    @SerialName("updated_on") val updatedOn: String? = null,
    @SerialName("created_on") val createdOn: String? = null,
    @SerialName("author") val author: String? = null,
    @SerialName("slug") val slug: String? = null,
    @SerialName("content") val content: String? = null,
    @SerialName("locations") val locations: List<LokalLocation> = listOf(),
    @SerialName("categories") val categories: List<Classified.ClassifiedNudgeList.Category> = listOf(),
    @SerialName("ugc_categories") val ugcCategories: List<Submission.UgcCategory> = listOf(),
    @SerialName("tags") val tags: List<Classified.Tag> = listOf(),
    @SerialName("images") val images: List<ServiceNudgeBanner.ServiceNudgeBannerItem.Image> = listOf(),
    @SerialName("videos") val videos: List<Video> = listOf(),

    @SerialName("reporter") val reporter: Submission.SubmissionPostDetails.Reporter? = null,
    @SerialName("submit") val submit: Submit? = null

) : Parcelable, AdListable()

@Serializable
@Parcelize
data class TagPost(
    override val id: Int = 7,
    override val type: Int,
    override val objectType: Int,

    @SerialName("title") val title: String? = null,
    @SerialName("updated_on") val updatedOn: String? = null,
    @SerialName("collapsed_tag_count") val collapsedTagCount: Int = 0,
    @SerialName("tags") val tags: List<Classified.Tag>? = listOf()

) : Parcelable, AdListable()

@Serializable
@Parcelize
data class VoiceOfThePeople(
    override val id: Int = 24,
    override val type: Int,
    override val objectType: Int,

    @SerialName("title") val title: String? = null,
    @SerialName("updated_on") val updatedOn: String? = null,
    @SerialName("icon") val icon: String? = null,

    @SerialName("vops") val vopsList: List<Vops>? = listOf()

) : Parcelable, AdListable() {

    @Serializable
    @Parcelize
    data class Vops(
        @SerialName("id") val id: Int,
        @SerialName("post") val articlePost: ArticlePost? = null,
        @SerialName("poll") val poll: Poll? = null,
        @SerialName("reactions") val reactions: Reactions? = null,

        @SerialName("is_liked") val isLiked: Int = 0,
        @SerialName("success_story") val successStory: String? = null,
        @SerialName("type") val type: Int = 0

    ) : Parcelable {
        @Serializable
        @Parcelize
        data class Reactions(
            @SerialName("num_likes") val likes: Int = 0,
            @SerialName("num_comments") val comments: Int = 0,
            @SerialName("shares") val shares: Int = 0,
            @SerialName("num_shares") val numShares: Int = 0,
            @SerialName("fb_shares") val fbShares: Int = 0,
            @SerialName("num_fb_shares") val numFbShares: Int = 0
        ) : Parcelable

        @Serializable
        @Parcelize
        data class Poll(
            @SerialName("id") val id: Int,
            @SerialName("title") val title: String? = null,
            @SerialName("end_time") val endTime: String? = null,
            @SerialName("updated_on") val updatedOn: String? = null,
            @SerialName("type") val type: Int = 0,
            @SerialName("total_votes") val totalVotes: Int = 0,
            @SerialName("is_voted") val isVoted: Int = 0,

            @SerialName("is_ended") val isEnded: Boolean = false,

            @SerialName("options") val pollOptions: List<PollOption> = listOf()

        ) : Parcelable {

            @Serializable
            @Parcelize
            data class PollOption(
                @SerialName("id") val id: Int,
                @SerialName("title") val title: String? = null,
                @SerialName("thumb_url") val thumbUrl: String? = null,
                @SerialName("num_votes") val numVotes: Int = 0,
                @SerialName("poll") val Int: Int = 0,
                @SerialName("is_correct") val isEnded: Boolean = false
            ) : Parcelable
        }
    }
}

@Serializable
@Parcelize
data class CategoriesCardNudge(
    override val id: Int = 23,
    override val type: Int,
    override val objectType: Int,

    @SerialName("title") val title: String? = null,
    @SerialName("position") val position: Int = 0,
    @SerialName("updated_on") val updatedOn: String? = null,
    @SerialName("categories_nudge") val categoriesList: List<CategoryCard> = listOf()

) : Parcelable, AdListable() {

    @Serializable
    @Parcelize
    data class CategoryCard(
        @SerialName("id") val id: Int,
        @SerialName("name") val name: String? = null,
        @SerialName("english_name") val englishName: String? = null,
        @SerialName("image") val image: String? = null,
    ) : Parcelable
}

@Serializable
@Parcelize
data class LokalServices(
    override val id: Int = 20,
    override val type: Int,
    override val objectType: Int,

    @SerialName("title") val title: String? = null,
    @SerialName("updated_on") val updatedOn: String? = null,
    @SerialName("services_nudge") val services: List<LokalService> = listOf(),
    @SerialName("position") val position: Int = 0,

    ) : Parcelable, AdListable() {

    @Serializable
    @Parcelize
    data class LokalService(
        @SerialName("id") val id: Int,
        @SerialName("name_eng") val englishName: String? = null,
        @SerialName("name") val name: String? = null,
        @SerialName("icon_url") val iconUrl: String? = null,
        @SerialName("url") val url: String? = null,
        @SerialName("new_posts_count_id") val newPostsCountId: String? = null,
        @SerialName("show_new_posts_count") val showNewPostsCount: Boolean = false

    ) : Parcelable
}

@Serializable
@Parcelize
data class InfoPost(
    override val id: Int = 11,
    override val type: Int,
    override val objectType: Int,

    @SerialName("title") val title: String? = null,
    @SerialName("slug") val slug: String? = null,
    @SerialName("updated_on") val updatedOn: String? = null,
    @SerialName("created_on") val createdOn: String? = null,
    @SerialName("author") val author: String? = null,
    @SerialName("content") val content: String? = null,
    @SerialName("tags") val tags: List<Classified.Tag>? = listOf(),

    @SerialName("reporter") val reporter: Submission.SubmissionPostDetails.Reporter? = null,

    @SerialName("is_active") val isActive: Boolean = false,
    @SerialName("collapse") val collapse: Boolean = false,
    @SerialName("is_premium") val isPremium: Boolean = false,
    @SerialName("video_processed") val videoProcessed: Boolean = false,

    @SerialName("is_liked") val isLiked: Int = 0,
    @SerialName("likes") val likes: Int = 0,
    @SerialName("dislikes") val dislikes: Int = 0,
    @SerialName("shares") val shares: Int = 0,
    @SerialName("fb_shares") val fbShares: Int = 0,
    @SerialName("views") val views: Int = 0,
    @SerialName("info_post_config") val infoPostConfig: Int = 0,
    @SerialName("num_comments") val commentsCount: Int = 0,


    @SerialName("locations") val locations: List<LokalLocation> = listOf(),
    @SerialName("categories") val categories: List<Classified.ClassifiedNudgeList.Category> = listOf()

) : Parcelable, AdListable()

@Serializable
@Parcelize
data class ClassifiedPost(
    override val id: Int = 14,
    override val type: Int,
    override val objectType: Int,

    @SerialName("title") val title: String? = null,
    @SerialName("updated_on") val updatedOn: String? = null,
    @SerialName("collapsed_tag_count") val collapsedTagCount: Int = 0,
    @SerialName("position") val position: Int = -1,
    @SerialName("lowest_version_code") val lowestVersionCode: Int = 0,
    @SerialName("check_for_intent") val checkForIntent: Boolean = false,

    @SerialName("classifieds") val classifiedList: List<Classified> = listOf()

) : Parcelable, AdListable() {
    @Serializable
    @Parcelize
    data class Classified(
        @SerialName("button_text") val buttonText: String? = null,
        @SerialName("background_color") val backgroundColor: String? = null,
        @SerialName("action") val action: String? = null,
        @SerialName("thumb_url") val thumbUrl: String? = null
    ) : Parcelable
}

@Serializable
@Parcelize
data class Submit(
    @SerialName("is_user_submitted") val isUserSubmitted: Boolean = false,
    @SerialName("message") val message: String? = null
) : Parcelable

@Serializable
@Parcelize
data class Video(
    @SerialName("stream_url") val streamUrl: String? = null,
    @SerialName("video_url") val videoUrl: String? = null,
    @SerialName("duration_string") val durationString: String? = null,
    @SerialName("size_string") val size: String? = null,
    @SerialName("duration") val duration: Long = 0,
) : Parcelable

@Serializable
@Parcelize
data class Ad(
    @SerialName("id") override val id: Int,

    override val objectType: Int = AdListableConstants.ObjectType.AD_OBJECT,

    @SerialName("ad_type") override val type: Int = -1,

    @SerialName("link_type") val linkType: Int = -1,
    @SerialName("custom_link") val customLink: String? = null,
    @SerialName("text") val text: String? = null,
    @SerialName("html_content") val htmlContent: String? = null,
    @SerialName("creatives") val creatives: List<Creative> = listOf(),
    @SerialName("call_to_action") val callToAction: CallToAction? = null,
    @SerialName("shares") val shares: Int = 0,
    @SerialName("fb_shares") val fbShares: Int = 0,
    @SerialName("videos") val videos: List<Video> = listOf(),
    @SerialName("impression_tracking") val impressionTracking: ImpressionTracking? = null,
    @SerialName("display_duration") val displayDuration: Int = 0,
    @SerialName("profile_image") val profileImage: String? = null,
    @SerialName("have_swipead_headers") val showSwipeAdHeaders: Boolean = false,
    @SerialName("advertiser_image") val advertiserImage: String? = null,


    var isViewMoreTextExpanded: Boolean = false,
    var isMute: Boolean = true

) : AdListable(), Parcelable {

    @Serializable
    @Parcelize
    data class ImpressionTracking(
        @SerialName("url") val url: String? = null,
        @SerialName("method") val methodType: String? = null,
    ) : Parcelable

    @Serializable
    @Parcelize
    data class Video(
        @SerialName("stream_url") val streamUrl: String? = null,
        @SerialName("video_url") val videoUrl: String? = null,
        @SerialName("duration_string") val duration: String? = null,
        @SerialName("size_string") val size: String? = null,
    ) : Parcelable

    @Serializable
    @Parcelize
    data class Creative(
        @SerialName("content_uri") val contentUri: String? = null,
        @SerialName("file") val file: String? = null,
        @SerialName("creative_type") val type: Int = 0,
        @SerialName("thumb_url") val thumbUrl: String? = null,
        @SerialName("height") val height: Int = 0,
        @SerialName("width") val width: Int = 0,
        @SerialName("text") val text: String? = null,
        @SerialName("custom_link") val customLink: String? = null,
        @SerialName("link_type") val linkType: Int = -1,
        @SerialName("order_id") val orderId: Int = -1,
    ) : Parcelable

    @Serializable
    @Parcelize
    data class CallToAction(
        @SerialName("name") val name: String? = null,
    ) : Parcelable
}

@Serializable
@Parcelize
data class AdMobNativeAd(
    override val id: Int = -1,
    override val type: Int,
    override val objectType: Int = AdListableConstants.ObjectType.AD_MOB_OBJECT,

    val nativeAd: NativeAd? = null

) : AdListable(), Parcelable {
    //TODO Replace NativeAd with real implementation
    @Serializable
    @Parcelize
    data class NativeAd(val name: String) : Parcelable
}

@Serializable
@Parcelize
data class BuyAndSellFilterData(
    override val id: Int = -1,
    override val type: Int,
    override val objectType: Int = -1,

    @SerialName("title") private var title: String? = null,
    @SerialName("filters") val filters: List<BuyAndSellFilter>? = null,
    @SerialName("collapsed_count_level1_filters") val collapsedCountLv1Filters: Int = 0,
    @SerialName("collapsed_count_level2_filters") val collapsedCountLv2Filters: Int = 0,
    val selectedLevel1Filter: BuyAndSellFilter? = null

) : AdListable(), Parcelable {

    @Serializable
    @Parcelize
    data class BuyAndSellFilter(
        @SerialName("id") val id: Int,
        @SerialName("title") val title: String?,
        @SerialName("english_name") val englishName: String?,
        @SerialName("image_url") val imageUrl: String?,
        @SerialName("type") val type: Int,
        @SerialName("order") val order: Int,
        @SerialName("have_children") val haveChildren: Int,
        @SerialName("highlighted_flag_text") val highlightedText: String?,
        @SerialName("background_color") val backgroundColor: String?,
        var isSelected: Boolean,
        var extraFiltersCount: Int
    ) : Parcelable
}

@Serializable
@Parcelize
data class JobsFilterData(
    override val id: Int = -1,
    override val type: Int,
    override val objectType: Int = -1,

    @SerialName("title") private var title: String? = null,
    @SerialName("filters") val filters: List<JobsFilter>? = null,
) : AdListable(), Parcelable {

    @Serializable
    @Parcelize
    data class JobsFilter(
        @SerialName("id") val id: Int,
        @SerialName("title") val title: String? = null,
        @SerialName("english_name") val englishName: String? = null,
        @SerialName("type") val type: Int = 0,
        @SerialName("order") val order: Int = 0,
        @SerialName("filter_value") val filterValues: List<Int?> = listOf(),
        var selectedFiltersCount: Int = 0,
        var isSelected: Boolean
    ) : Parcelable
}

@Serializable
@Parcelize
data class Classified(
    override val id: Int,
    override val type: Int,
    override val objectType: Int = AdListableConstants.ObjectType.POST_OBJECT,

    @SerialName("title") val title: String? = null,
    @SerialName("company_name") val companyName: String? = null,
    @SerialName("primary_details") val primaryDetails: ClassifiedPrimaryDetail? = null,
    @SerialName("content") val content: String? = null,
    @SerialName("advertiser") val advertiser: Int = -1,
    @SerialName("author") val author: String? = null,
    @SerialName("custom_link") val customLink: String? = null,
    @SerialName("amount") val amount: String? = null,
    @SerialName("views") val views: Int = -1,
    @SerialName("is_active") val isActive: Boolean = false,
    @SerialName("updated_on") val updatedOn: String? = null,
    @SerialName("status") val status: Int = -1,
    @SerialName("shares") val shares: Int = -1,
    @SerialName("fb_shares") val fbShares: Int = -1,
    @SerialName("created_on") val createdOn: String? = null,
    @SerialName("is_premium") val isPremium: Boolean = false,
    @SerialName("is_applied") val isApplied: Boolean = false,
    @SerialName("is_bookmarked") val isBookmarked: Boolean = false,
    @SerialName("creatives") val creatives: List<Ad.Creative> = listOf(),
    @SerialName("videos") val videos: List<Ad.Video> = listOf(),
    @SerialName("button_text") val buttonText: String? = null,
    @SerialName("values") val mainPageDetails: List<ClassifiedMainPageDetails> = listOf(),

    @SerialName("tags") val tags: List<Tag> = listOf(),
    @SerialName("collapsed_tag_count") val collapsedTagCount: Int = 5,

    @SerialName("services") val classifiedServices: List<ClassifiedService> = listOf(),
    @SerialName("primary_content") val primaryContent: HashMap<String, PrimaryContent> = emptyMap<String, PrimaryContent>() as HashMap<String, PrimaryContent>,
    @SerialName("published_content") val publishedContent: HashMap<String, PrimaryContent> = emptyMap<String, PrimaryContent>() as HashMap<String, PrimaryContent>,
    @SerialName("departments") val departments: List<BuyAnsSellDepartment> = listOf(),
    @SerialName("recommendation_header_config") val recommendationHeaderConfig: RecommendationHeaderConfig? = null,
    @SerialName("contentV3") val classifiedContentV3: ClassifiedContentV3? = null,
    @SerialName("services_nudge") val servicesNudgeList: List<MatrimonyProfile.ServiceNudge> = listOf(),

    @SerialName("is_badge_info_loaded") val isBadgeInfoLoaded: Boolean = false,
    @SerialName("categories_nudge") val categoriesNudgeList: List<ClassifiedNudgeList.Category> = listOf(),
    @SerialName("job_tags") val jobTagsList: List<JobTags> = listOf(),

    @SerialName("fee_details") val feeDetails: ClassifiedContentV3? = null,

    @SerialName("job_hours") val jobHours: String? = null,
    @SerialName("other_details") val jobOtherDetails: String? = null,
    @SerialName("job_role") val jobRole: String? = null,
    @SerialName("job_category") val jobCategory: String? = null,
    @SerialName("openings_count") val jobOpeningCount: Int = -1

) : AdListable(), Parcelable {


    @Serializable
    @Parcelize
    data class JobTags(
        @SerialName("value") val value: String? = null,
        @SerialName("bg_color") val bgColor: String? = null,
        @SerialName("text_color") val textColor: String? = null
    ) : Parcelable


    @Serializable
    @Parcelize
    data class ClassifiedContentV3(@SerialName("V3") val contentData: List<ClassifiedContentData> = listOf()) :
        Parcelable {

        @Serializable
        @Parcelize
        data class ClassifiedContentData(
            @SerialName("field_key") val fieldKey: String? = null,
            @SerialName("field_name") val fieldName: String? = null,
            @SerialName("field_value") val fieldValue: String? = null
        ) : Parcelable
    }


    @Serializable
    @Parcelize
    data class RecommendationHeaderConfig(@SerialName("title") val title: String?) : Parcelable

    @Serializable
    @Parcelize
    data class BuyAnsSellDepartment(
        @SerialName("id") val id: Int,
        @SerialName("title") val title: String? = null,
        @SerialName("english_name") val englishName: String? = null,
        @SerialName("type") val type: Int = 0
    ) : Parcelable


    @Serializable
    @Parcelize
    data class PrimaryContent(
        @SerialName("icon_url") val iconUrl: String? = null,
        @SerialName("translated_key") val translatedKey: String? = null,
        @SerialName("value") val value: String? = null,
        @SerialName("order") val order: Int = 0
    ) : Parcelable


    @Serializable
    @Parcelize
    data class ClassifiedService(
        @SerialName("id") val id: Int,
        @SerialName("name") val name: String? = null,
        @SerialName("english_name") val engName: String? = null,
        @SerialName("category_config") val categoryConfig: ClassifiedNudgeList.Category.CategoryConfig? = null,
        @SerialName("save_new_posts_count_timestamp") val timestampMode: Int = 0
    ) : Parcelable

    @Serializable
    @Parcelize
    data class Tag(
        @SerialName("id") val id: Int,
        @SerialName("title") val name: String? = null,
        @SerialName("english_name") val englishName: String? = null,
        @SerialName("type") val type: Int = -1,
        @SerialName("thumb_url") val thumbUrl: String? = null,
    ) : Parcelable

    @Serializable
    @Parcelize
    data class ClassifiedMainPageDetails(
        @SerialName("title") val title: String? = null,
        @SerialName("Subtitle") val subTitle: String? = null,
        @SerialName("action") val action: String? = null,
        @SerialName("classified_sublist") val mainPageDetails: List<Classified> = listOf(),
        @SerialName("matrimony_sublist") val matrimonySublist: List<MatrimonyProfile> = listOf(),
        @SerialName("category_list") val classifiedNudgeList: List<ClassifiedNudgeList> = listOf(),
    ) : Parcelable

    @Serializable
    @Parcelize
    data class ClassifiedNudgeList(
        @SerialName("text") val text: String? = null,
        @SerialName("sub_text") val subText: String? = null,
        @SerialName("icon_url") val iconUrl: String? = null,
        @SerialName("background_color") val backgroundColor: String? = null,
        @SerialName("background_image_url") val backgroundImageUrl: String? = null,
        @SerialName("category") val category: Category? = null,

        @SerialName("action") val action: String? = null,
        @SerialName("background_type") val backgroundType: Int = 0,
    ) : Parcelable {

        @Serializable
        @Parcelize
        data class Category(
            @SerialName("id") val id: Int,
            @SerialName("name") val name: String? = null,
            @SerialName("english_name") val engName: String? = null,
            @SerialName("show_ads") val showAds: Boolean = false,
            @SerialName("ads_position") val adPositions: List<Int> = listOf(),

            val locations: List<String> = listOf(),

            @SerialName("image") val image: String? = null,
            @SerialName("url") val resultsApi: String? = null,
            @SerialName("call_type") val viewConfig: Int = 0,
            @SerialName("categories") val categories: List<Category> = listOf(),
            @SerialName("type") val type: Int = -1,
            @SerialName("category_config") val categoryConfig: CategoryConfig? = null,
            @SerialName("is_home_feed") val isHomeFeed: Boolean = false,
            @SerialName("save_new_posts_count_timestamp") val timeStampMode: Int = 0,

            var fragmentType: Int = 1,
            var position: Int = -1


        ) : Parcelable {

            @Serializable
            @Parcelize
            data class CategoryConfig(
                @SerialName("custom_api") val customApi: String? = null,
                @SerialName("adsConfig") val adConfigData: AdConfigData? = null,
            ) : Parcelable {

                @Serializable
                @Parcelize
                data class AdConfigData(
                    @SerialName("lokalads") val lokaladsConfig: AdConfig? = null,
                    @SerialName("admob") val adMobConfig: AdConfig? = null,
                    @SerialName("ad_manager") val adManagerConfig: AdConfig? = null,
                ) : Parcelable {

                    @Serializable
                    @Parcelize
                    data class AdConfig(
                        @SerialName("showads") val showAds: Boolean = false,
                        @SerialName("endpoint") val endpoint: String? = null,
                        @SerialName("positions") val positions: List<Int> = listOf(),
                    ) : Parcelable
                }
            }
        }
    }

    @Serializable
    @Parcelize
    data class ClassifiedPrimaryDetail(
        @SerialName("Place") val place: String? = null,
        @SerialName("Salary") val salary: String? = null,
        @SerialName("Qualification") val qualification: String? = null,
        @SerialName("Experience") val experience: String? = null,
        @SerialName("Fees_Charged") val feesCharged: String? = null,
        @SerialName("Job_Type") val jobType: String? = null,
    ) : Parcelable
}

@Serializable
@Parcelize
class ReporterProfile(
    override val id: Int = SubmissionCardType.TYPE_REPORTER_EARNINGS,
    override val type: Int = SubmissionCardType.TYPE_REPORTER_EARNINGS,
    override val objectType: Int = 0,

    @SerialName("goal") val goal: Int = 0,
    @SerialName("super_goal") val superGoal: Int = 0,
    @SerialName("current_earnings") val currentEarnings: String? = null,
    @SerialName("month") val month: String? = null,
    @SerialName("trained") var isTrainingDone: Boolean = false,
) : Parcelable, AdListable()

@Serializable
@Parcelize
data class PromotedJobsData(
    override val id: Int = AdListableConstants.AdServerCardType.TYPE_JOBS_PROMOTED_POSTS_SECTION,
    override val type: Int = AdListableConstants.AdServerCardType.TYPE_JOBS_PROMOTED_POSTS_SECTION,
    override val objectType: Int = AdListableConstants.ObjectType.POST_OBJECT,

    val promotedJobPosts: List<Classified> = listOf()

) : AdListable(), Parcelable

@Serializable
@Parcelize
data class RegisteredProfileWithStatus(
    override val id: Int = SubmissionCardType.TYPE_REGISTERED_USER_EARNINGS,
    override val type: Int = SubmissionCardType.TYPE_REGISTERED_USER_EARNINGS,
    override val objectType: Int = -1,

    val registeredProfile: RegisteredProfile? = null,
    val registrationState: Int = 0

) : Parcelable, AdListable() {

    @Serializable
    @Parcelize
    data class RegisteredProfile(
        @SerialName("id") val registeredId: Int,
        @SerialName("user") val user: Int = 0,
        @SerialName("file") var file: String? = null,
        @SerialName("doc_type") val docType: Int = 0,
        @SerialName("rejection_reason") val rejectionReason: String? = null,
        @SerialName("current_earnings") val currentUserEarnings: Int = 0,
        @SerialName("goal") val userGoal: Int = 0
    ) : Parcelable
}

@Serializable
@Parcelize
data class ServiceNudgeBanner(
    override val id: Int,
    override val type: Int,
    override val objectType: Int = -1,

    @SerialName("banner_position") val bannerPosition: List<Int> = ArrayList(),
    @SerialName("url") val url: String?,
    @SerialName("items") val items: List<ServiceNudgeBannerItem> = ArrayList(),
) : Parcelable, AdListable() {

    @Serializable
    @Parcelize
    data class ServiceNudgeBannerItem(
        @SerialName("id") val id: Int,
        @SerialName("item_type") val itemType: Int,
        @SerialName("title") val title: String?,
        @SerialName("content") val content: String?,
        @SerialName("images") val images: List<Image>?,
        @SerialName("url") val url: String?,
        @SerialName("locations") val location: List<LokalLocation>?,
        @SerialName("area") val area: String?
    ) : Parcelable {

        @Serializable
        @Parcelize
        data class Image(
            @SerialName("image") val image: String? = null,
            @SerialName("thumb_url") val thumbnailUrl: String? = null,
            @SerialName("height") val height: Int,
            @SerialName("width") val width: Int

        ) : Parcelable
    }
}

@Serializable
@Parcelize
data class LokalLocation(
    @SerialName("id") val id: Int,
    @SerialName("districts") val lokalLocations: List<LokalLocation> = listOf(),
    @SerialName("title") val title: String? = null,
    @SerialName("short_name") val shortName: String? = null,
    @SerialName("image") val image: String? = null,
    @SerialName("color") val color: String? = null,
    @SerialName("icon") val icon: String? = null,
    @SerialName("places") val places: String? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("type") val type: Int = 0,
    @SerialName("state") val state: Int = 0,
    @SerialName("lat") val latitude: Float = 0.0F,
    @SerialName("long") val longitude: Float = 0.0F,
    @SerialName("state_obj") val stateObj: LokalLocation? = null,
    @SerialName("locale") val locales: List<String> = listOf(),

    var isSelected: Boolean = false

) : Parcelable

@Serializable
@Parcelize
data class FeaturedData(
    @SerialName("count") val count: Int,
    @SerialName("next") val next: Unit,
    @SerialName("previous") val previous: Unit,
    @SerialName("results") val featuredPosts: List<FeaturedPost> = listOf()
) : Parcelable {

    @Serializable
    @Parcelize
    data class FeaturedPost(
        @SerialName("id") val id: Int,
        @SerialName("type") val type: Int = 0,
        @SerialName("which_kind") val whichKind: Int = 0,
        @SerialName("link_type") val linkType: Int = 0,
        @SerialName("title") val title: String? = null,
        @SerialName("image") val image: String? = null,
        @SerialName("uri") val uri: String? = null,
        @SerialName("button_text") val buttonText: String? = null,
        @SerialName("custom_obj") val category: Classified.ClassifiedNudgeList.Category? = null
    ) : Parcelable
}

@Serializable
@Parcelize
data class Submission(
    @SerialName("id") val id: Int,
    @SerialName("status") val status: Int,
    @SerialName("title") val title: String? = null,
    @SerialName("content") val content: String? = null,
    @SerialName("status_string") val statusString: String? = null,
    @SerialName("editors_message") val editorsMessage: String? = null,
    @SerialName("submitted_on") val submittedDate: String? = null,
    @SerialName("finalised_on") val updatedDate: String? = null,
    @SerialName("locale") val submissionLocale: String? = null,
    @SerialName("expire_on") val expireDate: String? = null,
    @SerialName("module_action") val rejectionAction: String? = null,
    @SerialName("custom_message") val customMessage: String? = null,
    @SerialName("ugc_categories_info") val contentTagsInfo: String? = null,
    @SerialName("attachments") val locales: List<String> = listOf(),

    @SerialName("post_url") val postUrl: String? = null,
    @SerialName("uri") val uri: String? = null,

    @SerialName("user") val user: Int = 0,
    @SerialName("resubmit") val resubmitCount: Int = 0,
    @SerialName("module") val guidelineID: Int = -1,
    @SerialName("category") val submissionCategoryId: Int = 0,

    @SerialName("state") val state: LokalLocation? = null,
    @SerialName("location") val district: LokalLocation? = null,
    @SerialName("microlocation") val microlocation: LokalLocation? = null,
    @SerialName("constituencies") val microlocationList: List<LokalLocation> = listOf(),
    @SerialName("ugc_categories") val contentTagsList: List<UgcCategory> = listOf(),

    @SerialName("post") val submissionPostDetails: SubmissionPostDetails? = null,

    @SerialName("breaking") val breaking: Boolean = false,
    @SerialName("anonymous") val anonymous: Boolean = false,

    var isSelected: Boolean = false,
    val currentItem: Int = -1,
    val progress: Int = -1,
    val attachmentRetries: Int = 0,
    val timeRemainingToEdit: Int = 0

) : Parcelable {

    @Serializable
    @Parcelize
    data class UgcCategory(
        @SerialName("id") val id: Int,
        @SerialName("name") val title: String? = null,
        @SerialName("english_text") val englishName: String? = null,
        @SerialName("is_active") val isActive: Boolean = false,

        var isSelected: Boolean = false,
        val currAdapterPos: Int = 0
    ) : Parcelable

    @Serializable
    @Parcelize
    data class SubmissionPostDetails(
        @SerialName("id") val id: Int,
        @SerialName("views") val views: Int = 0,
        @SerialName("num_likes") val numLikes: Int = 0,
        @SerialName("num_dislikes") val numDislikes: Int = 0,
        @SerialName("num_comments") val numComments: Int = 0,
        @SerialName("fb_shares") val fbShares: Int = 0,
        @SerialName("shares") val shares: Int = 0,
        @SerialName("is_liked") val isLiked: Int = 0,
        @SerialName("title") val title: String? = null,

        @SerialName("sub_reporters") val subAuthors: List<Reporter> = listOf(),
    ) : Parcelable {

        @Serializable
        @Parcelize
        data class Reporter(
            @SerialName("id") val id: Int,
            @SerialName("member_id") val memberId: Int = -1,
            @SerialName("reward_level") val medal: Int = -1,
            @SerialName("name") val title: String? = null,
            @SerialName("thumb_url") val thumbUrl: String? = null,
            @SerialName("verified") val reporterVerified: Boolean = false,
        ) : Parcelable
    }
}

//Matrimony
@Serializable
@Parcelize
data class MatrimonyFormContentValue(
    override val id: Int,
    @SerialName("value_type") override val type: Int,
    override val objectType: Int = 0,

    @SerialName("title") var title: String? = null,
    @SerialName("english_name") var englishName: String? = null,
    @SerialName("tag_id") var tagId: Int = 0,


    ) : AdListable(), Parcelable

@Serializable
@Parcelize
data class MatrimonyFilter(
    override val id: Int = -1,
    override val type: Int,
    override val objectType: Int = AdListableConstants.ObjectType.FILTER_OBJECT,

    @SerialName("title") var title: String? = null,
    @SerialName("english_name") var englishName: String? = null,
    @SerialName("group_name") var groupName: String? = null,
    @SerialName("options") var matrimonyFilterOptions: List<MatrimonyFilterOption> = listOf(),

    var isSelected: Boolean = false,
    val selectedOptionPos: Int = 0,
    val selectedOption: Int = 0,
    val selectedOptionContent: String? = null

) : AdListable(), Parcelable {

    fun getMatrimonyId(): Int {
        return if (matrimonyFilterOptions.isNotEmpty()) {
            matrimonyFilterOptions[0].id
        } else -1
    }

    @Serializable
    @Parcelize
    data class MatrimonyFilterOption(
        @SerialName("id") val id: Int,
        @SerialName("title") val title: String? = null,
        @SerialName("english_name") val englishName: String? = null,
        @SerialName("tag_id") val tagId: Int = 0,
        @SerialName("value_type") val type: Int = 0,
        @SerialName("order_id") val orderId: Int = 0,
    ) : Parcelable
}

@Serializable
@Parcelize
data class MatrimonyFilterGroup(
    override val id: Int,
    override val type: Int,
    override val objectType: Int,

    @SerialName("title") var title: String? = null,
    @SerialName("depended_by") var dependedBy: List<Int> = listOf(),
    @SerialName("dependent_on") var dependentOn: Int = 0,
    @SerialName("english_name") var englishName: String? = null,

    var selectedCount: Int = 0,
    var isSelected: Boolean = false,
    //TODO check what is ArrayListMultiMap?
    val selectedFiltersMap: Map<String, MatrimonyFilter> = emptyMap()
) : AdListable(), Parcelable

@Serializable
@Parcelize
data class MatrimonyFilterOption(
    override val id: Int,
    @SerialName("value_type") override val type: Int,
    override val objectType: Int = 0,

    @SerialName("title") var title: String? = null,
    @SerialName("english_name") var englishName: String? = null,
    @SerialName("tag_id") var tagId: Int = 0,
    @SerialName("order_id") var orderId: Int = 0,

    ) : AdListable(), Parcelable

@Serializable
@Parcelize
data class MatrimonyProfile(
    override val id: Int,
    override val type: Int,
    override val objectType: Int = AdListableConstants.ObjectType.POST_OBJECT,

    @SerialName("status") val status: Int = -1,
    @SerialName("primary_info") val primaryInfo: MatrimonyProfilePrimaryInfo? = null,
    @SerialName("content") val contentGroupsMap: LinkedHashMap<String, MatrimonyContentGroup> = linkedMapOf(),

    @SerialName("profile_pic") val profilePic: MatrimonyProfilePic? = null,
    @SerialName("mobile_no") val mobileNo: String? = null,
    @SerialName("whatsapp_number") val whatsappNumber: String? = null,
    @SerialName("calling_number") val callingNumber: String? = null,

    @SerialName("is_private") val isPrivate: Boolean = false,
    @SerialName("is_premium") val isPremium: Boolean = false,

    @SerialName("user_id") val userId: Int = 0,
    @SerialName("user_location") val userLocation: Int = 0,
    @SerialName("gender") val gender: Int = 0,
    @SerialName("date_of_birth") val dateOfBirth: String? = null,
    @SerialName("title") val title: String = "",
    @SerialName("button_text") val buttonText: String? = null,
    @SerialName("action") val action: String? = null,
    @SerialName("background_image_url") val backgroundImageUrl: String? = null,
    @SerialName("services_nudge") val servicesNudgeList: List<ServiceNudge> = listOf(),

    @SerialName("is_badge_info_loaded") val isBadgeInfoLoaded: Boolean = false,
    @SerialName("categories_nudge") val categoriesNudgeList: List<Classified.ClassifiedNudgeList.Category> = listOf(),

    ) : AdListable(), Parcelable {


    @Serializable
    @Parcelize
    data class ServiceNudge(
        @SerialName("id") var id: Int,
        @SerialName("name") var name: String? = null,
        @SerialName("name_eng") var titleEng: String? = null,
        @SerialName("url") var url: String? = null,
        @SerialName("post_count") var postCount: Int = 0,
        @SerialName("icon_url") var iconUrl: String? = null,
        @SerialName("new_posts_count_id") var newPostsCountId: String? = null,
        @SerialName("show_new_posts_count") var showNewPostsCount: Boolean = false,
    ) : Parcelable

    @Serializable
    @Parcelize
    data class MatrimonyProfilePic(
        @SerialName("id") var id: Int,
        @SerialName("file") var fileUrl: String? = null,
        @SerialName("thumb_url") var thumbUrl: String? = null,
        @SerialName("content_uri") var contentUri: String? = null,
        @SerialName("order") var order: Int = 0,
        @SerialName("signed_url") var signedUrlDetails: SignedUrlDetails? = null,
        @AdListableConstants.ApiCallType var _apiCallType: Int? = 0,
        var _apiCallStatus: Status?
    ) : Parcelable {
        var apiCallType: Int?
            get() = if (_apiCallType ?: 0 > 0) _apiCallType else AdListableConstants.NO_CALL
            set(value) {
                _apiCallType = value
            }

        var apiCallStatus: Status?
            get() = _apiCallStatus ?: Status.IDLE
            set(value) {
                _apiCallStatus = value
            }

        var isValidPhoto: Boolean = false

    }

    @Serializable
    @Parcelize
    data class MatrimonyContentGroup(
        @SerialName("group_icon") val groupIcon: String? = null,
        @SerialName("value") val groupContent: String? = null,
    ) : Parcelable

    @Serializable
    @Parcelize
    data class MatrimonyProfilePrimaryInfo(
        @SerialName("name") val name: String? = null,
        @SerialName("content") val content: String? = null,
    ) : Parcelable

    @Serializable
    @Parcelize
    data class SignedUrlDetails(
        @SerialName("url") val url: String? = null,
        @SerialName("fields")
        //TODO change to JsonObject
        val fields: String? = null,
        @SerialName("face_detection_url") val faceDetectionUrl: String? = null,
    ) : Parcelable

}

fun main() {
    val nativeAd = AdMobNativeAd(nativeAd = AdMobNativeAd.NativeAd(""), type = 3)
}

enum class Status {
    LOCAL, SUCCESS, ERROR, FAILURE, LOADING, IDLE, NO_INTERNET
}

object SubmissionCardType {
    const val TYPE_SUBMISSION_CARD = 1
    const val TYPE_BUY_AND_SELL_SUBMISSION_CARD = 2
    const val TYPE_REPORTER_EARNINGS = 3
    const val TYPE_REGISTERED_USER_EARNINGS = 4
}