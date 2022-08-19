package com.droid.lokalplayground

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class FeedViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `check if loading state is encountered while fetching homefeed`() = runTest {
        val feedUseCase = mockk<FeedUseCase>()

        val viewModel = FeedViewModel(feedUseCase)

        val job = launch {
            viewModel.homeFeedState.collect()
        }

        assertTrue(viewModel.homeFeedState.value is FeedViewModel.HomeFeedState.Loading)
        job.cancel()
    }
  
    @Test
    fun `check if error is encountered when posts fail but adverts succeed`() = runTest {
        val sampleAdvert = AdResponse.Advert(id = 777)

        val feedUseCase = mockk<FeedUseCase>()
        coEvery { feedUseCase.fetchPosts() } returns Result.Error(java.lang.RuntimeException("No posts today!"))
        coEvery { feedUseCase.fetchAdverts() } returns Result.Success(AdResponse(adsList = listOf(sampleAdvert)))

        val viewModel = FeedViewModel(feedUseCase)
        viewModel.fetchFeedData()

        val job = launch {
            viewModel.homeFeedState.collect()
        }

        assertTrue(viewModel.homeFeedState.value is FeedViewModel.HomeFeedState.Loading)

        runCurrent()

        assertTrue(viewModel.homeFeedState.value is FeedViewModel.HomeFeedState.Error)

        job.cancel()
    }

    @Test
    fun `check if posts are fetched even when avderts fail`() = runTest {
        val samplePost = PostResponse.Post(postID = 777)

        val feedUseCase = mockk<FeedUseCase>()
        coEvery { feedUseCase.fetchPosts() } returns Result.Success(PostResponse(posts = listOf(samplePost)))
        coEvery { feedUseCase.fetchAdverts() } returns Result.Error(java.lang.Exception("No adverts today!"))

        val viewModel = FeedViewModel(feedUseCase)
        viewModel.fetchFeedData()

        val job = launch {
            viewModel.homeFeedState.collect()
        }

        assertTrue(viewModel.homeFeedState.value is FeedViewModel.HomeFeedState.Loading)

        runCurrent()

        assertTrue(viewModel.homeFeedState.value is FeedViewModel.HomeFeedState.Success)

        job.cancel()
    }

    @Test
    fun `check if feed fails when posts and adverts fail`() = runTest {
        val feedUseCase = mockk<FeedUseCase>()
        coEvery { feedUseCase.fetchPosts() } returns Result.Error(java.lang.Exception("No posts today!"))
        coEvery { feedUseCase.fetchAdverts() } returns Result.Error(java.lang.Exception("No adverts today!"))

        val viewModel = FeedViewModel(feedUseCase)
        viewModel.fetchFeedData()

        val job = launch {
            viewModel.homeFeedState.collect()
        }

        assertTrue(viewModel.homeFeedState.value is FeedViewModel.HomeFeedState.Loading)

        runCurrent()

        assertTrue(viewModel.homeFeedState.value is FeedViewModel.HomeFeedState.Error)

        job.cancel()
    }
}