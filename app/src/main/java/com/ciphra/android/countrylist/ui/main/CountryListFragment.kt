package com.ciphra.android.countrylist.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ciphra.android.countrylist.Models.Country
import com.ciphra.android.countrylist.R
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
                setupRecyclerView(it)
            }
        }
        viewModel.countryListLiveData.observe(this, countryListObserver)
        val errorObserver = Observer<String>{
            if(it.isNotEmpty()){
                val errorMessage = requireContext().getString(viewModel.getErrorMessageForCode(it))
                showErrorLayout(errorMessage)
                binding.retryButton.setOnClickListener { retryCountryFetch() }
            }
        }
        viewModel.errorLiveData.observe(this, errorObserver)
        viewModel.retrieveCountryList()
    }

    private fun showErrorLayout(errorCode : String) {
        binding.countryListRecyclerview.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.errorLayout.visibility = View.VISIBLE
        binding.errorTextview.setText(errorCode)

    }

    private fun setupRecyclerView(it: MutableList<Country>) {
        val adapter = CountryListAdapter(dataSet = it, rowClicked = navigateToProvinces, showFlags = true)
        binding.countryListRecyclerview.layoutManager = LinearLayoutManager(context)
        binding.countryListRecyclerview.adapter = adapter
        binding.progressBar.visibility = View.GONE
        binding.errorLayout.visibility = View.GONE
        binding.countryListRecyclerview.visibility = View.VISIBLE
    }

    private fun showProgressBar(){
        binding.progressBar.visibility = View.VISIBLE
        binding.errorLayout.visibility = View.GONE
        binding.countryListRecyclerview.visibility = View.GONE
    }

    private val navigateToProvinces = {
        id : Int ->
        var bundle = Bundle()
        bundle.putInt("Id", id)
        requireView().findNavController().navigate(R.id.action_countryListFragment_to_countryDetailsFragment, bundle)
    }

    fun retryCountryFetch(){
        showProgressBar()
        viewModel.retrieveCountryList()
    }


}