package com.droid.lokalplayground.di

import com.droid.lokalplayground.feed.AdsAPIService
import com.droid.lokalplayground.feed.LokalAPIService
import com.droid.lokalplayground.posts.*
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
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
                    .header("Authorization", "Token e4e25e1cdb6cd2bb2f5c098cc2b8052cfdc46fcd")
                    .build()
            chain.proceed(request)
        }
    }

    @Provides
    @Type.LokalSerializer
    @Singleton
    fun providesJsonSerializer(): Json {
        return Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
            explicitNulls = false
        }
    }

    @Provides
    @Type.PostSerializer
    @Singleton
    fun providesPostJsonSerializer(): Json {
        val modules = SerializersModule {
            polymorphic(Post::class) {
                subclass(Banner::class, Banner.serializer())
                subclass(QuickAccess::class, QuickAccess.serializer())
                subclass(Carousel::class, Carousel.serializer())
                subclass(QuickNotification::class, QuickNotification.serializer())
                subclass(Form::class, Form.serializer())
                subclass(ArticleFullScreen::class, ArticleFullScreen.serializer())
                subclass(Article::class, Article.serializer())
                subclass(Toolbar::class, Toolbar.serializer())
            }

            polymorphic(Style::class) {
                subclass(BannerStyle::class, BannerStyle.serializer())
                subclass(ToolbarStyle::class, ToolbarStyle.serializer())
            }
        }

        return Json {
            serializersModule = modules
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
            explicitNulls = false
        }
    }

    @Type.LokalRetrofit
    @Provides
    @Singleton
    fun providesLokalRetrofit(okHttpClient: OkHttpClient, @Type.PostSerializer json: Json, contentType: MediaType): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .baseUrl("https://api.getlokalapp.com/")
            .build()
    }

    @Type.LokalAdsRetrofit
    @Provides
    @Singleton
    fun providesLokalAdRetrofit(okHttpClient: OkHttpClient, @Type.LokalSerializer json: Json, contentType: MediaType): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .baseUrl("http://ads.getlokalapp.com/")
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

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class PostSerializer

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class LokalSerializer
}

