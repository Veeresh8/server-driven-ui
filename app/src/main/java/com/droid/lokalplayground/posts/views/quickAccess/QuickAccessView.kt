package com.droid.lokalplayground.posts.views.quickAccess

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView
import com.airbnb.epoxy.*
import com.droid.lokalplayground.R
import com.droid.lokalplayground.posts.QuickAccess
import com.droid.lokalplayground.toast
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent


@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class QuickAccessView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var recyclerBannerView: EpoxyRecyclerView
    private var tvTitle: TextView
    private var customUIController: CustomUIController

    init {
        val view = inflate(context, R.layout.item_quick_access, this)
        recyclerBannerView = view.findViewById(R.id.rvQuickAccess)
        tvTitle = view.findViewById(R.id.tvHeader)

        customUIController = CustomUIController()
        recyclerBannerView.setController(customUIController)
    }

    @ModelProp
    fun setData(quickAccess: QuickAccess) {
        initGrid(quickAccess.quickAccessMeta?.layoutJustify)
        tvTitle.text = quickAccess.quickAccessMeta?.title
        customUIController.addList(quickAccess.quickAccessData)
    }

    private fun initGrid(layoutJustify: String?) {
        val layoutManager = FlexboxLayoutManager(context)
        layoutManager.flexDirection = FlexDirection.ROW
        when (layoutJustify) {
            "start" -> {
                layoutManager.justifyContent = JustifyContent.SPACE_EVENLY
            }
            "center" -> {
                layoutManager.justifyContent = JustifyContent.CENTER
            }
            else -> {
                layoutManager.justifyContent = JustifyContent.FLEX_START
            }
        }
        recyclerBannerView.layoutManager = layoutManager
    }

    inner class CustomUIController : AsyncEpoxyController() {
        var quickAccessList: List<QuickAccess.QuickAccessData> = emptyList()

        fun addList(list: List<QuickAccess.QuickAccessData>) {
            quickAccessList = list
            requestModelBuild()
        }

        override fun buildModels() {
            quickAccessList.forEach { it ->
                quickAccessItemView {
                    id(it.id)
                    quickAccessData(it).onClickListener { model, parentView, clickedView, position ->
                        clickedView.toast("${model.quickAccessData.action}")
                    }
                }
            }
        }
    }
}