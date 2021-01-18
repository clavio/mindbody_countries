package com.ciphra.android.countrylist.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ciphra.android.countrylist.Models.Country
import com.ciphra.android.countrylist.Models.Province
import com.ciphra.android.countrylist.R
import com.ciphra.android.countrylist.WebService.CountryWebService
import com.ciphra.android.countrylist.WebService.CustomCountryException
import com.ciphra.android.countrylist.WebService.NO_INTERNET_ERRORCODE
import com.ciphra.android.countrylist.WebService.WEB_SERVICE_ERRORCODE
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CountryListViewModel(val webService: CountryWebService) : ViewModel() {
    val countryListLiveData = MutableLiveData<MutableList<Country>>()
    val provinceLiveData = MutableLiveData<MutableList<Province>>()
    val errorLiveData = MutableLiveData<String>()

    fun retrieveCountryList(){
        viewModelScope.launch {
            try{
                countryListLiveData.postValue(getAllCountries())
                errorLiveData.postValue("")
            }
            catch (exception : CustomCountryException){
                errorLiveData.postValue(exception.message)
            }
        }
    }

    fun retrieveProvinceList(id : Int){
        viewModelScope.launch {
            try{
                provinceLiveData.postValue(getProvinces(id))
                errorLiveData.postValue("")
            }
            catch (exception : CustomCountryException){
                errorLiveData.postValue(exception.message)
            }
        }
    }

    suspend fun getAllCountries(): MutableList<Country> {
        return withContext(IO) {
            webService.getCountryList()
        }
    }

    suspend fun getProvinces(id : Int): MutableList<Province>{
        return withContext(IO) {
            webService.getProvinceList(id)
        }
    }

    fun getErrorMessageForCode(code : String) : Int{
        return when(code){
            NO_INTERNET_ERRORCODE -> R.string.internet_required
            WEB_SERVICE_ERRORCODE -> R.string.web_service_failed
            else -> R.string.unknown_error_occured
        }
    }
}