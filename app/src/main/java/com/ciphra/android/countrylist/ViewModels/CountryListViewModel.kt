package com.ciphra.android.countrylist.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ciphra.android.countrylist.Models.Country
import com.ciphra.android.countrylist.WebService.CountryWebService
import com.ciphra.android.countrylist.WebService.CustomCountryException
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CountryListViewModel(val webService: CountryWebService) : ViewModel() {
    val countryListLiveData = MutableLiveData<MutableList<Country>>()
    val errorLiveData = MutableLiveData<String>()

    fun retrieveCountryList(){
        viewModelScope.launch {
            try{
                countryListLiveData.postValue(getAllCountries())
            }
            catch (exception : CustomCountryException){
                errorLiveData.postValue(exception.message)
            }
        }
    }

    suspend fun getAllCountries(): MutableList<Country> {
        // 2
        return withContext(IO) {
            webService.getCountryList()
        }
    }
}