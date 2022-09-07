package com.droid.lokalplayground.posts.views

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.CheckBox
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import com.airbnb.epoxy.*
import com.droid.lokalplayground.R
import com.droid.lokalplayground.posts.Banner
import com.droid.lokalplayground.toast

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class BannerView @JvmOverloads constructor(
     context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var recyclerBannerView: EpoxyRecyclerView
    private var bannerCountView: LinearLayout
    private var customUIController: CustomUIController

    private val checkBoxList = arrayListOf<CheckBox>()

    init {
        val view = inflate(context, R.layout.item_custom_carousel, this)
        recyclerBannerView = view.findViewById(R.id.rvBanner)
        bannerCountView = view.findViewById(R.id.viewBannerCount)

        customUIController = CustomUIController()
        recyclerBannerView.setController(customUIController)

        val epoxyVisibilityTracker = EpoxyVisibilityTracker()
        epoxyVisibilityTracker.attach(recyclerBannerView)
    }

    @ModelProp
    fun setCarouselItems(banner: Banner) {
        buildCheckBox(banner.bannerData.size)
        customUIController.addList(banner.bannerData)
    }

    private fun buildCheckBox(size: Int) {
        val params = LayoutParams(
            LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 0, 12, 0)

        repeat(size) {
            val checkBox = CheckBox(context)
            checkBox.buttonDrawable = getDrawable(context, R.drawable.custom_checkbox)
            checkBox.layoutParams = params
            checkBox.tag = it

            bannerCountView.addView(checkBox)
            checkBoxList.add(checkBox)
        }
    }

    private fun enableCheckBox(position: Int) {
        Log.d("Banner", "enableCheckBox() called with: position = $position")
        checkBoxList.forEach {
            it.isChecked = it.tag as Int == position
        }
    }

    inner class CustomUIController : AsyncEpoxyController() {
        var posts: List<Banner.BannerData> = emptyList()

        fun addList(list: List<Banner.BannerData>) {
            posts = list
            requestModelBuild()
        }

        override fun buildModels() {
            val bannerList = posts.map { bannerData ->
                BannerItemView_()
                    .id(bannerData.hashCode())
                    .bannerData(bannerData)
                    .onClickListener { model, parentView, clickedView, position ->
                        clickedView.toast("${model.bannerData.action}")
                    }
                    .onVisibilityStateChanged { model, view, visibilityState ->
                        if (visibilityState == VisibilityState.FULL_IMPRESSION_VISIBLE) {
                           enableCheckBox(posts.indexOf(model.bannerData))
                        }
                    }

            }

            CarouselModel_()
                .numViewsToShowOnScreen(1.2F)
                .id("banner carousel")
                .models(bannerList)
                .addTo(this)
        }
    }
}