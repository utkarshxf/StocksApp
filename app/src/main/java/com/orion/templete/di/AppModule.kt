package com.orion.templete.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.orion.templete.data.db.CompanyOverviewDao
import com.orion.templete.data.db.CompanyOverviewDatabase
import com.orion.templete.data.db.TopGainLoseDao
import com.orion.templete.data.db.TopGainLoseDatabase
import com.orion.templete.data.network.ApiService
import com.orion.templete.data.repository.StocksRepositoryImplementation
import com.orion.templete.domain.repository.StocksRepository
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
        return Retrofit.Builder().baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideUserDetails(apiService: ApiService): StocksRepository {
        return StocksRepositoryImplementation(apiService = apiService)
    }

    @Provides
    @Singleton
    fun provideTopGainLoseDatabase(app: Application): TopGainLoseDatabase {
        return Room.databaseBuilder(
            app, TopGainLoseDatabase::class.java, TopGainLoseDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun preovideCompanyOverviewDatabase(app: Application): CompanyOverviewDatabase {
        return Room.databaseBuilder(
            app, CompanyOverviewDatabase::class.java, CompanyOverviewDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTopGainLoseDao(database: TopGainLoseDatabase): TopGainLoseDao {
        return database.topGainLoseDao()
    }

    @Provides
    @Singleton
    fun provideCompanyOverViewDao(database: CompanyOverviewDatabase): CompanyOverviewDao {
        return database.companyOverviewDao()
    }

    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }
}