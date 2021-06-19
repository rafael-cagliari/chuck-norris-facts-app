package com.rafael.domain.repository

import com.rafael.domain.model.ChuckNorrisFact
import com.rafael.domain.model.MockFact
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Test

class DataBaseRepositoryTest {

    private val repository:DataBaseRepository = mockk()
    @Test
    fun getFact() {
        val expected = Single.just(1.toLong())
        every { repository.addFactToDB(MockFact().getChuckNorrisFact()) } returns expected
        val response = repository.addFactToDB(MockFact().getChuckNorrisFact())
        assert(response == expected)
    }

    @Test
    fun deleteFactFromDB(){
        val expected = Single.just(1)
        every {repository.deleteFactFromDB("qgshozvsrboyqdbqa-vz3q")} returns expected
        val response = repository.deleteFactFromDB("qgshozvsrboyqdbqa-vz3q")
        assert(response==expected)
    }

    @Test
    fun readAllDataBase(){
        val expected = Single.just(listOf(MockFact().getChuckNorrisFact()))
        every {repository.readAllDB()} returns expected
        val response = repository.readAllDB()
        assert(response==expected)
    }

}

