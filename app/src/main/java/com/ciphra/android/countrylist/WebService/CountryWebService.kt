package com.ciphra.android.countrylist.WebService

import com.ciphra.android.countrylist.Models.Country

interface CountryWebService {
    suspend fun getCountryList() : MutableList<Country>
}