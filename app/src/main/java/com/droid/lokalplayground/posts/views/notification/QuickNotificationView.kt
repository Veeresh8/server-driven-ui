package com.droid.lokalplayground.posts.views.notification//package com.droid.lokalplayground.posts.views

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.droid.lokalplayground.R
import com.droid.lokalplayground.posts.QuickNotification
import com.droid.lokalplayground.posts.views.KotlinEpoxyHolder

@EpoxyModelClass(layout = R.layout.item_notification)
abstract class QuickNotificationView: EpoxyModelWithHolder<QuickNotificationView.Holder>() {

    @EpoxyAttribute
    lateinit var quickNotificationMeta: QuickNotification.QuickNotificationMeta

    @EpoxyAttribute (EpoxyAttribute.Option.DoNotHash)
    lateinit var onClickListener: View.OnClickListener

    override fun bind(holder: Holder) {
        holder.tvHeader.text = quickNotificationMeta.title
        holder.tvSubHeader.text = quickNotificationMeta.subTitle
        holder.ivImage.load(quickNotificationMeta.icon)
        holder.btnAction.text = quickNotificationMeta.buttonText

        holder.btnAction.setOnClickListener(onClickListener)
    }

    class Holder : KotlinEpoxyHolder() {
        val tvHeader by bind<TextView>(R.id.tvHeader)
        val tvSubHeader by bind<TextView>(R.id.tvSubHeader)
        val ivImage by bind<ImageView>(R.id.ivImage)
        val btnAction by bind<Button>(R.id.btnAction)
    }
}