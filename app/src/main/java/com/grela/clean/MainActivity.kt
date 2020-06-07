package com.grela.clean

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.grela.clean.model.CountryViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val countryViewModel: CountryViewModel by viewModel()

    lateinit var adapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = CountryAdapter {
            Toast.makeText(this, it.continent, Toast.LENGTH_LONG).show()
        }
        countryListView.adapter = adapter

        countryViewModel.countryModels.observe(this, Observer { countriesResource ->
            when (countriesResource.status) {
                Status.LOADING -> {

                }
                Status.ERROR -> {
                    showError()
                }
                Status.SUCCESS -> {
                    countriesResource.data?.let {
                        adapter.updateData(it)
                    }
                }
            }
        })
        countryViewModel.load()
    }

    fun showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
    }
}