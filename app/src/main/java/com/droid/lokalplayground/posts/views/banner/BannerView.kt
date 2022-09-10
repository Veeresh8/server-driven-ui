package com.droid.lokalplayground.posts.views.banner

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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
@AndroidEntryPoint
class BannerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {


    @Inject
    lateinit var coroutineScope: CoroutineScope

    private var job: Job? = null
    private var interval: Long = 0
    private var recyclerBannerView: EpoxyRecyclerView
    private var bannerCountView: LinearLayout
    private var customUIController: CustomUIController
    private var currentPosition: Int = 1
    private val checkBoxList = arrayListOf<CheckBox>()

    private lateinit var currentBanner: Banner

    init {
        val view = inflate(context, R.layout.item_banner_view, this)
        recyclerBannerView = view.findViewById(R.id.rvBanner)
        bannerCountView = view.findViewById(R.id.viewBannerCount)

        customUIController = CustomUIController()
        recyclerBannerView.setController(customUIController)

        val epoxyVisibilityTracker = EpoxyVisibilityTracker()
        epoxyVisibilityTracker.attach(recyclerBannerView)
    }

    @ModelProp
    fun setData(banner: Banner) {
        bannerCountView.removeAllViews()

        currentBanner = banner
        interval = banner.bannerMeta?.getBannerDelay() ?: 0
        buildCheckBox(banner)
        customUIController.addList(banner.bannerData)
    }

    @OnViewRecycled
    fun clear() {
        job?.cancel()
        bannerCountView.removeAllViews()
    }

    private fun buildCheckBox(banner: Banner) {
        if (banner.bannerMeta?.enableDots == false) {
            bannerCountView.removeAllViews()
            return
        }

        val params = LayoutParams(
            LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 0, 12, 0)

        repeat(banner.bannerData.size) {
            val checkBox = CheckBox(context)
            checkBox.buttonDrawable = getDrawable(context, R.drawable.custom_checkbox)
            checkBox.layoutParams = params
            checkBox.tag = it

            if (it == 0) {
                checkBox.isChecked = true
                currentPosition = 0
            }

            bannerCountView.addView(checkBox)
            checkBoxList.add(checkBox)
        }
    }

    private fun enableCheckBox(position: Int) {
        checkBoxList.forEach {
            it.isChecked = it.tag as Int == position
        }
    }

    inner class CustomUIController : AsyncEpoxyController() {
        var bannerList: List<Banner.BannerData> = emptyList()

        fun addList(list: List<Banner.BannerData>) {
            bannerList = list
            requestModelBuild()
        }

        override fun buildModels() {
            val bannerList = bannerList.map { bannerData ->

                if (currentBanner.bannerMeta?.type == "large") {
                    BannerLargeItemView_()
                        .id(bannerData.id)
                        .bannerData(bannerData)
                        .onClickListener { model, parentView, clickedView, position ->
                            clickedView.toast("${model.bannerData.action}")
                        }
                        .onVisibilityStateChanged { model, view, visibilityState ->
                            if (visibilityState == VisibilityState.FULL_IMPRESSION_VISIBLE) {
                                val position = bannerList.indexOf(model.bannerData)
                                currentPosition = position
                                Log.d("Banner Large", "CurrentPosition: $currentPosition")
                                enableCheckBox(position)
                            }
                        }
                } else {
                    BannerItemView_()
                        .id(bannerData.id)
                        .bannerData(bannerData)
                        .onClickListener { model, parentView, clickedView, position ->
                            clickedView.toast("${model.bannerData.action}")
                        }
                        .onVisibilityStateChanged { model, view, visibilityState ->
                            if (visibilityState == VisibilityState.FULL_IMPRESSION_VISIBLE) {
                                val position = bannerList.indexOf(model.bannerData)
                                currentPosition = position
                                Log.d("Banner", "CurrentPosition: $currentPosition")
                                enableCheckBox(position)
                            }
                        }
                }

            }

            CarouselModel_()
                .numViewsToShowOnScreen(1.2F)
                .id("banner carousel")
                .models(bannerList)
                .onBind { model, view, position ->
                    if (interval > 0) {
                        job?.cancel()
                        job = coroutineScope.launch(Dispatchers.Main.immediate) {
                            while (isActive) {
                                delay(interval)
                                val nextPosition = getNextPosition(bannerList.size)
                                Log.d("Banner", "NextPosition: $nextPosition")
                                view?.smoothScrollToPosition(nextPosition)
                            }
                        }
                    }
                }
                .addTo(this)
        }
    }

    private fun getNextPosition(total: Int): Int {
        if (currentPosition == total - 1)
            currentPosition = 0
        else
            currentPosition += 1

        return currentPosition
    }

    override fun isSaveEnabled(): Boolean {
        return true
    }
}