package com.ciphra.android.countrylist.ViewModels

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.ciphra.android.countrylist.R
import com.ciphra.android.countrylist.WebService.CountryWebServiceImpl
import com.ciphra.android.countrylist.WebService.NO_INTERNET_ERRORCODE
import com.ciphra.android.countrylist.WebService.UNKNOWN_ERRORCODE
import com.ciphra.android.countrylist.WebService.WEB_SERVICE_ERRORCODE
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.mockito.*


class CountryListViewModelTest : TestCase() {

    @Mock
    val webService = CountryWebServiceImpl()
    val viewModel = CountryListViewModel(webService)

    @Test
    fun testGetErrorMessageForCode() {
        assertEquals(viewModel.getErrorMessageForCode(UNKNOWN_ERRORCODE) , R.string.unknown_error_occured)
        assertEquals(viewModel.getErrorMessageForCode("-22") , R.string.unknown_error_occured)
        assertEquals(viewModel.getErrorMessageForCode(NO_INTERNET_ERRORCODE), R.string.internet_required)
        assertEquals(viewModel.getErrorMessageForCode(WEB_SERVICE_ERRORCODE), R.string.web_service_failed)
    }

}