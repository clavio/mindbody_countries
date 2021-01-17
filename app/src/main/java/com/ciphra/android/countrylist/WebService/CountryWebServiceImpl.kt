package com.ciphra.android.countrylist.WebService

import com.ciphra.android.countrylist.Models.Country
import com.ciphra.android.countrylist.Models.Province
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception
import java.lang.reflect.Type

class CountryWebServiceImpl : CountryWebService {
    val gson = Gson()
    val client = OkHttpClient()

    override suspend fun getCountryList() : MutableList<Country> {
        var returnList = mutableListOf<Country>()
        val request = Request.Builder()
            .url(COUNTRY_API_URL)
            .build()
        try{
            val response = client.newCall(request).execute()
            val body = response.body!!.string()
            val itemType = object : TypeToken<MutableList<Country>>() {}.type
            returnList = gson.fromJson<MutableList<Country>>(body, itemType)
        }
        catch (e  : Exception){
            throw(CustomCountryException(WEB_SERVICE_ERRORCODE))
        }
        return returnList
    }

    override suspend fun getProvinceList(id : Int): MutableList<Province> {
        var returnList = mutableListOf<Province>()
        val request = Request.Builder()
            .url("$COUNTRY_API_URL$id/province")
            .build()
        try{
            val response = client.newCall(request).execute()
            val body = response.body!!.string()
            val itemType = object : TypeToken<MutableList<Country>>() {}.type
            returnList = gson.fromJson<MutableList<Province>>(body, itemType)
        }
        catch (e  : Exception){
            throw(CustomCountryException(WEB_SERVICE_ERRORCODE))
        }
        return returnList
    }

    //TODO, try to make something more generic like this
    fun getList(url : String, itemType : Type): MutableList<Any> {
        var returnList = mutableListOf<Any>()
        val request = Request.Builder()
            .url(url)
            .build()
        try{
            val response = client.newCall(request).execute()
            val body = response.body!!.string()
            returnList = gson.fromJson<MutableList<Any>>(body, itemType)
        }
        catch (e  : Exception){
            throw(CustomCountryException(WEB_SERVICE_ERRORCODE))
        }
        return returnList
    }
}