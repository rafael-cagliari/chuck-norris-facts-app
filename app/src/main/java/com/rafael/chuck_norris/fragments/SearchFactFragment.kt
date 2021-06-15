package com.rafael.chuck_norris.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rafael.chuck_norris.R
import com.rafael.chuck_norris.databinding.FragmentFactsListBinding
import com.rafael.chuck_norris.databinding.FragmentSearchFactBinding
import com.rafael.chuck_norris.viewmodel.SearchFactViewModel
import com.rafael.domain.model.ChuckNorrisFact
import java.util.*


class SearchFactFragment : Fragment() {

    lateinit var binding: FragmentSearchFactBinding
    val searchFactViewModel: SearchFactViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentSearchFactBinding.inflate(inflater, container, false)
        binding = fragmentBinding


        binding.searchFact.setOnClickListener {
            if(searchFactViewModel.category.value=="") searchFactViewModel.getFact()
           else searchFactViewModel.category.value?.lowercase()?.let { it1 -> searchFactViewModel.getFilteredFact(it1) }
        }

        return fragmentBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        searchFactViewModel.retrievedFact.observe(
            viewLifecycleOwner, {fact -> binding.retrievedFact.text=fact.value}
        )

        searchFactViewModel.category.observe(
            viewLifecycleOwner, {category -> binding.selectedCategory.text=category}
        )

        binding.bottomSheet.setOnClickListener {
            findNavController().navigate(SearchFactFragmentDirections.actionSearchFactFragmentToBottomSheetFragment())
        }

        binding.addFactToDatabase.setOnClickListener {
            searchFactViewModel.retrievedFact.value?.let { it1 ->
                searchFactViewModel.addFactToDB(
                    it1
                )
            }
        }
    }


}
