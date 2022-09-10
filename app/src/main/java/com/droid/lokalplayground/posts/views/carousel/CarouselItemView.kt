package com.droid.lokalplayground.posts.views.carousel

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import coil.load
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.droid.lokalplayground.R
import com.droid.lokalplayground.gone
import com.droid.lokalplayground.posts.Carousel
import com.droid.lokalplayground.posts.QuickAccess
import com.droid.lokalplayground.posts.views.KotlinEpoxyHolder
import com.droid.lokalplayground.visible

@EpoxyModelClass(layout = R.layout.item_carousel_item)
abstract class CarouselItemView: EpoxyModelWithHolder<CarouselItemView.Holder>() {

    @EpoxyAttribute
    lateinit var carouselData: Carousel.CarouselData

    @EpoxyAttribute (EpoxyAttribute.Option.DoNotHash)
    lateinit var onClickListener: View.OnClickListener

    override fun bind(holder: Holder) {
        holder.ivImage.load(carouselData.imageUrl)

        if (!carouselData.content.isNullOrEmpty()) {
            holder.tvContent.visible()
            holder.tvContent.text = carouselData.content
        } else {
            holder.tvContent.gone()
        }

        holder.rootView.setOnClickListener(onClickListener)
    }

    class Holder : KotlinEpoxyHolder() {
        val ivImage by bind<ImageView>(R.id.ivImage)
        val rootView by bind<ConstraintLayout>(R.id.carouselRoot)
        val tvContent by bind<TextView>(R.id.tvContent)
    }

    override fun shouldSaveViewState(): Boolean {
        return true
    }
}