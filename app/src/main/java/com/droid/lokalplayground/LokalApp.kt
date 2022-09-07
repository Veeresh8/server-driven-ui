package com.droid.lokalplayground

import android.app.Application
import android.content.Context
import android.view.Gravity
import androidx.recyclerview.widget.SnapHelper
import com.airbnb.epoxy.Carousel
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LokalApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Carousel.setDefaultGlobalSnapHelperFactory(object : Carousel.SnapHelperFactory() {
            override fun buildSnapHelper(context: Context?): SnapHelper {
                return GravitySnapHelper(Gravity.CENTER)
            }
        })
    }
}