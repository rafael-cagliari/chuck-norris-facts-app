package com.rafael.domain.model

class MockFact {

    fun getChuckNorrisFact(): ChuckNorrisFact {
        return ChuckNorrisFact(
            categories = listOf("dev"),
            id = "qgshozvsrboyqdbqa-vz3q",
            value = "To Chuck Norris, everything contains a vulnerability"
        )
    }
}