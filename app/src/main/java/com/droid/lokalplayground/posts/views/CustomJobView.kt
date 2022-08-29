package com.droid.lokalplayground.posts.views

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import com.airbnb.epoxy.*
import com.droid.lokalplayground.R
import com.droid.lokalplayground.posts.ItemType
import com.droid.lokalplayground.posts.JobPost
import com.droid.lokalplayground.posts.Post

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class CustomJobView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    lateinit var tvHeader: TextView
    lateinit var btnOpen: Button
    lateinit var recyclerView: EpoxyRecyclerView
    lateinit var customUIController: CustomUIController

    init {
        val view = inflate(context, R.layout.item_custom_carousel, this)
        tvHeader = view.findViewById(R.id.tvHeader)
        btnOpen = view.findViewById(R.id.btnOpen)
        recyclerView = view.findViewById(R.id.rvCustom)

        customUIController = CustomUIController()
        recyclerView.setController(customUIController)
    }

    @ModelProp
    fun setCarouselItems(items: List<Post>) {
        customUIController.addList(items)
    }

    @TextProp
    fun setButtonText(input: CharSequence) {
        btnOpen.text = input
    }

    @TextProp
    fun setHeaderText(input: CharSequence) {
        tvHeader.text = input
    }

    inner class CustomUIController : AsyncEpoxyController() {
        var posts: List<Post> = emptyList()

        fun addList(list: List<Post>) {
            posts = list
            requestModelBuild()
        }

        override fun buildModels() {
            val jobList = posts.filter { it.itemType == ItemType.JOBPOST }.map { jobPost ->
                JobPostView_()
                    .id(jobPost.id)
                    .jobPost(jobPost as JobPost)
                    .onClickListener { model, parentView, clickedView, position ->
                        println("$position || ${model.jobPost.id}")
                    }
            }

            CarouselModel_()
                .numViewsToShowOnScreen(1.5F)
                .id("job carousel")
                .models(jobList)
                .addTo(this)
        }
    }
}