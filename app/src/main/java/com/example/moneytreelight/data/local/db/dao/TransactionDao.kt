package com.example.moneytreelight.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.moneytreelight.data.local.db.entity.TransactionEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transaction_entity WHERE account_id = :id")
    fun getAll(id: Int): Single<List<TransactionEntity>>

    @Insert
    fun insert(entity: List<TransactionEntity>): Completable

    @Query("DELETE FROM transaction_entity")
    fun deleteAll(): Completable
}