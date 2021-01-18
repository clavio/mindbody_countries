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
    var countryId = -1
    var countryLabel = ""
    private lateinit var binding : CountryDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CountryDetailsFragmentBinding.inflate(layoutInflater)
        return binding.root    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        countryId = arguments?.get("Id") as Int
        countryLabel = arguments?.get("Country") as String
        val provinceListObserver = Observer<MutableList<Province>>{
            if(it.isNotEmpty()) {
                setupRecyclerView(it)
            }
            else{
                showEmptyProvinceMessage()
            }
        }
        requireActivity().title = countryLabel
        viewModel.provinceLiveData.observe(this, provinceListObserver)

        val errorObserver = Observer<String>{
            if(it.isNotEmpty()){
                val errorMessage = requireContext().getString(viewModel.getErrorMessageForCode(it))
                showErrorLayout(errorMessage)
                binding.retryButton.setOnClickListener { retryProvinceFetch() }
            }
        }
        viewModel.errorLiveData.observe(this, errorObserver)
        viewModel.retrieveProvinceList(countryId)
    }

    private fun setupRecyclerView(it: MutableList<Province>) {
        val adapter = CountryListAdapter(dataSet = it, rowClicked = null, showFlags = false)
        binding.provinceListRecyclerview.layoutManager = LinearLayoutManager(context)
        binding.provinceListRecyclerview.adapter = adapter
        binding.progressBar.visibility = View.GONE
        binding.errorLayout.visibility = View.GONE
        binding.provinceListRecyclerview.visibility = View.VISIBLE
    }

    private fun showProgressBar(){
        binding.progressBar.visibility = View.VISIBLE
        binding.errorLayout.visibility = View.GONE
        binding.provinceListRecyclerview.visibility = View.GONE
    }

    private fun showErrorLayout(errorCode : String) {
        setErrorVisibilities()
        binding.errorTextview.setText(errorCode)

    }

    private fun setErrorVisibilities() {
        binding.provinceListRecyclerview.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.errorLayout.visibility = View.VISIBLE
    }

    fun retryProvinceFetch(){
        showProgressBar()
        viewModel.retrieveProvinceList(countryId)
    }

    fun showEmptyProvinceMessage(){
        setErrorVisibilities()
        binding.errorTextview.setText(requireContext().getString(R.string.no_provinces_message))
        binding.retryButton.visibility = View.GONE
    }


}