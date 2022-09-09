package com.droid.lokalplayground.posts.views.article

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.TextView
import com.airbnb.epoxy.*
import com.droid.lokalplayground.R
import com.droid.lokalplayground.posts.Article
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper


@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class ArticleView @JvmOverloads constructor(context: Context,
                                            attrs: AttributeSet? = null,
                                            defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var recyclerView: EpoxyRecyclerView
    private var tvHeader: TextView
    private var customUIController: CustomUIController


    init {
        val view = inflate(context, R.layout.item_article_view, this)
        recyclerView = view.findViewById(R.id.rvArticles)
        tvHeader = view.findViewById(R.id.tvHeader)

        customUIController = CustomUIController()
        recyclerView.setController(customUIController)

        val epoxyVisibilityTracker = EpoxyVisibilityTracker()
        epoxyVisibilityTracker.attach(recyclerView)
    }

    @ModelProp
    fun setData(article: Article) {
        tvHeader.text = article.articleMeta?.title
        customUIController.addList(article.articleItem)
    }

    inner class CustomUIController : AsyncEpoxyController() {
        var articleList: List<Article.ArticleItem> = emptyList()

        fun addList(list: List<Article.ArticleItem>) {
            articleList = list
            requestModelBuild()
        }

        override fun buildModels() {
            articleList.forEach { article ->
                articleItemView {
                    id(article.id)
                    articleItem(article)
                }
            }
        }
    }
}