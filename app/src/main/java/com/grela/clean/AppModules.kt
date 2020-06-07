package com.grela.clean

import android.app.Activity
import com.grela.data.DataModules
import com.grela.domain.DomainModules
import com.grela.remote_datasource.RemoteModules
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

object AppModules {
    inline fun <reified T : Any> Activity.injectActivity(): Lazy<T> = inject { parametersOf(this) }

    val modules = listOf(
        DataModules.modules,
        DomainModules.modules,
        RemoteModules.modules,
        ViewModules.modules
    )
}