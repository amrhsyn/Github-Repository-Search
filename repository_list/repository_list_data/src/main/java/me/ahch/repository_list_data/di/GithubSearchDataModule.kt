package me.ahch.repository_list_data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.ahch.repository_list_data.BuildConfig
import me.ahch.repository_list_data.remote.SearchApi
import me.ahch.repository_list_data.repository.SearchRepositoryImpl
import me.ahch.repository_list_domain.repository.SearchRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GithubSearchDataModule {

    @Provides
    @Singleton
    fun provideSearchApi(client: OkHttpClient): SearchApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideSearchRepository(
        api: SearchApi,
    ): SearchRepository {
        return SearchRepositoryImpl(
            api = api,
        )
    }

}








