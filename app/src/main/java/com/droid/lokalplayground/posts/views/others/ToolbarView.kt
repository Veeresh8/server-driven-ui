package com.droid.lokalplayground.posts.views.others//package com.droid.lokalplayground.posts.views

import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.droid.lokalplayground.R
import com.droid.lokalplayground.posts.views.KotlinEpoxyHolder

@EpoxyModelClass(layout = R.layout.item_toolbar)
abstract class ToolbarView: EpoxyModelWithHolder<ToolbarView.Holder>() {

    @EpoxyAttribute
    lateinit var title: String

    override fun bind(holder: Holder) {
        holder.tvTitle.text = title
    }

    class Holder : KotlinEpoxyHolder() {
        val tvTitle by bind<TextView>(R.id.tvTitle)
    }
}