package com.example.moneytreelight

import android.app.Application
import com.example.moneytreelight.di.component.ApplicationComponent
import com.example.moneytreelight.di.component.DaggerApplicationComponent
import com.example.moneytreelight.di.module.ApplicationModule

class MoneytreeApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}