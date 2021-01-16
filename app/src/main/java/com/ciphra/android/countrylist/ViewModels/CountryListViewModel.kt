package com.ciphra.android.countrylist.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ciphra.android.countrylist.Models.Country
import com.ciphra.android.countrylist.Repository.CountryListRepository

class CountryListViewModel(val repository: CountryListRepository) : ViewModel() {
    val countryListLiveData = MutableLiveData<MutableList<Country>>()
    val errorLiveData = MutableLiveData<String>()

    fun retrieveCountryList(){
        countryListLiveData.postValue(repository.getCountryList())
    }
}