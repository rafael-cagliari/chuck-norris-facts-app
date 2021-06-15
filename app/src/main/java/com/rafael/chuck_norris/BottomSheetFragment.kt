package com.rafael.chuck_norris

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rafael.chuck_norris.databinding.FragmentBottomSheetBinding
import com.rafael.chuck_norris.viewmodel.SearchFactViewModel


class BottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var binding: FragmentBottomSheetBinding
    val searchFactViewModel: SearchFactViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        binding = fragmentBinding


        binding.chipRandom.setOnClickListener { searchFactViewModel._category.value= null}
        binding.chipAnimal.setOnClickListener { searchFactViewModel._category.value= binding.chipAnimal.text.toString()}
        binding.chipCelebrity.setOnClickListener { searchFactViewModel._category.value= binding.chipCelebrity.text.toString()}
        binding.chipDev.setOnClickListener { searchFactViewModel._category.value= binding.chipDev.text.toString()}
        binding.chipExplicit.setOnClickListener { searchFactViewModel._category.value= binding.chipExplicit.text.toString()}
        binding.chipFashion.setOnClickListener { searchFactViewModel._category.value= binding.chipFashion.text.toString()}
        binding.chipFood.setOnClickListener { searchFactViewModel._category.value= binding.chipFood.text.toString()}
        binding.chipHistory.setOnClickListener { searchFactViewModel._category.value= binding.chipHistory.text.toString()}
        binding.chipMoney.setOnClickListener { searchFactViewModel._category.value= binding.chipMoney.text.toString()}
        binding.chipMovie.setOnClickListener { searchFactViewModel._category.value= binding.chipMovie.text.toString()}
        binding.chipMusic.setOnClickListener { searchFactViewModel._category.value= binding.chipMusic.text.toString()}
        binding.chipPolitical.setOnClickListener { searchFactViewModel._category.value= binding.chipPolitical.text.toString()}
        binding.chipReligion.setOnClickListener { searchFactViewModel._category.value= binding.chipReligion.text.toString()}
        binding.chipScience.setOnClickListener { searchFactViewModel._category.value= binding.chipScience.text.toString()}
        binding.chipSports.setOnClickListener { searchFactViewModel._category.value= binding.chipSports.text.toString()}
        binding.chipTravel.setOnClickListener { searchFactViewModel._category.value= binding.chipTravel.text.toString()}

        return fragmentBinding.root
    }
}
