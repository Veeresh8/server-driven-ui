package com.droid.lokalplayground.posts.views.carousel

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.airbnb.epoxy.*
import com.droid.lokalplayground.R
import com.droid.lokalplayground.posts.Carousel
import com.droid.lokalplayground.posts.views.banner.BannerItemView_
import com.droid.lokalplayground.toast
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class CarouselView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var recyclerBannerView: EpoxyRecyclerView
    private var tvTitle: TextView
    private var tvMore: TextView
    private var ivImage: ImageView
    private var customUIController: CustomUIController

    init {
        val view = inflate(context, R.layout.item_carousel, this)
        recyclerBannerView = view.findViewById(R.id.rvCarousel)
        tvTitle = view.findViewById(R.id.tvHeader)
        tvMore = view.findViewById(R.id.tvMore)
        ivImage = view.findViewById(R.id.ivImage)

        customUIController = CustomUIController()
        recyclerBannerView.setController(customUIController)
    }

    @ModelProp
    fun setData(carousel: Carousel) {
        tvTitle.text = carousel.carouselMeta?.title
        tvMore.text = carousel.carouselMeta?.subTitle
        ivImage.load(carousel.carouselMeta?.icon)

        customUIController.addList(carousel.carouselData)
    }

    inner class CustomUIController : AsyncEpoxyController() {
        var carouselList: List<Carousel.CarouselData> = emptyList()

        fun addList(list: List<Carousel.CarouselData>) {
            carouselList = list
            requestModelBuild()
        }

        override fun buildModels() {
            val carouselList = carouselList.map { carousel ->
                CarouselItemView_()
                    .id(carousel.id)
                    .carouselData(carousel)
                    .onClickListener { model, parentView, clickedView, position ->
                        clickedView.toast("${model.carouselData.action}")
                    }
            }

            CarouselModel_()
                .numViewsToShowOnScreen(1.2F)
                .id("carousel_list")
                .models(carouselList)
                .addTo(this)
        }
    }

    override fun isSaveEnabled(): Boolean {
        return true
    }
}