package com.ciphra.android.countrylist.WebService

import com.ciphra.android.countrylist.Models.Country
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception

class CountryWebServiceImpl : CountryWebService {
    override fun getCountryList() : MutableList<Country> {
        var returnList = mutableListOf<Country>()
        val gson = Gson()
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(COUNTRY_LIST_URL)
            .build()
        try{
            val response = client.newCall(request).execute()
            val body = response.body.toString()
            val itemType = object : TypeToken<MutableList<Country>>() {}.type
            returnList = gson.fromJson<MutableList<Country>>(body, itemType)
        }
        catch (e  : Exception){
            throw(CustomCountryException(WEB_SERVICE_ERRORCODE))
        }
        return returnList
    }
}