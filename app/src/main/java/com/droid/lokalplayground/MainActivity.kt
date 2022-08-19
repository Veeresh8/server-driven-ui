package com.droid.lokalplayground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val feedViewModel: FeedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        feedViewModel.fetchFeedData()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                feedViewModel.homeFeedState.collect {
                    when (it) {
                        is FeedViewModel.HomeFeedState.Error -> {
                            println("MainActivity: $it")
                        }
                        is FeedViewModel.HomeFeedState.Loading -> {
                            println("MainActivity: $it")
                        }
                        is FeedViewModel.HomeFeedState.Success -> {
                            println("MainActivity: Posts:${it.posts.size} || Adverts:${it.adverts.size}")
                        }
                    }
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        viewModelStore.clear()
    }
}