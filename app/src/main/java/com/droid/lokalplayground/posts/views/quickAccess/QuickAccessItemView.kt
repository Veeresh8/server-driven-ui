package com.droid.lokalplayground.posts.views.quickAccess

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import coil.load
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.droid.lokalplayground.R
import com.droid.lokalplayground.posts.QuickAccess
import com.droid.lokalplayground.posts.views.KotlinEpoxyHolder

@EpoxyModelClass(layout = R.layout.item_quick_acees_item)
abstract class QuickAccessItemView: EpoxyModelWithHolder<QuickAccessItemView.Holder>() {

    @EpoxyAttribute
    lateinit var quickAccessData: QuickAccess.QuickAccessData

    @EpoxyAttribute (EpoxyAttribute.Option.DoNotHash)
    lateinit var onClickListener: View.OnClickListener

    override fun bind(holder: Holder) {
        holder.ivImage.load(quickAccessData.imageUrl)
        holder.tvHeader.text = quickAccessData.title
        holder.rootView.setOnClickListener(onClickListener)
    }

    class Holder : KotlinEpoxyHolder() {
        val ivImage by bind<ImageView>(R.id.ivImage)
        val rootView by bind<ConstraintLayout>(R.id.quickAccessRoot)
        val tvHeader by bind<TextView>(R.id.tvHeader)
    }
}