package com.ciphra.android.countrylist.Models

data class Country(val PhoneCode : String = "") : Place()
data class Province( val CountryCode : String = "") : Place()
open class Place(val ID : String = "", val Name : String = "", val Code : String = "")