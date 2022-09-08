package com.droid.lokalplayground.posts.views.banner

import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import coil.load
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.droid.lokalplayground.R
import com.droid.lokalplayground.posts.Banner
import com.droid.lokalplayground.posts.views.KotlinEpoxyHolder

@EpoxyModelClass(layout = R.layout.item_banner)
abstract class BannerItemView: EpoxyModelWithHolder<BannerItemView.Holder>() {

    @EpoxyAttribute
    lateinit var bannerData: Banner.BannerData

    @EpoxyAttribute (EpoxyAttribute.Option.DoNotHash)
    lateinit var onClickListener: View.OnClickListener

    override fun bind(holder: Holder) {
        holder.ivImage.load(bannerData.imageUrl)
        holder.rootView.setOnClickListener(onClickListener)
    }

    class Holder : KotlinEpoxyHolder() {
        val ivImage by bind<ImageView>(R.id.ivImage)
        val rootView by bind<ConstraintLayout>(R.id.bannerRoot)
    }
}