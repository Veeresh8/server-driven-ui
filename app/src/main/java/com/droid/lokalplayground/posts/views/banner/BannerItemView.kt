package com.droid.lokalplayground.posts.views.banner

import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import coil.load
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.airbnb.paris.extensions.*
import com.droid.lokalplayground.R
import com.droid.lokalplayground.posts.BannerData
import com.droid.lokalplayground.posts.BannerMeta
import com.droid.lokalplayground.posts.BannerStyle
import com.droid.lokalplayground.setMargin
import com.droid.lokalplayground.setScaleType

@EpoxyModelClass(layout = R.layout.item_banner)
abstract class BannerItemView : EpoxyModelWithHolder<BannerItemView.Holder>() {

    @EpoxyAttribute
    lateinit var bannerData: BannerData

    @EpoxyAttribute
    lateinit var bannerMeta: BannerMeta

    @EpoxyAttribute
    lateinit var bannerStyle: BannerStyle

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var onClickListener: View.OnClickListener


    override fun bind(holder: Holder) {
        holder.ivImage.load(bannerData.imageUrl)
        holder.rootView.setOnClickListener(onClickListener)
    }

    inner class Holder : EpoxyHolder() {

        lateinit var ivImage: ImageView
        lateinit var rootView: ConstraintLayout

        override fun bindView(itemView: View) {
            ivImage = itemView.findViewById(R.id.ivImage)
            rootView = itemView.findViewById(R.id.bannerRoot)

            rootView.style {
                layoutWidthDp(bannerStyle.width)
                layoutHeightDp(bannerStyle.height)
            }

            ivImage.setScaleType(bannerStyle.scaleType)
            rootView.setMargin(bannerStyle.margin)
        }
    }

    override fun shouldSaveViewState(): Boolean {
        return true
    }
}