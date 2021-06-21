package com.example.moneytreelight.data.model.transaction


data class NewTransaction(
        val id: Int,

        val account_id: Int,

        val category_id: Int,

        val date: String,

        val description: String,

        val amount: Float,

        val month: String
)