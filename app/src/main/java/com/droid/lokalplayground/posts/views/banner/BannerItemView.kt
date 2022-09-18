package com.droid.lokalplayground.posts.views.banner

import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import coil.load
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.airbnb.paris.ExtendableStyleBuilder
import com.airbnb.paris.extensions.layoutHeightDp
import com.airbnb.paris.extensions.layoutWidthDp
import com.airbnb.paris.extensions.scaleType
import com.airbnb.paris.extensions.style
import com.droid.lokalplayground.R
import com.droid.lokalplayground.posts.Banner

@EpoxyModelClass(layout = R.layout.item_banner)
abstract class BannerItemView : EpoxyModelWithHolder<BannerItemView.Holder>() {

    @EpoxyAttribute
    lateinit var bannerData: Banner.BannerData

    @EpoxyAttribute
    lateinit var bannerMeta: Banner.BannerMeta

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

            val bannerMeta = this@BannerItemView.bannerMeta

            rootView.style {
                layoutWidthDp(bannerMeta.bannerStyle.width)
                layoutHeightDp(bannerMeta.bannerStyle.height)
            }

            ivImage.style {
                scaleType(bannerMeta.bannerStyle.scaleType)
            }
        }
    }

    override fun shouldSaveViewState(): Boolean {
        return true
    }
}