package com.droid.lokalplayground

import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.airbnb.paris.extensions.*

fun View.toast(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
}

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun String.toColorHex(): Int {
    return Color.parseColor(this)
}

fun CardView.setRadiusInDp(value: Float) {
    radius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.resources.displayMetrics)
}

fun CardView.setCardBackgroundColor(value: String) {
    setCardBackgroundColor(value.toColorHex())
}

fun View.setMargin(marginList: List<Int>) {
    this.style {
        for(index in marginList.indices) {
            when (index) {
                0 -> {
                    layoutMarginStartDp(marginList[index])
                }
                1 -> {
                    layoutMarginTopDp(marginList[index])
                }
                2 -> {
                    layoutMarginEndDp(marginList[index])
                }
                3 -> {
                    layoutMarginBottomDp(marginList[index])
                }
            }
        }
    }
}

fun ImageView.setScaleType(value: String) {
    scaleType = when (value) {
        "centerCrop" -> {
            ImageView.ScaleType.CENTER_CROP
        }
        "centerInside" -> {
            ImageView.ScaleType.CENTER_INSIDE
        }
        "center" -> {
            ImageView.ScaleType.CENTER
        }
        "fitXY" -> {
            ImageView.ScaleType.FIT_XY
        }
        else -> {
            ImageView.ScaleType.CENTER_CROP
        }
    }
}

fun View.setPadding(paddingList: List<Int>) {
    this.style {
        for(index in paddingList.indices) {
            when (index) {
                0 -> {
                    paddingStartDp(paddingList[index])
                }
                1 -> {
                    paddingTopDp(paddingList[index])
                }
                2 -> {
                    paddingEndDp(paddingList[index])
                }
                3 -> {
                    paddingBottomDp(paddingList[index])
                }
            }
        }
    }
}