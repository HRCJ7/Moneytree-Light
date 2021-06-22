package com.example.moneytreelight.data

import com.example.moneytreelight.data.repository.transaction.TransactionDataService
import com.example.moneytreelight.data.repository.transaction.TransactionRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class TransactionRepositoryTest {
    private lateinit var transactionDataService: TransactionDataService
    private lateinit var transactionRepository: TransactionRepository

    @Before
    fun setup() {
        transactionDataService = mock()
        transactionRepository = TransactionRepository(transactionDataService)
    }

    @After
    fun tearDown() {
        Mockito.reset(transactionDataService)
    }

    @Test
    fun test_fetchAccountData() {
        transactionRepository.fetchTransactionData(1)
        verify(transactionDataService, times(1)).fetchTransactionData(1)
    }
}