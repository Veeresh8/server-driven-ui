package com.droid.lokalplayground.posts.views.others//package com.droid.lokalplayground.posts.views

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.airbnb.paris.extensions.*
import com.droid.lokalplayground.R
import com.droid.lokalplayground.posts.ToolbarStyle
import com.droid.lokalplayground.toColorHex

@EpoxyModelClass(layout = R.layout.item_toolbar)
abstract class ToolbarView : EpoxyModelWithHolder<ToolbarView.Holder>() {

    @EpoxyAttribute
    lateinit var title: String

    @EpoxyAttribute
    lateinit var toolbarStyle: ToolbarStyle

    override fun bind(holder: Holder) {
        holder.tvTitle.text = title
    }

    inner class Holder : EpoxyHolder() {

        lateinit var tvTitle: TextView
        lateinit var root: ConstraintLayout
        @SuppressLint("ResourceAsColor")
        override fun bindView(itemView: View) {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            root = itemView.findViewById(R.id.root)

            val toolbarStyle = this@ToolbarView.toolbarStyle

            tvTitle.style {
                textColor(toolbarStyle.textColor.toColorHex())
                textSizeDp(toolbarStyle.textSize)
                gravity(Gravity.CENTER)

                for(index in toolbarStyle.margin.indices) {
                    when (index) {
                        0 -> {
                            layoutMarginStartDp(toolbarStyle.margin[index])
                        }
                        1 -> {
                            layoutMarginTopDp(toolbarStyle.margin[index])
                        }
                        2 -> {
                            layoutMarginRightDp(toolbarStyle.margin[index])
                        }
                        3 -> {
                            layoutMarginEndDp(toolbarStyle.margin[index])
                        }
                    }
                }

                for(index in toolbarStyle.padding.indices) {
                    when (index) {
                        0 -> {
                            paddingStartDp(toolbarStyle.padding[index])
                        }
                        1 -> {
                            paddingTopDp(toolbarStyle.padding[index])
                        }
                        2 -> {
                            paddingRightDp(toolbarStyle.padding[index])
                        }
                        3 -> {
                            paddingBottomDp(toolbarStyle.padding[index])
                        }
                    }
                }
            }

            root.style {
                layoutWidth(toolbarStyle.width)
                layoutHeightDp(toolbarStyle.height)
                root.setBackgroundColor(toolbarStyle.backgroundColor.toColorHex())
            }
        }
    }
}