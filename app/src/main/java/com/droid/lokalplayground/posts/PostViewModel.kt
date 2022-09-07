package com.droid.lokalplayground.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droid.lokalplayground.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val postsUseCase: PostsUseCase): ViewModel() {

    val homeState = MutableStateFlow<HomeState>(HomeState.Loading(""))

    fun getAllPosts() {
        homeState.value = HomeState.Loading("Fetching Feed")

        viewModelScope.launch {

            delay(1000)

            when (val result = postsUseCase.getPosts()) {
                is Result.Success -> {
                    homeState.value = HomeState.Success(result.data)
                }
                is Result.Error -> {
                    homeState.value = HomeState.Error(result.exception)
                }
            }
        }
    }
}