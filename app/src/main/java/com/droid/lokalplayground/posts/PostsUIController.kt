package com.droid.lokalplayground.posts

import com.airbnb.epoxy.AsyncEpoxyController
import com.droid.lokalplayground.posts.views.bannerView
import javax.inject.Inject


class PostsUIController @Inject constructor() : AsyncEpoxyController() {

    var posts: List<Post> = emptyList()

    fun addPosts(incomingPosts: List<Post>) {
        posts = incomingPosts
        requestModelBuild()
    }

    override fun buildModels() {
        posts.forEach {
            when (it) {
                is Banner -> {
                    bannerView {
                        id("banner view")
                        carouselItems(it)
                    }
                }
                else -> {
                    println("No matching type")
                }
            }
        }
    }
}