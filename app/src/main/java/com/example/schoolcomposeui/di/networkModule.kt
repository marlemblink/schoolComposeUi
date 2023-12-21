package com.example.schoolcomposeui.di

import com.example.schoolcomposeui.network.NetworkAPI
import com.example.schoolcomposeui.repository.Repository
import com.example.schoolcomposeui.repository.RepositoryImpl
import com.example.schoolcomposeui.utils.BASE_URL
import com.example.schoolcomposeui.vm.MainVM
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single { androidContext().applicationContext }

    single {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkAPI::class.java)
    }

    single<Repository> { RepositoryImpl(get()) }

    viewModel {
        MainVM(get())
    }
}