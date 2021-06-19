package com.rafael.data.datasource

import com.rafael.data.MockFilteredFact
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Test

class GetFilteredFactDataSourceTest {
    private val datasource:GetFactDataSource = mockk()

    @Test
    fun getFilteredFactSuccessfully(){
        val category = "food"
        val expected = Single.just(MockFilteredFact().getFilteredMockedResponse(category))

        every { datasource.getFilteredFact(category) } returns expected

        val response = datasource.getFilteredFact(category)

        //datasource returns a ChuckNorrisResponse object
        assert(response==expected)

        //the ChuckNorrisResponse object's categories property first item equal the category parameter passed
        assert(response.blockingGet().categories?.first() ==category)
    }
}