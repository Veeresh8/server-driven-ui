package com.droid.lokalplayground.posts

import android.util.Log
import android.view.Gravity
import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.EpoxyRecyclerView
import com.airbnb.epoxy.VisibilityState
import com.airbnb.epoxy.stickyheader.StickyHeaderCallbacks
import com.droid.lokalplayground.posts.views.article.articleFullScreenItemView
import com.droid.lokalplayground.posts.views.article.articleView
import com.droid.lokalplayground.posts.views.banner.bannerView
import com.droid.lokalplayground.posts.views.carousel.carouselView
import com.droid.lokalplayground.posts.views.form.formView
import com.droid.lokalplayground.posts.views.notification.quickNotificationView
import com.droid.lokalplayground.posts.views.others.headerView
import com.droid.lokalplayground.posts.views.others.toolbarView
import com.droid.lokalplayground.posts.views.quickAccess.quickAccessView
import com.droid.lokalplayground.toast
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import javax.inject.Inject

class PostsUIController @Inject constructor() : AsyncEpoxyController() {

    private lateinit var currentRecyclerView: EpoxyRecyclerView

    private var hasSetFling = false

    var posts: List<Post> = emptyList()

    private val snapHelper = GravitySnapHelper(Gravity.CENTER).apply {
        maxFlingSizeFraction = 1.0f
        scrollMsPerInch = 20f
    }

    fun addPosts(incomingPosts: List<Post>) {
        posts = incomingPosts
        requestModelBuild()
    }

    fun attachRecyclerView(epoxyRecyclerView: EpoxyRecyclerView) {
        currentRecyclerView = epoxyRecyclerView
    }

    override fun buildModels() {
        posts.forEach {
            when (it) {
                is Toolbar -> {
                    toolbarView {
                        id("toolbar_view")
                        title(it.toolbarMeta?.title)
                    }
                }
                is Banner -> {
                    bannerView {
                        id("banner_view")
                        data(it)
                    }
                }
                is QuickAccess -> {
                    quickAccessView {
                        id("quick_access_view")
                        data(it)
                    }
                }
                is Carousel -> {
                    carouselView {
                        id("carousel_view")
                        data(it)
                    }
                }
                is QuickNotification -> {
                    quickNotificationView {
                        id(it.id)
                        quickNotificationMeta(it.quickNotificationMeta).onClickListener { model, parentView, clickedView, position ->
                            clickedView.toast("${model.quickNotificationMeta.action}")
                        }
                    }
                }
                is Form -> {
                    formView {
                        id("form_view")
                        data(it)
                    }
                }
                is Article -> {
                    articleView {
                        id("article_view")
                        data(it)
                    }
                }
                is ArticleFullScreen -> {

                    headerView {
                        id("header")
                        title(it.articleMeta?.title)
                    }

                    it.articleItem.forEachIndexed { index, article ->
                        articleFullScreenItemView {
                            id(article.id)
                            articleItem(
                                if (index == it.articleItem.size - 1) article.copy(
                                    isLastItem = true
                                ) else article
                            ).onVisibilityStateChanged { model, view, visibilityState ->
                                val postsUIController = this@PostsUIController

                                if (!postsUIController.hasSetFling && visibilityState == VisibilityState.FULL_IMPRESSION_VISIBLE) {
                                    postsUIController.hasSetFling = true
                                    Log.d("Snap", "Setting Snap")
                                    postsUIController.snapHelper.attachToRecyclerView(
                                        postsUIController.currentRecyclerView
                                    )

                                    postsUIController.snapHelper.setSnapListener {
                                        val snapView =
                                            postsUIController.currentRecyclerView.layoutManager?.let { it1 ->
                                                postsUIController.snapHelper.findSnapView(
                                                    it1
                                                )
                                            }

                                        val articleItem =
                                            snapView?.tag as ArticleFullScreen.ArticleItem?

                                        Log.d("Snap", "Tag: $articleItem")

                                        if (articleItem == null) {
                                            Log.d("Snap", "Reset")
                                            postsUIController.snapHelper.attachToRecyclerView(
                                                null
                                            )
                                            postsUIController.hasSetFling = false
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else -> {
                    println("No matching type")
                }
            }
        }
    }
}