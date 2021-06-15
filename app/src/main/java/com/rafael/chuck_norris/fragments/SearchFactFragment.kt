package com.rafael.chuck_norris.fragments
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.rafael.chuck_norris.R
import com.rafael.chuck_norris.databinding.FragmentSearchFactBinding
import com.rafael.chuck_norris.viewmodel.SearchFactViewModel
import com.rafael.domain.model.ChuckNorrisFact
import org.koin.android.ext.android.bind


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

        return fragmentBinding.root
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchFactViewModel.retrievedFact.observe(
            viewLifecycleOwner,
            { fact ->
                binding.addFactToDatabase.setBackgroundColor(resources.getColor(R.color.design_default_color_primary_dark))
                binding.addFactToDatabase.isClickable=true
                binding.retrievedFact.text = fact.value; binding.progressBar.visibility = View.GONE
            }
        )

        searchFactViewModel.getFactException.observe(
            viewLifecycleOwner, { exception ->
                Toast.makeText(context, exception, Toast.LENGTH_LONG).show()
                binding.addFactToDatabase.setBackgroundColor(resources.getColor(android.R.color.darker_gray))
                binding.addFactToDatabase.isClickable=false
                binding.progressBar.visibility=View.GONE
            }
        )

        searchFactViewModel.addToDbResult.observe(
            viewLifecycleOwner, {response ->
                if(response.toInt()!=-1) Toast.makeText(context, "This fact was added to your list.", Toast.LENGTH_LONG).show()
            }
        )

        searchFactViewModel.category.observe(
            viewLifecycleOwner, { category ->
                if(category==null)binding.selectedCategory.text="Random"
                else binding.selectedCategory.text = category }
        )


         binding.searchFact.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
             binding.retrievedFact.text=""
            if (searchFactViewModel.category.value == null) searchFactViewModel.getFact()
            else searchFactViewModel.category.value?.lowercase()
                ?.let { it1 -> searchFactViewModel.getFilteredFact(it1) }
        }

        binding.bottomSheet.setOnClickListener {
            findNavController().navigate(SearchFactFragmentDirections.actionSearchFactFragmentToBottomSheetFragment())
        }

        binding.addFactToDatabase.setOnClickListener {
            binding.addFactToDatabase.setBackgroundColor(resources.getColor(android.R.color.darker_gray))
            binding.addFactToDatabase.isClickable=false
            searchFactViewModel.retrievedFact.value?.let { it1 ->
                searchFactViewModel.addFactToDB(
                    it1
                )
            } }

        binding.returnToList.setOnClickListener {
            searchFactViewModel.reset()
            findNavController().navigate(SearchFactFragmentDirections.actionSearchFactFragmentToFactsListFragment())

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        searchFactViewModel.reset()
    }


}
