package com.ciphra.android.countrylist.WebService

import com.ciphra.android.countrylist.Models.Country

interface CountryWebService {
    fun getCountryList() : MutableList<Country>
}