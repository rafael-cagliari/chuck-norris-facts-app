package com.rafael.chuck_norris

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rafael.chuck_norris.adapter.FactsListAdapter
import com.rafael.chuck_norris.databinding.FragmentFactsListBinding
import com.rafael.chuck_norris.viewmodel.FactListViewModel


class FactsListFragment() : Fragment() {
    lateinit var binding:FragmentFactsListBinding
    lateinit var factListViewModel: FactListViewModel
    lateinit var adapter: FactsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentFactsListBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        factListViewModel = context?.let { FactListViewModel(it) }!!


        //recyclerView
        adapter = FactsListAdapter(factListViewModel.readAllDB())
        var recyclerView: RecyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.testButton.setOnClickListener {
            factListViewModel.getFact()
            factListViewModel.getFilteredFact("dev")
        }

        binding.addToDbButton.setOnClickListener {
           factListViewModel.addFactToDB(factListViewModel.retrievedFact)
        }
    }
    }
