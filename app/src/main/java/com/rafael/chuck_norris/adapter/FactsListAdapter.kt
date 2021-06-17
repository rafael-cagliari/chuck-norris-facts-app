package com.rafael.chuck_norris.adapter

import android.util.TypedValue
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
        holder.deleteButton.setOnClickListener {
            factItemListener.deleteFact(factItem.id)
            factItemListener.updateDataBase()
        }

        holder.shareButton.setOnClickListener {
            factItemListener.shareFact(factItem.value)
        }

        holder.fact.text = factItem.value
        if(factItem.value.length>=80) holder.fact.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22.toFloat())
        else holder.fact.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28.toFloat())


        if(factItem.categories[0]=="") holder.category.text="uncategorized"
        else holder.category.text=factItem.categories[0]
    }

    override fun getItemCount(): Int {
        return factsList.size
    }

    class FactsViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        val fact:TextView = view.findViewById(R.id.fact)
        val category:TextView = view.findViewById(R.id.category)
        val deleteButton:Button = view.findViewById(R.id.delete_button)
        val shareButton:Button = view.findViewById(R.id.share)

    }

    fun setData(facts: List<ChuckNorrisFact>) {
        if (factsList.isNotEmpty()) {
            factsList.clear()
        }
        factsList = facts.toMutableList()
        notifyDataSetChanged()
    }

}