package com.example.moneytreelight.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.moneytreelight.MoneytreeApplication
import com.example.moneytreelight.di.component.DaggerFragmentComponent
import com.example.moneytreelight.di.component.FragmentComponent
import com.example.moneytreelight.di.module.FragmentModule
import javax.inject.Inject

/**
 * Reference for generics: https://kotlinlang.org/docs/reference/generics.html
 * Basically BaseFragment will take any class that extends BaseViewModel
 */
abstract class BaseFragment<VM : BaseViewModel> : Fragment() {

    @Inject
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildFragmentComponent())
        super.onCreate(savedInstanceState)
        setupObservers()
        viewModel.onCreate()
    }

    private fun buildFragmentComponent() =
            DaggerFragmentComponent
                    .builder()
                    .applicationComponent((requireContext().applicationContext as MoneytreeApplication).applicationComponent)
                    .fragmentModule(FragmentModule(this))
                    .build()


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? =
            inflater.inflate(provideLayoutId(), container, false)

    protected open fun setupObservers() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
    }

    @LayoutRes
    protected abstract fun provideLayoutId(): Int

    protected abstract fun injectDependencies(fragmentComponent: FragmentComponent)

    protected abstract fun setupView(view: View)

}