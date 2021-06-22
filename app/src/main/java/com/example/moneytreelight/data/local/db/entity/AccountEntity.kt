package com.example.moneytreelight.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "account_entity")
data class AccountEntity(

        @PrimaryKey
        @ColumnInfo(name = "id")
        @NotNull
        val id: Int,

        @ColumnInfo(name = "name")
        @NotNull
        val name: String,

        @ColumnInfo(name = "institution")
        @NotNull
        val institution: String,

        @ColumnInfo(name = "currency")
        @NotNull
        val currency: String,

        @ColumnInfo(name = "current_balance")
        @NotNull
        val currentBalance: Float,

        @ColumnInfo(name = "current_balance_in_base")
        @NotNull
        val currentBalanceInBase: Float,
)