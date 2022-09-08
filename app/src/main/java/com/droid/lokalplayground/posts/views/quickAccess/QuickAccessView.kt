package com.droid.lokalplayground.posts.views.quickAccess

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.epoxy.*
import com.droid.lokalplayground.R
import com.droid.lokalplayground.posts.QuickAccess
import com.droid.lokalplayground.toast

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class QuickAccessView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var recyclerBannerView: EpoxyRecyclerView
    private var tvTitle: TextView
    private var customUIController: CustomUIController
    private val spanCount = 4

    init {
        val view = inflate(context, R.layout.item_quick_access, this)
        recyclerBannerView = view.findViewById(R.id.rvQuickAccess)
        tvTitle = view.findViewById(R.id.tvHeader)

        customUIController = CustomUIController()
        recyclerBannerView.setController(customUIController)

        val layoutManager = GridLayoutManager(getContext(), spanCount)
        customUIController.spanCount = spanCount
        layoutManager.spanSizeLookup = customUIController.spanSizeLookup

        recyclerBannerView.layoutManager = layoutManager
    }

    @ModelProp
    fun setData(quickAccess: QuickAccess) {
        tvTitle.text = quickAccess.quickAccessMeta?.title
        customUIController.addList(quickAccess.quickAccessData)
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