package com.ciphra.android.countrylist.Repository

import com.ciphra.android.countrylist.Models.Country

interface CountryListRepository {
    fun getCountryList() : MutableList<Country>
}