package com.example.moneytreelight.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.moneytreelight.data.local.db.entity.AccountEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


@Dao
interface AccountDao {

    @Query("SELECT * FROM account_entity")
    fun getAll(): Single<List<AccountEntity>>

    @Insert
    fun insert(entity: List<AccountEntity>): Completable

}