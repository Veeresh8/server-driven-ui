package com.droid.lokalplayground.posts

import com.airbnb.epoxy.AsyncEpoxyController
import com.droid.lokalplayground.posts.views.banner.bannerView
import com.droid.lokalplayground.posts.views.carousel.carouselView
import com.droid.lokalplayground.posts.views.form.formView
import com.droid.lokalplayground.posts.views.notification.quickNotificationView
import com.droid.lokalplayground.posts.views.quickAccess.quickAccessView
import com.droid.lokalplayground.toast
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
                else -> {
                    println("No matching type")
                }
            }
        }
    }
}