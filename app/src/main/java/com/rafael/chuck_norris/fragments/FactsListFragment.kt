package com.rafael.chuck_norris.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rafael.chuck_norris.FactItemListener
import com.rafael.chuck_norris.adapter.FactsListAdapter
import com.rafael.chuck_norris.databinding.FragmentFactsListBinding
import com.rafael.chuck_norris.viewmodel.FactListViewModel
import com.rafael.domain.model.ChuckNorrisFact


class FactsListFragment() : FactItemListener, Fragment() {
    lateinit var binding: FragmentFactsListBinding
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

        factListViewModel.readAllData?.observe(
            viewLifecycleOwner,
            { facts: List<ChuckNorrisFact> ->
                adapter.setData(facts)
            }
        )

        //recyclerView
        adapter = FactsListAdapter(this)
        var recyclerView: RecyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        factListViewModel.readAllDB()
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.addFact.setOnClickListener {
            findNavController().navigate(FactsListFragmentDirections.actionFactsListFragmentToSearchFactFragment())
        }

    }

    override fun deleteFact(id: String) {
        factListViewModel.deleteFactFromDB(id)
    }

    override fun updateDataBase() {
        factListViewModel.readAllDB()
    }

}
