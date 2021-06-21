package com.example.moneytreelight.ui.account

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moneytreelight.R
import kotlinx.android.synthetic.main.recyclerview_item_account_group.view.*

class AccountsGroupAdapter(private var accountGroupList: List<AccountGroup>, var context: Context, var transactionListener: TransactionListener) :
    RecyclerView.Adapter<AccountsGroupAdapter.AccountHolder>() {

    override fun onBindViewHolder(holder: AccountHolder, position: Int) {
        val accountGroup = accountGroupList[position]
        holder.bindAccountGroup(accountGroup, transactionListener)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AccountHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_item_account_group, false)
        return AccountHolder(inflatedView)
    }

    override fun getItemCount(): Int = accountGroupList.size

    private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    class AccountHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        private var accountGroup: AccountGroup? = null

        fun bindAccountGroup(accountGroup: AccountGroup, transactionListener: TransactionListener) {
            this.accountGroup = accountGroup
            view.institution.text = accountGroup.institution

            view.rv_child.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = AccountsAdapter(accountGroup.accountList, transactionListener)
            }
        }
    }
}