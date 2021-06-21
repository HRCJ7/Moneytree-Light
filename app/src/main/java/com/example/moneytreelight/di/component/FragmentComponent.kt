package com.example.moneytreelight.di.component

import com.example.moneytreelight.di.FragmentScope
import com.example.moneytreelight.di.module.FragmentModule
import com.example.moneytreelight.ui.account.AccountsFragment
import com.example.moneytreelight.ui.transaction.TransactionFragment
import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {
    fun inject(fragment: AccountsFragment)
    fun inject(fragment: TransactionFragment)
}