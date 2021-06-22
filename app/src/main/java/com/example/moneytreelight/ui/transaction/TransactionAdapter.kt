package com.example.moneytreelight.ui.transaction

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.moneytreelight.R
import com.example.moneytreelight.data.local.db.entity.TransactionEntity
import kotlinx.android.synthetic.main.recyclerview_item_transaction.view.*

class TransactionAdapter(private var transactionList: List<TransactionEntity>, var currency: String) :
        RecyclerView.Adapter<TransactionAdapter.TransactionHolder>() {

    @ExperimentalStdlibApi
    override fun onBindViewHolder(holder: TransactionHolder, position: Int) {
        val account = transactionList[position]
        holder.bindAccountGroup(account, currency)
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): TransactionHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_item_transaction, false)
        return TransactionHolder(inflatedView)
    }

    override fun getItemCount(): Int = transactionList.size

    private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    class TransactionHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        private var transaction: TransactionEntity? = null

        @ExperimentalStdlibApi
        @SuppressLint("SetTextI18n")
        fun bindAccountGroup(transaction: TransactionEntity, currency: String) {
            this.transaction = transaction
            view.tv_date.text = getMonthAndYear(transaction.date)
            view.tv_description.text = getDecodedString(transaction.description)
            if (transaction.amount < 0) {
                view.tv_amount.text = "-" + currency + kotlin.math.abs(transaction.amount)
            } else {
                view.tv_amount.text = currency + kotlin.math.abs(transaction.amount)
            }
        }

        private fun getMonthAndYear(dateString: String): String {
            val date = dateString.subSequence(8, 10).toString().toInt()
            var dateInWord = ""
            when (date) {
                1 -> dateInWord = "1st"
                2 -> dateInWord = "2nd"
                3 -> dateInWord = "3rd"
                in 4..31 -> {
                    dateInWord = date.toString() + "th"
                }
            }
            return dateInWord
        }

        @ExperimentalStdlibApi
        private fun getDecodedString(text: String): String {
            val byteArray: ByteArray = text.toByteArray()
            return byteArray.decodeToString()
        }
    }
}