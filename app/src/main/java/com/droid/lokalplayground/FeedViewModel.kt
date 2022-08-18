package com.droid.lokalplayground

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(private val feedUseCase: FeedUseCase): ViewModel() {

    fun fetchFeedData() {
        viewModelScope.launch {
            feedUseCase.fetchHomeFeed().collect {

            }
        }
    }
}