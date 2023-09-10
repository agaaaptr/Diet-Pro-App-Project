package com.example.dietproapp.core.di

import com.example.dietproapp.ui.jurnalmakanan.JurnalMenuViewModel
import com.example.dietproapp.ui.login.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { JurnalMenuViewModel(get()) }
}