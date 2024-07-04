package com.orion.templete.di

import com.orion.templete.data.repository.StocksRepositoryImplementation
import com.orion.templete.domain.repository.StocksRepository
import com.orion.templete.data.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStockApi(): ApiService {
        return Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideUserDetails(apiService: ApiService): StocksRepository {
        return StocksRepositoryImplementation(apiService = apiService)
    }
}