package com.ciphra.android.countrylist.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ciphra.android.countrylist.Models.Country
import com.ciphra.android.countrylist.Models.Province
import com.ciphra.android.countrylist.R
import com.ciphra.android.countrylist.ViewModels.CountryListViewModel
import com.ciphra.android.countrylist.databinding.CountryDetailsFragmentBinding
import com.ciphra.android.countrylist.databinding.CountryListFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CountryDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = CountryDetailsFragment()
    }

    val viewModel: CountryListViewModel by viewModel()
    private lateinit var binding : CountryDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CountryDetailsFragmentBinding.inflate(layoutInflater)
        return binding.root    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val id = arguments?.get("Id") as Int
        val provinceListObserver = Observer<MutableList<Province>>{
            if(it.isNotEmpty()) {
                setupRecyclerView(it)
            }
        }
        viewModel.provinceLiveData.observe(this, provinceListObserver)
        viewModel.retrieveProvinceList(id)
    }

    private fun setupRecyclerView(it: MutableList<Province>) {
        val adapter = CountryListAdapter(dataSet = it, rowClicked = null, showFlags = false)
        binding.provinceListRecyclerview.layoutManager = LinearLayoutManager(context)
        binding.provinceListRecyclerview.adapter = adapter
        binding.progressBar.visibility = View.GONE
        binding.provinceListRecyclerview.visibility = View.VISIBLE
    }
}