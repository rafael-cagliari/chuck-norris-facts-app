package com.rafael.domain.repository

import com.rafael.domain.model.MockFact
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Test


class GetFactRepositoryTest {
    private val repository:GetFactRepository = mockk()
    @Test
    fun getFact() {
        val expected = Single.just(MockFact().getChuckNorrisFact())
        every { repository.getFact() } returns expected
        val response = repository.getFact()

        assert(response == expected)
    }

    @Test
    fun getFilteredFact(){
        val expected = Single.just(MockFact().getChuckNorrisFact())
        every {repository.getFilteredFact("dev")} returns expected
        val response = repository.getFilteredFact("dev")

        assert(response==expected)
        assert(response.blockingGet().categories.first()=="dev")
    }
}