package com.ciphra.android.countrylist.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ciphra.android.countrylist.Models.Country
import com.ciphra.android.countrylist.R
import kotlinx.coroutines.withContext
import org.apache.commons.lang3.text.WordUtils

class CountryListAdapter(private val dataSet : List<Country>) : RecyclerView.Adapter<CountryListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.country_cell, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: CountryListAdapter.ViewHolder, position: Int) {
        val countryLabel = WordUtils.capitalizeFully(dataSet[position].Name)
        holder.labelTextView.setText(countryLabel)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val labelTextView : TextView
        init {
            labelTextView = view.findViewById(R.id.countryLabelTextView)
        }
    }
}