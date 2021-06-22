package com.example.moneytreelight.ui.transaction

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moneytreelight.R
import com.example.moneytreelight.data.local.db.entity.TransactionEntity
import kotlinx.android.synthetic.main.recyclerview_item_account_group.view.rv_child
import kotlinx.android.synthetic.main.recyclerview_item_transaction_group.view.*

class TransactionGroupAdapter(private var transactionGroupList: List<TransactionGroup>, var context: Context, private var currency: String?) :
        RecyclerView.Adapter<TransactionGroupAdapter.TransactionHolder>() {

    override fun onBindViewHolder(holder: TransactionHolder, position: Int) {
        val transactionGroup = transactionGroupList[position]
        holder.bindAccountGroup(transactionGroup, currency)
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): TransactionHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_item_transaction_group, false)
        return TransactionHolder(inflatedView)
    }

    override fun getItemCount(): Int = transactionGroupList.size

    private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    class TransactionHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        private var transactionGroup: TransactionGroup? = null

        @SuppressLint("SetTextI18n")
        fun bindAccountGroup(transactionGroup: TransactionGroup, currency: String?) {
            this.transactionGroup = transactionGroup
            view.tv_month.text = getMonthAndYear(transactionGroup.month)
            view.tv_deposit.text =
                    currency + getDepositValue(transactionGroup.transactionList).toString()
            view.tv_withdrowal.text =
                    "-$currency" + kotlin.math.abs(getWithdrawalValue(transactionGroup.transactionList))
                            .toString()
            view.rv_child.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = currency?.let { TransactionAdapter(transactionGroup.transactionList, it) }
            }
        }

        /**
         * This method to use to get required month and year format which is shown in transaction page
         * @param dateString ex: 2017-08-22T00:00:00+09:00
         * @return ex: August 2017
         */
        private fun getMonthAndYear(date: String): String {
            val year = date.subSequence(0, 4)
            val month = date.subSequence(5, 7).toString().toInt()
            var monthInWord = ""
            when (month) {
                1 -> monthInWord = "January"
                2 -> monthInWord = "February"
                3 -> monthInWord = "March"
                4 -> monthInWord = "April"
                5 -> monthInWord = "May"
                6 -> monthInWord = "June"
                7 -> monthInWord = "July"
                8 -> monthInWord = "August"
                9 -> monthInWord = "September"
                10 -> monthInWord = "October"
                11 -> monthInWord = "November"
                12 -> monthInWord = "December"
            }
            return "$monthInWord $year"
        }

        /**
         * Calculate total deposited transaction in selected account
         */
        private fun getDepositValue(transactionList: List<TransactionEntity>): Float {
            var total = 0.0F
            transactionList.forEach {
                if (it.amount > 0) {
                    total += it.amount
                }
            }
            return total
        }


        /**
         * Calculate total withdrawal transaction in selected account
         */
        private fun getWithdrawalValue(transactionList: List<TransactionEntity>): Float {
            var total = 0.0F
            transactionList.forEach {
                if (it.amount < 0) {
                    total += it.amount
                }
            }
            return total
        }
    }
}