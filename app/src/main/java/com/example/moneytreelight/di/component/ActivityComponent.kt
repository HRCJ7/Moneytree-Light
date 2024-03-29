package com.example.moneytreelight.di.component

import com.example.moneytreelight.di.ActivityScope
import com.example.moneytreelight.di.module.ActivityModule
import com.example.moneytreelight.ui.main.MainActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {
    fun inject(activity: MainActivity)

}