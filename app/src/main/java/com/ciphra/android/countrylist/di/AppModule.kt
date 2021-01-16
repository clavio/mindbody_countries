package com.ciphra.android.countrylist.di

import com.ciphra.android.countrylist.Repository.CountryListRepository
import com.ciphra.android.countrylist.Repository.CountryListRepositoryImpl
import com.ciphra.android.countrylist.ViewModels.CountryListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<CountryListRepository> {CountryListRepositoryImpl()}
    viewModel {CountryListViewModel(get())}
}