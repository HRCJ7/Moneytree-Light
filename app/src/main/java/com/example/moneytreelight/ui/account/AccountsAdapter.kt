package com.example.moneytreelight.ui.account

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.moneytreelight.R
import com.example.moneytreelight.data.local.db.entity.AccountEntity
import kotlinx.android.synthetic.main.recyclerview_item_account.view.*

class AccountsAdapter(private var accountList: List<AccountEntity>, private var transactionListener: TransactionListener) :
        RecyclerView.Adapter<AccountsAdapter.AccountHolder>() {

    override fun onBindViewHolder(holder: AccountHolder, position: Int) {
        val account = accountList[position]
        holder.bindAccountGroup(account, transactionListener)
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): AccountHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_item_account, false)
        return AccountHolder(inflatedView)
    }

    override fun getItemCount(): Int = accountList.size

    private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    class AccountHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var account: AccountEntity? = null
        private var transactionListener: TransactionListener? = null

        init {
            v.setOnClickListener(this)
        }

        @SuppressLint("SetTextI18n")
        fun bindAccountGroup(account: AccountEntity, transactionListener: TransactionListener) {
            this.account = account
            this.transactionListener = transactionListener
            view.tv_name.text = account.name
            view.tv_amount.text = account.currency + account.currentBalance
        }

        override fun onClick(v: View?) {
            account?.let { transactionListener?.onClickListener(it) }
        }
    }
}