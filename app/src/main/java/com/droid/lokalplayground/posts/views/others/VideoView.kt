package com.droid.lokalplayground.posts.views.others//package com.droid.lokalplayground.posts.views
//
//import android.widget.ImageView
//import coil.load
//import com.airbnb.epoxy.EpoxyAttribute
//import com.airbnb.epoxy.EpoxyModelClass
//import com.airbnb.epoxy.EpoxyModelWithHolder
//import com.droid.lokalplayground.R
//import com.droid.lokalplayground.posts.Advert
//import com.droid.lokalplayground.posts.Video
//
//@EpoxyModelClass(layout = R.layout.item_video)
//abstract class VideoView: EpoxyModelWithHolder<VideoView.Holder>() {
//
//    @EpoxyAttribute
//    lateinit var video: Video
//
//    override fun bind(holder: Holder) {
//        holder.ivThumbnail.load(video.thumbnail)
//    }
//
//    class Holder : KotlinEpoxyHolder() {
//        val ivThumbnail by bind<ImageView>(R.id.ivImage)
//    }
//}