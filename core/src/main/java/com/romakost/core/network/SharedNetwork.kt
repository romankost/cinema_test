package com.romakost.core.network

import com.romakost.core.network.adaptors.ResultCallAdapterFactory
import com.romakost.network.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.themoviedb.org/3/"

@Module
@InstallIn(ActivityRetainedComponent::class)
class NetworkModule {

    @ActivityRetainedScoped
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder()
                        .also {
                            it.addHeader("accept", "application/json")
                            it.addHeader("Authorization", "Bearer ${BuildConfig.API_KEY}")
                        }
                        .build()
                )
            }
            .build()
    }

    @ActivityRetainedScoped
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ResultCallAdapterFactory.create())
            .build()
    }
}
