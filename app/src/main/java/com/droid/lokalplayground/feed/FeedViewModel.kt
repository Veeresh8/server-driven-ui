//package com.droid.lokalplayground.feed
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.droid.lokalplayground.Result
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.async
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class FeedViewModel @Inject constructor(private val feedUseCase: FeedUseCase) : ViewModel() {
//
//    val homeFeedState = MutableStateFlow<HomeFeedState>(HomeFeedState.Loading())
//
//    fun fetchFeedData() {
//        val postsRequest = viewModelScope.async { feedUseCase.fetchPosts() }
//        val advertsRequest = viewModelScope.async { feedUseCase.fetchAdverts() }
//
//        viewModelScope.launch {
//            homeFeedState.value = HomeFeedState.Loading("Fetching Feed")
//
//            var posts = listOf<PostResponse.Post>()
//            var adverts = listOf<AdResponse.Advert>()
//
//            when (val result = postsRequest.await()) {
//                is Result.Error -> {
//                    println(result.exception)
//                }
//                is Result.Success -> {
//                    posts = result.data.posts ?: listOf()
//                }
//            }
//
//            when (val result = advertsRequest.await()) {
//                is Result.Error -> {
//                    println(result.exception)
//                }
//                is Result.Success -> {
//                    adverts = result.data.adsList ?: listOf()
//                }
//            }
//
//            if (posts.isEmpty()) {
//                homeFeedState.value = HomeFeedState.Error("Failed to fetch posts!")
//            } else {
//                homeFeedState.value = HomeFeedState.Success(posts = posts, adverts = adverts)
//            }
//        }
//    }
//
//    sealed class HomeFeedState {
//        data class Success(val posts: List<PostResponse.Post>,
//                           val adverts: List<AdResponse.Advert>): HomeFeedState()
//        data class Error(val message: String): HomeFeedState()
//
//        data class Loading(val message: String = ""): HomeFeedState()
//    }
//}