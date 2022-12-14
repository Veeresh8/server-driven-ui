package com.droid.lokalplayground

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.airbnb.epoxy.EpoxyRecyclerView
import com.airbnb.epoxy.EpoxyVisibilityTracker
import com.droid.lokalplayground.posts.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private val postViewModel: PostViewModel by viewModels()

    @Inject
    lateinit var postsUIController: PostsUIController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()

        lifecycleScope.launch {
            postViewModel.getAllPosts()

            repeatOnLifecycle(Lifecycle.State.CREATED) {
                postViewModel.homeState.collect {
                    when (it) {
                        is HomeState.Error -> {
                            Toast.makeText(this@MainActivity, it.exception.message, Toast.LENGTH_SHORT).show()
                            swipeRefreshLayout.isRefreshing = false
                            println(it)
                        }
                        is HomeState.Loading -> {
                            swipeRefreshLayout.toast("Fetching Feed")
                        }
                        is HomeState.Success -> {
                            swipeRefreshLayout.isRefreshing = false
                            showPosts(it.posts)
                        }
                    }
                }
            }
        }
    }

    private fun initUI() {
        val epoxyVisibilityTracker = EpoxyVisibilityTracker()

        findViewById<EpoxyRecyclerView>(R.id.rvPosts).apply {
            setController(postsUIController)
            epoxyVisibilityTracker.attach(this)
            postsUIController.attachRecyclerView(this)
        }

        swipeRefreshLayout = findViewById(R.id.swipeContainer)
        swipeRefreshLayout.setOnRefreshListener {
            postViewModel.getAllPosts()
        }

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light);

    }

    private fun showPosts(posts: List<LokalViewType>) {
        postsUIController.addPosts(posts)
    }
}