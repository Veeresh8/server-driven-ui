package com.droid.lokalplayground.posts.views.article

import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import coil.load
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.droid.lokalplayground.R
import com.droid.lokalplayground.posts.Article
import com.droid.lokalplayground.posts.views.KotlinEpoxyHolder

@EpoxyModelClass(layout = R.layout.item_article)
abstract class ArticleItemView: EpoxyModelWithHolder<ArticleItemView.Holder>() {

    @EpoxyAttribute
    lateinit var articleItem: Article.ArticleItem

    override fun bind(holder: Holder) {
        holder.tvTitle.text = articleItem.title
        holder.rootView.tag = articleItem
        holder.tvDescription.text = articleItem.content
        holder.ivImage.load(articleItem.imageUrl)
    }

    class Holder : KotlinEpoxyHolder() {
        val tvTitle by bind<TextView>(R.id.tvTitle)
        val tvDescription by bind<TextView>(R.id.tvDescription)
        val ivImage by bind<ImageView>(R.id.ivImage)
        val rootView by bind<CardView>(R.id.articleRoot)
    }
}