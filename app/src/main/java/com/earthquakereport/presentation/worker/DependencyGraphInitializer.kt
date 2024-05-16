package com.earthquakereport.presentation.worker

import android.content.Context
import androidx.startup.Initializer
import com.earthquakereport.presentation.di.InitializerEntryPoint

class DependencyGraphInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        //this will lazily initialize ApplicationComponent before Application's `onCreate`
        InitializerEntryPoint.resolve(context)
        return
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }

}