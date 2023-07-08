package com.example.dietproapp.core.di

import com.example.dietproapp.core.data.repository.AppRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AppRepository(get(), get()) }
}