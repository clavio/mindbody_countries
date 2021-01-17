package com.ciphra.android.countrylist.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ciphra.android.countrylist.R
import com.ciphra.android.countrylist.ViewModels.CountryDetailsViewModel

class CountryDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = CountryDetailsFragment()
    }

    private lateinit var viewModel: CountryDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.country_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val bundle = arguments
        val id = arguments?.get("Id")
        viewModel = ViewModelProvider(this).get(CountryDetailsViewModel::class.java)

    }

}