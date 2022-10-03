package com.droid.lokalplayground.posts.views.others//package com.droid.lokalplayground.posts.views

import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.airbnb.paris.extensions.*
import com.droid.lokalplayground.*
import com.droid.lokalplayground.posts.*
import com.droid.lokalplayground.posts.views.KotlinEpoxyHolder
import com.google.android.flexbox.FlexboxLayout

@EpoxyModelClass(layout = R.layout.item_header)
abstract class HeaderView: EpoxyModelWithHolder<HeaderView.Holder>() {

    @EpoxyAttribute
    lateinit var title: String

    @EpoxyAttribute
    lateinit var style: Header.HeaderStyle

    override fun bind(holder: Holder) {

    }

    inner class Holder : EpoxyHolder() {
        lateinit var tvHeader: TextView

        override fun bindView(itemView: View) {
            tvHeader = itemView.findViewById(R.id.tvHeader)

            tvHeader.style {
                layoutWidth(style.width)
                layoutHeight(style.height)
                textColor(style.textColor.toColorHex())
                textSizeDp(style.textSize)

                text(title)

                tvHeader.setMargin(style.margin)

                when (style.textStyle) {
                    "bold" -> {
                        textStyle(Typeface.BOLD)
                    }
                    "italics" -> {
                        textStyle(Typeface.ITALIC)
                    }
                    else -> {
                        textStyle(Typeface.NORMAL)
                    }
                }

                when (style.gravity) {
                    "center" -> {
                        gravity(Gravity.CENTER)
                    }
                    "start|center" -> {
                        gravity(Gravity.CENTER or Gravity.START)
                    }
                    "start" -> {
                        gravity(Gravity.START)
                    }
                    else -> {
                        gravity(Gravity.START)
                    }
                }
            }
        }
    }
}