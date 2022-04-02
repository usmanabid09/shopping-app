package com.example.androidassignment.di

import android.content.Context
import com.example.androidassignment.MainApplication
import com.example.androidassignment.common.Constants.BASE_URL
import com.example.androidassignment.data.remote.MainApi
import com.example.androidassignment.data.repository.ProductRepositoryImpl
import com.example.androidassignment.domain.repository.ProductRepository
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

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): MainApplication = app as MainApplication

    @Singleton
    @Provides
    fun provideMainApi(): MainApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MainApi::class.java)
    }

    @Singleton
    @Provides
    fun provideProductRepository(api: MainApi): ProductRepository = ProductRepositoryImpl(api)

}