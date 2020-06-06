package com.grela.clean

import com.grela.clean.DomainGenerator.givenAGetCountryUseCase
import com.grela.clean.InvokerInstruments.givenAnInvoker
import com.grela.domain.model.CountryDomainEntity
import com.grela.presentation.MainPresenter
import com.grela.presentation.MainViewTranslator

object PresenterGenerator {

    class MainPresenterCallbackResult : BaseCallbackResult<MainPresenterMethods>()

    enum class MainPresenterMethods {
        GET_COUNTRIES,
        SHOW_ERROR,
        SHOW_COUNTRY
    }

    fun givenAMainPresenter(mainPresenterCallbackResult: MainPresenterCallbackResult, isSuccess: Boolean) = MainPresenter(
        object : MainViewTranslator {
            override fun showError() {
                mainPresenterCallbackResult.putMethodCall(MainPresenterMethods.SHOW_ERROR)
            }

            override fun showCountry(r: List<CountryDomainEntity>) {
                mainPresenterCallbackResult.putMethodCall(MainPresenterMethods.SHOW_COUNTRY)
            }
        },
        givenAGetCountryUseCase(isSuccess),
        givenAnInvoker()
    )

}