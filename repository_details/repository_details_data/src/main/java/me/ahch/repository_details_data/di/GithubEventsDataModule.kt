package me.ahch.repository_details_data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.ahch.repository_details_data.BuildConfig
import me.ahch.repository_details_data.remote.EventsApi
import me.ahch.repository_details_data.repository.EventRepositoryImpl
import me.ahch.repository_details_domain.repository.EventsRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GithubEventsDataModule {

    @Provides
    @Singleton
    fun provideSearchApi(client: OkHttpClient): EventsApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideEventRepository(
        api: EventsApi,
    ): EventsRepository {
        return EventRepositoryImpl(
            api = api,
        )
    }


}








