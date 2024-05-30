package com.telema.malakisi.domain.data

import com.telema.malakisi.data.source.remote.MalakisiApi
import com.telema.malakisi.domain.model.MalakisiDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get


class MalakisiApiImpl (private val httpClient: HttpClient) : MalakisiApi {

    private val BASE_URL =
        "https://script.google.com/macros/s/AKfycbyPag-NzAQA8ye-Q9xY0cuQa5owLB0i46A75q0OSiMlveTdvdfcQo7p7U0-p0PEY-8nvA/"
    private val GET_DATA_ENDPOINT = "exec"

    override suspend fun getMalakisiData(): MalakisiDto =
        httpClient.get("$BASE_URL$GET_DATA_ENDPOINT").body()

}