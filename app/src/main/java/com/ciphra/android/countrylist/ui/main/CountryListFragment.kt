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
import com.ciphra.android.countrylist.ViewModels.CountryListViewModel
import com.ciphra.android.countrylist.databinding.CountryListFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CountryListFragment : Fragment() {

    companion object {
        fun newInstance() = CountryListFragment()
    }

    val viewModel: CountryListViewModel by viewModel()
    private lateinit var binding : CountryListFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = CountryListFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val countryListObserver = Observer<MutableList<Country>>{
            if(it.isNotEmpty()) {
                //TODO remove errors
                setupRecyclerView(it)
            }
        }
        viewModel.countryListLiveData.observe(this, countryListObserver)
        val errorObserver = Observer<String>{
            //TODO remove loading
            //TODO remove List
            //TODO show errors
        }
        viewModel.retrieveCountryList()
    }

    private fun setupRecyclerView(it: MutableList<Country>) {
        val adapter = CountryListAdapter(it)
        binding.countryListRecyclerview.layoutManager = LinearLayoutManager(context)
        binding.countryListRecyclerview.adapter = adapter
        binding.progressBar.visibility = View.GONE
        binding.countryListRecyclerview.visibility = View.VISIBLE
    }

}