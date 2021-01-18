package com.ciphra.android.countrylist.di

import com.ciphra.android.countrylist.ViewModels.CountryListViewModel
import com.ciphra.android.countrylist.WebService.CountryWebService
import com.ciphra.android.countrylist.WebService.CountryWebServiceImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<CountryWebService>{CountryWebServiceImpl(get())}
    viewModel {CountryListViewModel(get())}
}