package com.droid.lokalplayground.posts.views.notification//package com.droid.lokalplayground.posts.views

import android.view.View
import androidx.cardview.widget.CardView
import androidx.core.view.children
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.airbnb.paris.extensions.layoutHeight
import com.airbnb.paris.extensions.layoutWidth
import com.airbnb.paris.extensions.minHeightDp
import com.airbnb.paris.extensions.style
import com.droid.lokalplayground.R
import com.droid.lokalplayground.posts.*
import com.droid.lokalplayground.setCardBackgroundColor
import com.droid.lokalplayground.setMargin
import com.droid.lokalplayground.setRadiusInDp
import com.google.android.flexbox.FlexboxLayout

@EpoxyModelClass(layout = R.layout.item_notification)
abstract class QuickNotificationView : EpoxyModelWithHolder<QuickNotificationView.Holder>() {

    @EpoxyAttribute
    lateinit var quickNotificationMeta: QuickNotification.QuickNotificationMeta

    @EpoxyAttribute
    lateinit var style: QuickNotification.QuickNotificationStyle

    @EpoxyAttribute
    lateinit var children: List<LokalView>

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var onClickListener: View.OnClickListener

    override fun unbind(holder: Holder) {
        super.unbind(holder)
        holder.container.removeAllViews()
    }

    override fun bind(holder: Holder) {
        with(holder) {
            children.forEach {
                when (it) {
                    is LokalTextView -> {
                        val textView = buildTextView(root.context, it)
                        container.addView(textView)
                    }
                    is LokalImageView -> {
                        val imageView = buildImageView(root.context, it)
                        container.addView(imageView)
                    }
                    else -> {

                    }
                }
            }
        }

        if (quickNotificationMeta.isLastItem) {
            holder.root.tag = null
        } else {
            holder.root.tag = quickNotificationMeta
        }
    }

    inner class Holder : EpoxyHolder() {
        lateinit var root: CardView
        lateinit var container: FlexboxLayout

        override fun bindView(itemView: View) {
            root = itemView.findViewById(R.id.root)
            container = itemView.findViewById(R.id.container)

            root.style {
                layoutWidth(style.width)
                layoutHeight(style.height)
                minHeightDp(style.minHeight)
                root.setMargin(style.margin)
            }

            root.setCardBackgroundColor(style.backgroundColor.toString())
            root.setRadiusInDp(style.cornerRadius.toFloat())
        }
    }
}