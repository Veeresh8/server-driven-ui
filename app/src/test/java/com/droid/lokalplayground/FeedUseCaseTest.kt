package com.droid.lokalplayground

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit


@OptIn(ExperimentalCoroutinesApi::class)
class FeedUseCaseTest {

    private lateinit var mockWebServer: MockWebServer

    private lateinit var lokalAPIService: LokalAPIService
    private lateinit var lokalAdsAPIService: AdsAPIService

    private lateinit var feedUseCase: FeedUseCase

    private val client = OkHttpClient.Builder().build()
    private val contentType = "application/json".toMediaType()

    @OptIn(ExperimentalSerializationApi::class)
    @Before
    fun setup() {
        val jsonConverter = Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
            explicitNulls = false
        }

        mockWebServer = MockWebServer()

        lokalAPIService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(client)
            .addConverterFactory(jsonConverter.asConverterFactory(contentType))
            .build().create(LokalAPIService::class.java)

        lokalAdsAPIService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(client)
            .addConverterFactory(jsonConverter.asConverterFactory(contentType))
            .build().create(AdsAPIService::class.java)

        feedUseCase = FeedUseCase(lokalAPIService, lokalAdsAPIService)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `encounters 400 when posts are loaded`() = runTest {
        val response = MockResponse()
            .setBody("Bad Request Response")
            .setResponseCode(400)

        mockWebServer.enqueue(response)

        val result = feedUseCase.fetchPosts()

        assertTrue(result is Result.Error)
        assertTrue((result as Result.Error).exception.message == "HTTP 400 Client Error")
    }

    @Test
    fun `encounters 500 when posts are loaded`() = runTest {
        val response = MockResponse()
            .setBody("Server Gateway Response")
            .setResponseCode(500)

        mockWebServer.enqueue(response)

        val result = feedUseCase.fetchPosts()

        assertTrue(result is Result.Error)
        assertTrue((result as Result.Error).exception.message == "HTTP 500 Server Error")
    }

    @Test
    fun `check for malformed JSON in posts`() = runTest {
        val response = MockResponse()
            .setBody("Some Random JSON")
            .setResponseCode(200)

        mockWebServer.enqueue(response)

        val result = feedUseCase.fetchPosts()

        assertTrue(result is Result.Error)
    }
}