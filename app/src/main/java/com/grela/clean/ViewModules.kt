package com.grela.clean

import com.grela.clean.model.CountryViewModel
import com.grela.data.datasource.SportRemoteDataSourceContract
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModules {
    val modules = module {
        viewModel {
            CountryViewModel(get(), get())
        }
    }
}