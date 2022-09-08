package com.droid.lokalplayground.posts.views.others//package com.droid.lokalplayground.posts.views
//
//import android.widget.ImageView
//import coil.load
//import com.airbnb.epoxy.EpoxyAttribute
//import com.airbnb.epoxy.EpoxyModelClass
//import com.airbnb.epoxy.EpoxyModelWithHolder
//import com.droid.lokalplayground.R
//import com.droid.lokalplayground.posts.Advert
//
//@EpoxyModelClass(layout = R.layout.item_advert)
//abstract class AdvertView: EpoxyModelWithHolder<AdvertView.Holder>() {
//
//    @EpoxyAttribute
//    lateinit var advert: Advert
//
//    override fun bind(holder: Holder) {
//        holder.ivImage.load(advert.adImage)
//    }
//
//    class Holder : KotlinEpoxyHolder() {
//        val ivImage by bind<ImageView>(R.id.ivImage)
//    }
//}