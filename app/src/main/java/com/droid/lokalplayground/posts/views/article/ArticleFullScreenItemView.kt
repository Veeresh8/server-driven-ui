package com.droid.lokalplayground.posts.views.article//package com.droid.lokalplayground.posts.views

import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import coil.load
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.droid.lokalplayground.R
import com.droid.lokalplayground.posts.ArticleFullScreen
import com.droid.lokalplayground.posts.views.KotlinEpoxyHolder

@EpoxyModelClass(layout = R.layout.item_article_fullscreen)
abstract class ArticleFullScreenItemView: EpoxyModelWithHolder<ArticleFullScreenItemView.Holder>() {

    @EpoxyAttribute
    lateinit var articleItem: ArticleFullScreen.ArticleItem

    override fun bind(holder: Holder) {
        holder.tvTitle.text = articleItem.title
        if (articleItem.isLastItem)
            holder.rootView.tag = null
        else
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