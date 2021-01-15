package com.ciphra.android.countrylist.ui.main

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ciphra.android.countrylist.Models.Country
import kotlinx.coroutines.withContext

class CountryListAdapter(private val dataSet : List<Country>) : RecyclerView.Adapter<CountryListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryListAdapter.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: CountryListAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        init {

        }
    }
}