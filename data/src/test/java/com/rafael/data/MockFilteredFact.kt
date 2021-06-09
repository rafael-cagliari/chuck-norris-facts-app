package com.rafael.data

import com.rafael.data.model.ChuckNorrisResponse

class MockFilteredFact {
    fun getFilteredMockedResponse(category:String): ChuckNorrisResponse {
        return ChuckNorrisResponse(
            categories = listOf(category),
            createdAt = "2020-01-05 13:42:19.104863",
            iconUrl = "https://assets.chucknorris.host/img/avatar/chuck-norris.png",
            id = "qgshozvsrboyqdbqa-vz3q",
            updatedAt = "updatedAt=2020-01-05 13:42:19.104863",
            url = "https://api.chucknorris.io/jokes/qgshozvsrboyqdbqa-vz3q",
            value = "To Chuck Norris, everything contains a vulnerability"
        )
    }
}