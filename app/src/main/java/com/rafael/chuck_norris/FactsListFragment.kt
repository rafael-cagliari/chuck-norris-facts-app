package com.rafael.chuck_norris

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rafael.chuck_norris.databinding.FragmentFactsListBinding
import com.rafael.chuck_norris.viewmodel.FactListViewModel


class FactsListFragment() : Fragment() {
    var binding:FragmentFactsListBinding? = null
    var factListViewModel: FactListViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentFactsListBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        factListViewModel = context?.let { FactListViewModel(it) }
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
     factListViewModel?.readAllDB()
        binding?.testButton?.setOnClickListener {
            factListViewModel?.getFact()
            factListViewModel?.getFilteredFact("dev")
        }
        binding?.addToDbButton?.setOnClickListener {
            factListViewModel?.retrievedFact?.let { it1 -> factListViewModel?.addFactToDB(it1) }
        }
        factListViewModel?.deleteFactFromDB("O5dJZQC-S3KReemxLiD4Zw")
    }
    }
