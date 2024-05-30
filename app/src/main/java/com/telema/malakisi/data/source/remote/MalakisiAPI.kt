package com.telema.malakisi.data.source.remote

import com.telema.malakisi.domain.model.MalakisiDto

interface MalakisiApi {

    suspend fun getMalakisiData(): MalakisiDto

}