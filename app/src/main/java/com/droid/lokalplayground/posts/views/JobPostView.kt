package com.droid.lokalplayground.posts.views

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.droid.lokalplayground.R
import com.droid.lokalplayground.posts.Article
import com.droid.lokalplayground.posts.JobPost

@EpoxyModelClass(layout = R.layout.item_job)
abstract class JobPostView: EpoxyModelWithHolder<JobPostView.Holder>() {

    @EpoxyAttribute
    lateinit var jobPost: JobPost

    @EpoxyAttribute (EpoxyAttribute.Option.DoNotHash)
    lateinit var onClickListener: View.OnClickListener

    override fun bind(holder: Holder) {
        holder.ivImage.load(jobPost.image)
        holder.btnApply.setOnClickListener(onClickListener)
    }

    class Holder : KotlinEpoxyHolder() {
        val ivImage by bind<ImageView>(R.id.ivImage)
        val btnApply by bind<Button>(R.id.btnApply)

    }
}