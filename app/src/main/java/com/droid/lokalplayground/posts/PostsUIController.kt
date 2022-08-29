package com.droid.lokalplayground.posts

import android.view.View
import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.VisibilityState
import com.airbnb.epoxy.carousel
import com.droid.lokalplayground.posts.views.*
import javax.inject.Inject


class PostsUIController @Inject constructor() : AsyncEpoxyController() {

    var posts: List<Post> = emptyList()

    fun addPosts(incomingPosts: List<Post>) {
        posts = incomingPosts
        requestModelBuild()
    }

    override fun buildModels() {
        buildVerticalList()
        buildFilteredList()
        buildCustomRecycler()
    }

    private fun buildCustomRecycler() {
        customJobView {
            id("custom job view")
            headerText("Jobs near you")
            buttonText("Open Now")
            carouselItems(this@PostsUIController.posts)
        }
    }

    private fun buildFilteredList() {
        posts.filter { it.itemType == ItemType.ARTICLE }.forEach { article ->
            articleView {
                id(article.id)
                article(article as Article)
            }
        }
    }

    private fun buildVerticalList() {
        posts.forEachIndexed { index, it ->
            when (it) {
                is Article -> {
                    articleView {
                        id(it.id)
                        article(it)
                            .onVisibilityStateChanged { model, view, visibilityState ->
                                when (visibilityState) {
                                    VisibilityState.VISIBLE -> {
                                        println("VEERESH: ${model.article.id}: VISIBLE")
                                    }
                                    VisibilityState.INVISIBLE -> {
                                        println("VEERESH: ${model.article.id}: INVISIBLE")
                                    }
                                    VisibilityState.FOCUSED_VISIBLE -> {
                                        println("VEERESH: ${model.article.id}: FOCUSED_VISIBLE")
                                    }
                                    VisibilityState.FULL_IMPRESSION_VISIBLE -> {
                                        println("VEERESH: ${model.article.id}: FULL_IMPRESSION_VISIBLE")
                                    }
                                    VisibilityState.PARTIAL_IMPRESSION_VISIBLE -> {
                                        println("VEERESH: ${model.article.id}: PARTIAL_IMPRESSION_VISIBLE")
                                    }
                                    VisibilityState.PARTIAL_IMPRESSION_INVISIBLE -> {
                                        println("VEERESH: ${model.article.id}: PARTIAL_IMPRESSION_INVISIBLE")
                                    }
                                }
                            }
                    }
                }
                is Video -> {
                    val videoList = posts.filter { it.itemType == ItemType.VIDEO }.map { videoPost ->
                        VideoView_()
                            .id(videoPost.id)
                            .video(videoPost as Video)
                    }

                    CarouselModel_()
                        .numViewsToShowOnScreen(1.2F)
                        .id("video carousel")
                        .models(videoList)
                        .addTo(this)
                }
                is Advert -> {
                    advertView {
                        id(it.id)
                        advert(it)
                    }
                }
            }
        }
    }
}