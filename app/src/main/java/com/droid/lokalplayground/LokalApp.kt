package com.droid.lokalplayground

import android.app.Application
import android.content.Context
import android.os.Build
import android.view.Gravity
import androidx.recyclerview.widget.SnapHelper
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.airbnb.epoxy.Carousel
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LokalApp: Application() {

    lateinit var imageLoader: ImageLoader

    companion object {
        lateinit var instance: LokalApp
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        imageLoader = ImageLoader.Builder(this)
            .components {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()

        Carousel.setDefaultGlobalSnapHelperFactory(object : Carousel.SnapHelperFactory() {
            override fun buildSnapHelper(context: Context?): SnapHelper {
                return GravitySnapHelper(Gravity.CENTER)
            }
        })
    }
}