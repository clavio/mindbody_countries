package com.ciphra.android.countrylist.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ciphra.android.countrylist.Models.Country

class CountryListViewModel : ViewModel() {
    val countryListLiveData = MutableLiveData<MutableList<Country>>()
    val errorLiveData = MutableLiveData<String>()

}