package com.droid.lokalplayground

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.airbnb.epoxy.EpoxyRecyclerView
import com.airbnb.epoxy.EpoxyVisibilityTracker
import com.droid.lokalplayground.posts.HomeState
import com.droid.lokalplayground.posts.Post
import com.droid.lokalplayground.posts.PostViewModel
import com.droid.lokalplayground.posts.PostsUIController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

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
                            println(it)
                        }
                        is HomeState.Loading -> {
                            println("Loading posts!")
                        }
                        is HomeState.Success -> {
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
        }
    }

    private fun showPosts(posts: List<Post>) {
        postsUIController.addPosts(posts)
    }
}