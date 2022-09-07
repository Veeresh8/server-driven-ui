//package com.droid.lokalplayground.posts.views
//
//import android.widget.ImageView
//import android.widget.TextView
//import coil.load
//import com.airbnb.epoxy.EpoxyAttribute
//import com.airbnb.epoxy.EpoxyModelClass
//import com.airbnb.epoxy.EpoxyModelWithHolder
//import com.droid.lokalplayground.R
//import com.droid.lokalplayground.posts.Article
//
//@EpoxyModelClass(layout = R.layout.item_article)
//abstract class ArticleView: EpoxyModelWithHolder<ArticleView.Holder>() {
//
//    @EpoxyAttribute
//    lateinit var article: Article
//
//    override fun bind(holder: Holder) {
//        holder.tvTitle.text = article.title
//        holder.tvDescription.text = article.description
//        holder.ivImage.load(article.imageURL)
//    }
//
//    class Holder : KotlinEpoxyHolder() {
//        val tvTitle by bind<TextView>(R.id.tvTitle)
//        val tvDescription by bind<TextView>(R.id.tvDescription)
//        val ivImage by bind<ImageView>(R.id.ivImage)
//    }
//}