package com.rafael.chuck_norris.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rafael.chuck_norris.FactItemListener
import com.rafael.chuck_norris.R
import com.rafael.domain.model.ChuckNorrisFact

class FactsListAdapter(val factItemListener: FactItemListener): RecyclerView.Adapter<FactsListAdapter.FactsViewHolder>() {

    var factsList:MutableList<ChuckNorrisFact> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.chuck_norris_fact_item, parent, false)
        return FactsViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: FactsViewHolder, position: Int) {
        val factItem = factsList[position]
        holder.deletebutton.setOnClickListener {
            factItemListener.deleteFact(factItem.id)
            factItemListener.updateDataBase()
        }

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
        val deletebutton:Button = view.findViewById(R.id.delete_button)

    }

    fun setData(facts: List<ChuckNorrisFact>) {
        if (factsList.isNotEmpty()) {
            factsList.clear()
        }
        factsList = facts.toMutableList()
        notifyDataSetChanged()
    }

}