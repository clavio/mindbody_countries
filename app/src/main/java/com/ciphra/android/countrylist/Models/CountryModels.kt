package com.ciphra.android.countrylist.Models

data class Country(val ID : String = "", val Name : String = "", val Code : String = "", val PhoneCode : String = "")
data class Province(val ID : Int = 0, val CountryCode : String = "", val Code : String = "", val Name : String = "")