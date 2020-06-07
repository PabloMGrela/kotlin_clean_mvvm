package com.grela.clean.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.grela.clean.BaseUnitTest
import com.grela.clean.Resource
import com.grela.clean.model.CountryModel
import com.grela.clean.model.CountryViewModel
import com.grela.data.datasource.SportRemoteDataSourceContract
import com.grela.data.model.CountryDataEntity
import com.grela.domain.DataResult
import com.grela.domain.SportRepositoryContract
import com.grela.domain.interactor.GetCountryUseCase
import com.grela.domain.interactor.Invoker
import com.grela.domain.model.CountryDomainEntity
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class CountryViewmodelTest : BaseUnitTest() {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val getCountries = mock<GetCountryUseCase>()
    private val countryRepository = mock<SportRepositoryContract>()
    private val sportRemoteDataSource = mock<SportRemoteDataSourceContract>()
    private val invoker = mock<Invoker>()
    private val observer = mock<Observer<Resource<List<CountryModel>>>>()

    private lateinit var viewModel: CountryViewModel

    @Before
    override fun setUp() {
        super.setUp()
        viewModel = CountryViewModel(
            invoker,
            getCountries
        )
        viewModel.countryModels.observeForever(observer)

    }

    @Test
    fun `should shows countries when interactor returns correct data`() {
        val countries = listOf<CountryDomainEntity>(mock())
        val countriesData = listOf<CountryDataEntity>(mock())
        val countryModels = listOf<CountryModel>(mock())

        given(countryRepository.getCountry()).willReturn(DataResult.Success(countries))
        given(sportRemoteDataSource.getSport()).willReturn(DataResult.Success(countriesData))
        runBlocking {
            getCountries.run {
                viewModel.load()
                verify(observer).onChanged(Resource.loading())
                verify(observer).onChanged(Resource.success(countryModels))
            }
        }
    }
}