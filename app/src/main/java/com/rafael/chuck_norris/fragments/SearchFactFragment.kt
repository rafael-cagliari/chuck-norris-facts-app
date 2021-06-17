package com.rafael.chuck_norris.fragments

import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rafael.chuck_norris.R
import com.rafael.chuck_norris.databinding.FragmentSearchFactBinding
import com.rafael.chuck_norris.viewmodel.SearchFactViewModel


class SearchFactFragment : Fragment() {

    private lateinit var binding: FragmentSearchFactBinding
    private val searchFactViewModel: SearchFactViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
                enableAddToListButton()
                factCardSwitch("visible")
                setFactText(fact.value)
                progressBarSwitch("gone")
                textSizeAdjust()
            }
        )

        searchFactViewModel.getFactException.observe(
            viewLifecycleOwner, { exception ->
                Toast.makeText(context, exception, Toast.LENGTH_LONG).show()
                disableAddToListButton()
                progressBarSwitch("gone")
            }
        )

        searchFactViewModel.addToDbResult.observe(
            viewLifecycleOwner, { response ->
                addToDatabaseResponse(response)
            }
        )

        searchFactViewModel.category.observe(
            viewLifecycleOwner, { category ->
               setCategory(category)
            }
        )

        binding.searchFact.setOnClickListener {
            progressBarSwitch("visible")
            factCardSwitch("invisible")
            setFactText("")
            getFilteredFact()
        }


        binding.addFactToDatabase.setOnClickListener {
            disableAddToListButton()
            addFactToDatabase()
        }

        binding.returnToList.setOnClickListener {
            searchFactViewModel.reset()
            findNavController().navigate(SearchFactFragmentDirections.actionSearchFactFragmentToFactsListFragment())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.filter -> {
                findNavController().navigate(SearchFactFragmentDirections.actionSearchFactFragmentToBottomSheetFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        searchFactViewModel.reset()
    }

    private fun enableAddToListButton() {
        binding.addFactToDatabase.setBackgroundColor(resources.getColor(R.color.design_default_color_primary_dark))
        binding.addFactToDatabase.isClickable = true
    }

    private fun disableAddToListButton() {
        binding.addFactToDatabase.setBackgroundColor(resources.getColor(android.R.color.darker_gray))
        binding.addFactToDatabase.isClickable = false
    }

    private fun textSizeAdjust() {
        if(searchFactViewModel.retrievedFact.value?.value.toString().length >= 80)
            binding.retrievedFactText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15.toFloat())
    }

    private fun progressBarSwitch(visibility:String){
       when(visibility){
           "gone" -> binding.progressBar.visibility=View.GONE
           "visible" -> binding.progressBar.visibility=View.VISIBLE
            else -> return
       }
    }

    private fun factCardSwitch(visibility: String){
        when(visibility){
            "invisible" -> binding.retrievedFactCard.visibility=View.INVISIBLE
            "visible" -> binding.retrievedFactCard.visibility=View.VISIBLE
            else -> return
        }
    }

    private fun setFactText(text:String){
        binding.retrievedFactText.text=text
    }

    private fun setCategory(category:String){
        if (category == "") binding.selectedCategory.text = "Random"
        else binding.selectedCategory.text = category
    }

    private fun getFilteredFact(){
        if (searchFactViewModel.category.value == "") searchFactViewModel.getFact()
        else searchFactViewModel.category.value?.lowercase()
            ?.let { it1 -> searchFactViewModel.getFilteredFact(it1) }
    }

    private fun addFactToDatabase(){
        searchFactViewModel.retrievedFact.value?.let { it1 ->
            searchFactViewModel.addFactToDB(
                it1
            )
        }
    }

    private fun addToDatabaseResponse(response:Long){
        if (response.toInt() != -1) Toast.makeText(
            context,
            "This fact was added to your list.",
            Toast.LENGTH_LONG
        ).show()
    }

}
