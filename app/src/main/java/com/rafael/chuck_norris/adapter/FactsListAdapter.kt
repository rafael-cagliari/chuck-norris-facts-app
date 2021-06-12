package com.rafael.chuck_norris.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rafael.chuck_norris.R
import com.rafael.domain.model.ChuckNorrisFact

class FactsListAdapter(var factsList:List<ChuckNorrisFact>): RecyclerView.Adapter<FactsListAdapter.FactsViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.chuck_norris_fact_item, parent, false)
        return FactsViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: FactsViewHolder, position: Int) {
        val factItem = factsList[position]
        holder.fact.text = factItem.value
        if(factItem.categories.isEmpty()) holder.category.text=""
        else holder.category.text=factItem.categories[0]
    }

    override fun getItemCount(): Int {
        return factsList.size
    }

    class FactsViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        val fact:TextView = view.findViewById(R.id.fact)
        val category:TextView = view.findViewById(R.id.category)
    }

}