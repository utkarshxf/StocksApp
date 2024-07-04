package com.orion.templete.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.orion.templete.data.db.companyOverviewDao
import com.orion.templete.data.db.companyOverviewDatabase
import com.orion.templete.data.db.topGainLoseDao
import com.orion.templete.data.db.topGainLoseDatabase
import com.orion.templete.data.repository.StocksRepositoryImplementation
import com.orion.templete.domain.repository.StocksRepository
import com.orion.templete.data.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideTopGainLoseDatabase(app: Application): topGainLoseDatabase {
        return Room.databaseBuilder(
            app,
            topGainLoseDatabase::class.java,
            topGainLoseDatabase.DATABASE_NAME
        ).build()
    }
    @Provides
    @Singleton
    fun preovideCompanyOverviewDatabase(app: Application): companyOverviewDatabase {
        return Room.databaseBuilder(
            app,
            companyOverviewDatabase::class.java,
            companyOverviewDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTopGainLoseDao(database: topGainLoseDatabase): topGainLoseDao {
        return database.topGainLoseDao()
    }
    @Provides
    @Singleton
    fun provideCompanyOverViewDao(database: companyOverviewDatabase): companyOverviewDao {
        return database.companyOverviewDao()
    }

    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }
}