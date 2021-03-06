package com.rafael.chuck_norris.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rafael.chuck_norris.FactItemListener
import com.rafael.chuck_norris.adapter.FactsListAdapter
import com.rafael.chuck_norris.databinding.FragmentFactsListBinding
import com.rafael.chuck_norris.viewmodel.FactListViewModel
import com.rafael.domain.model.ChuckNorrisFact
import org.koin.androidx.viewmodel.ext.android.sharedViewModel



class FactsListFragment : FactItemListener, Fragment() {
    private lateinit var binding: FragmentFactsListBinding
    private lateinit var adapter: FactsListAdapter
    private val factListViewModel by sharedViewModel<FactListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        val fragmentBinding = FragmentFactsListBinding.inflate(inflater, container, false)
        binding = fragmentBinding


        factListViewModel.readAllData.observe(
            viewLifecycleOwner,
            { facts: List<ChuckNorrisFact> ->
                adapter.setData(facts)
            }
        )

        //recyclerView
        adapter = FactsListAdapter(this)
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this.context,
                DividerItemDecoration.VERTICAL
            )
        )

        factListViewModel.readAllDB()
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.addFact.setOnClickListener {
            findNavController().navigate(FactsListFragmentDirections.actionFactsListFragmentToSearchFactFragment())
        }
    }


    //functions from FactItemListener, implemented here and called by the adapter
    override fun deleteFact(id: String) {
        factListViewModel.deleteFactFromDB(id)
    }

    override fun updateDataBase() {
        factListViewModel.readAllDB()
    }

    override fun shareFact(fact: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT, "Hey! Here is a funny Chuck Norris Fact just for you: \n \n" +
                        "${fact}\n \n " +
                        "For more jokes, download Chuck Norris Facts at: *playstore/ChuchNorrisFactsApp* "
            )
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}
