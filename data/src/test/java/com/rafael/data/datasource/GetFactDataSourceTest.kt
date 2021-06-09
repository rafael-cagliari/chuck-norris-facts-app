package com.rafael.data.datasource

import com.rafael.data.MockFact
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Test

class GetFactDataSourceTest {
    private val datasource: GetFactDataSource = mockk()

    @Test
    fun getFactSuccessfully() {
        val expected = Single.just(MockFact().getMockedResponse())

        every { datasource.getFact() } returns expected
        val response = datasource.getFact()

        //datasource returns a ChuckNorrisResponse object
        assert(response == expected)
    }
}