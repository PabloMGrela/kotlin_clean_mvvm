package com.grela.clean.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.grela.clean.Resource
import com.grela.domain.DataResult
import com.grela.domain.interactor.GetCountryUseCase
import com.grela.domain.interactor.Invoker
import com.grela.domain.interactor.UseCaseInvoker

class CountryViewModel(
    private val invoker: Invoker,
    private val getCountryUseCase: GetCountryUseCase
) : ViewModel() {
    private val _countryModels = MutableLiveData<Resource<List<CountryModel>>>()

    val countryModels: LiveData<Resource<List<CountryModel>>>
        get() = _countryModels

    fun load() {
        invokeGetMovies()
    }

    private fun invokeGetMovies() {
        _countryModels.value = Resource.loading()
        invoker.execute(getCountryUseCase) {
            when (it) {
                is DataResult.Success -> _countryModels.value = Resource.success(it.r.toCountryModelList())
                is DataResult.Error -> _countryModels.value = Resource.error(Error())
            }
        }
    }
}