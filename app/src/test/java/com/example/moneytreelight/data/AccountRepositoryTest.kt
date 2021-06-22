package com.example.moneytreelight.data

import com.example.moneytreelight.data.repository.account.AccountDataService
import com.example.moneytreelight.data.repository.account.AccountRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.*
import org.mockito.Mockito

class AccountRepositoryTest {
    private lateinit var accountDataService: AccountDataService
    private lateinit var accountRepository: AccountRepository

    @Before
    fun setup() {
        accountDataService = mock()
        accountRepository = AccountRepository(accountDataService)
    }

    @After
    fun tearDown() {
        Mockito.reset(accountDataService)
    }

    @Test
    fun test_fetchAccountData() {
        accountRepository.fetchAccountData()
        verify(accountDataService, times(1)).fetchAccountData()
    }
}