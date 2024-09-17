package me.melkopisi.payskytask.data.di

import com.google.gson.Gson
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }

    @Singleton
    @Provides
    fun providesMainRetrofit(
        client: OkHttpClient,
        gson: Gson,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()

    @Singleton
    @Provides
    fun providesMainOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(LoggingInterceptor.Builder().setLevel(Level.BASIC).log(Platform.WARN).build())
            .build()


    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()

}


//@Suppress("KotlinConstantConditions")
//fun provideLoggingLevel(): Level = if (BuildConfig.BUILD_TYPE != "release") Level.BASIC else NONE

