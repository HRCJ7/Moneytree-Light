package com.example.moneytreelight.ui.transaction

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moneytreelight.R
import com.example.moneytreelight.di.component.FragmentComponent
import com.example.moneytreelight.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_transaction.*
import kotlinx.android.synthetic.main.fragment_transaction.progressBar
import kotlinx.android.synthetic.main.fragment_transaction.tv_total
import kotlinx.android.synthetic.main.fragment_transaction.view.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class TransactionFragment : BaseFragment<TransactionViewModel>() {

    var name: String? = null
    var currency: String? = null

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun provideLayoutId(): Int = R.layout.fragment_transaction

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    @SuppressLint("SetTextI18n")
    override fun setupObservers() {

        viewModel.transactionGroups.observe(this, Observer {
            rv_transaction.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = TransactionGroupAdapter(it, context, currency)
            }
        })

        viewModel.totalValue.observe(this, Observer {
            tv_total.text = currency + it.toString()
        })

        viewModel.loading.observe(this, Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    override fun setupView(view: View) {
        view.tv_name.text = name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val arg = arguments?.let { TransactionFragmentArgs.fromBundle(it) }
        name = arg?.name
        currency = arg?.currency

        // Here check data is stored or not.According to that data fetching method get decided.
        // Two types of data source is available 1.File 2.Local database
        if (sharedPreferences.getBoolean("TRANSACTIONS_STORED", false)) {
            viewModel.fetchTransactionDataFromDatabase(arg?.id)
        } else {
            // Need to access two json files to fetch transaction data.
            viewModel.fetchTransactionDataFromFile(1)
            viewModel.fetchTransactionDataFromFile(2)
        }
    }
}