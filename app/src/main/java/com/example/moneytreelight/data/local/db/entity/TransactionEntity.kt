package com.example.moneytreelight.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "transaction_entity")
data class TransactionEntity(

        @PrimaryKey
        @ColumnInfo(name = "id")
        @NotNull
        val id: Int,

        @ColumnInfo(name = "account_id")
        @NotNull
        val account_id: Int,

        @ColumnInfo(name = "category_id")
        @NotNull
        val category_id: Int,

        @ColumnInfo(name = "date")
        @NotNull
        val date: String,

        @ColumnInfo(name = "description")
        @NotNull
        val description: String,

        @ColumnInfo(name = "amount")
        @NotNull
        val amount: Float,

        @ColumnInfo(name = "month")
        @NotNull
        val month: String,
)