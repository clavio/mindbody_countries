package com.ciphra.android.countrylist.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.ciphra.android.countrylist.Models.Place
import com.ciphra.android.countrylist.R
import org.apache.commons.lang3.text.WordUtils

class CountryListAdapter(private val dataSet : List<Place>, val rowClicked : ((Int) -> (Unit))?) : RecyclerView.Adapter<CountryListAdapter.ViewHolder>() {
    //I decided to make one adapter for both screens because the lists are so similar
    //the row clicked is nullable, if it is null onclick does nothing
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.country_cell, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val countryLabel = WordUtils.capitalizeFully(dataSet[position].Name)
        holder.labelTextView.setText(countryLabel)
        if(rowClicked != null)holder.rowLayout.setOnClickListener {
            rowClicked!!(dataSet[position].ID.toInt())
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val labelTextView : TextView
        val rowLayout : ConstraintLayout
        init {
            labelTextView = view.findViewById(R.id.countryLabelTextView)
            rowLayout = view.findViewById(R.id.country_row_layout)
        }
    }
}