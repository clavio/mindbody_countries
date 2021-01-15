package com.ciphra.android.countrylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ciphra.android.countrylist.ui.main.CountryListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, CountryListFragment.newInstance())
                    .commitNow()
        }
    }
}