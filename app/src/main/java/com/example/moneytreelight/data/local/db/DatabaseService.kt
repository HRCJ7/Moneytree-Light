package com.example.moneytreelight.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moneytreelight.data.local.db.dao.AccountDao
import com.example.moneytreelight.data.local.db.dao.TransactionDao
import com.example.moneytreelight.data.local.db.entity.AccountEntity
import com.example.moneytreelight.data.local.db.entity.TransactionEntity
import javax.inject.Singleton

@Singleton
@Database(
    entities = [
        AccountEntity::class,
        TransactionEntity::class
    ],
    exportSchema = false,
    version = 1
)
abstract class DatabaseService : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun transactionDao(): TransactionDao
}