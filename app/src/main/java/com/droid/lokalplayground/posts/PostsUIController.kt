package com.droid.lokalplayground.posts

import android.view.Gravity
import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.EpoxyRecyclerView
import com.droid.lokalplayground.posts.views.banner.bannerView
import com.droid.lokalplayground.posts.views.notification.quickNotificationView
import com.droid.lokalplayground.posts.views.others.headerView
import com.droid.lokalplayground.toast
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import javax.inject.Inject

class PostsUIController @Inject constructor() : AsyncEpoxyController() {

    private lateinit var currentRecyclerView: EpoxyRecyclerView

    private var hasSetFling = false

    var items: List<LokalViewType> = emptyList()

    private val snapHelper = GravitySnapHelper(Gravity.CENTER).apply {
        maxFlingSizeFraction = 1.0f
        scrollMsPerInch = 20f
    }

    fun addPosts(incomingPosts: List<LokalViewType>) {
        items = incomingPosts
        requestModelBuild()
    }

    fun attachRecyclerView(epoxyRecyclerView: EpoxyRecyclerView) {
        currentRecyclerView = epoxyRecyclerView
    }

    override fun buildModels() {
        items.forEach { itemViewType ->
            when (itemViewType) {
                is BannerType -> {
                    bannerView {
                        id("banner_view: ${itemViewType.id}")
                        data(itemViewType)
                    }
                }
                is CardType -> {
                    itemViewType.children.forEachIndexed { index, it ->
                        when (it) {
                            is Header -> {
                                headerView {
                                    id("header_view: ${it.id}")
                                    title(it.title)
                                    style(it.style)
                                }
                            }

                            is QuickNotification -> {
                                val quickNotificationMeta = QuickNotification.QuickNotificationMeta(isLastItem = index == itemViewType.children.size - 1)
                                quickNotificationView {
                                        id(it.id)
                                        quickNotificationMeta(quickNotificationMeta)
                                        children(it.children)
                                        style(it.style)
                                        .onClickListener { model, parentView, clickedView, position ->
                                            clickedView.toast(
                                                ""
                                            )
                                        }
                                        .onVisibilityStateChanged { model, view, visibilityState ->

                                        }
                                }
                            }
                            else -> {
                                println("No matching type")
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