package com.ciphra.android.countrylist.WebService

import com.ciphra.android.countrylist.Models.Country
import com.ciphra.android.countrylist.Models.Province

interface CountryWebService {
    suspend fun getCountryList() : MutableList<Country>
    suspend fun getProvinceList(id : Int) : MutableList<Province>
}