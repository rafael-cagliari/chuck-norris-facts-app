package com.rafael.chuck_norris

import com.rafael.chuck_norris.viewmodel.FactListViewModel
import com.rafael.chuck_norris.viewmodel.SearchFactViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { SearchFactViewModel(get(), get(), get()) }

    viewModel { FactListViewModel(get(), get())}

}