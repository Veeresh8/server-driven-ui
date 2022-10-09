package com.droid.lokalplayground.posts

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.os.Build.VERSION.SDK_INT
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.load
import coil.request.ImageRequest
import com.airbnb.paris.extensions.*
import com.droid.lokalplayground.*
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily

fun buildTextView(context: Context, lokalTextView: LokalTextView): TextView {
    val textView = TextView(context)
    textView.style {
        layoutWidth(lokalTextView.width)
        layoutHeight(lokalTextView.height)
        textSizeDp(lokalTextView.textSize)
        textColor(lokalTextView.textColor.toColorHex())
        text(lokalTextView.text)

        lokalTextView.drawablePadding?.let {
            drawablePaddingDp(lokalTextView.drawablePadding)
        }

        lokalTextView.url?.let {
            textView.context?.let {
                val loader = ImageLoader(it)
                val request = ImageRequest.Builder(it)
                    .data(lokalTextView.url)
                    .target { result ->
                        textView.setCompoundDrawablesWithIntrinsicBounds(result, null, null, null)
                    }
                    .build()
                loader.enqueue(request)
            }
        }


        when (lokalTextView.textStyle) {
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

        when (lokalTextView.gravity) {
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

        textView.setPadding(lokalTextView.padding)
        textView.setMargin(lokalTextView.margin)
    }

    return textView
}

fun buildImageView(context: Context, lokalImageView: LokalImageView): ShapeableImageView {
    val imageView = ShapeableImageView(context)
    imageView.style {
        layoutWidth(lokalImageView.width)
        layoutHeight(lokalImageView.height)

        imageView.setScaleType(lokalImageView.scaleType)

        imageView.shapeAppearanceModel = imageView.shapeAppearanceModel
            .toBuilder()
            .setAllCorners(CornerFamily.ROUNDED, lokalImageView.cornerRadius.toFloat())
            .build()

        imageView.setMargin(lokalImageView.margin)
    }

    imageView.load(lokalImageView.url, LokalApp.instance.imageLoader) {
        crossfade(true)
    }

    return imageView
}