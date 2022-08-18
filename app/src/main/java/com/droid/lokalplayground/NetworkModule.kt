package com.droid.lokalplayground

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
@OptIn(ExperimentalSerializationApi::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideContentType() = "application/json".toMediaType()

    @Provides
    @Singleton
    fun providesSupervisorScope() = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    @Provides
    @Singleton
    fun providesHttpClient(
        networkInterceptor: HttpLoggingInterceptor,
        authInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(networkInterceptor)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providesNetworkInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .setLevel(level = HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun providesHeaders(): Interceptor {
        return Interceptor { chain ->
            val request =
                chain.request().newBuilder()
                    .header("Accept-Language", "te")
                    .header("Day-Count", "0")
                    .header("Authorization", "Token 82b0ab73a2f6d7f529867a163ec777fdeb7f8d0b")
                    .build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun providesJsonSerializer(): Json {
        return Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
            explicitNulls = false
        }
    }

    @Type.LokalRetrofit
    @Provides
    @Singleton
    fun providesLokalRetrofit(okHttpClient: OkHttpClient, json: Json, contentType: MediaType): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .baseUrl("http://testapi.getlokalapp.com/")
            .build()
    }

    @Type.LokalAdsRetrofit
    @Provides
    @Singleton
    fun providesLokalAdRetrofit(okHttpClient: OkHttpClient, json: Json, contentType: MediaType): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .baseUrl("http://testads.getlokalapp.com/")
            .build()
    }

    @Type.LokalAPIService
    @Provides
    @Singleton
    fun providesLokalApiService(@Type.LokalRetrofit retrofit: Retrofit): LokalAPIService {
        return retrofit.create(LokalAPIService::class.java)
    }

    @Type.LokalAdsAPIService
    @Provides
    @Singleton
    fun providesLokalAdsApiService(@Type.LokalAdsRetrofit retrofit: Retrofit): AdsAPIService {
        return retrofit.create(AdsAPIService::class.java)
    }
}

class Type {
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class LokalRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class LokalAdsRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class LokalAdsAPIService

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class LokalAPIService
}

