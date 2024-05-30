package com.telema.malakisi.di

import androidx.room.Room
import com.telema.malakisi.data.repository.MalakisiRepository
import com.telema.malakisi.data.source.local.MalakisiDatabase
import com.telema.malakisi.data.source.local.dao.MalakisiLocalDao
import com.telema.malakisi.data.source.remote.MalakisiApi
import com.telema.malakisi.domain.data.MalakisiApiImpl
import com.telema.malakisi.domain.repository.MalakisiRepositoryImpl
import com.telema.malakisi.ui.MainActivityViewModel
import com.telema.malakisi.ui.screen.DetailsProgramViewModel
import com.telema.malakisi.ui.screen.ListProgramScreenViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.UserAgent
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.appendIfNameAbsent
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val networkModule = module {

    factory<MalakisiApi> { MalakisiApiImpl(get()) }

    single {
        HttpClient {
            install(ContentNegotiation) {
                json(json = Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }

            install(HttpRequestRetry) {
                //function enables retrying a request if a 5xx response is received from a server and specifies the number of retries.
                retryOnServerErrors(5)
                //specifies an exponential delay between retries, which is calculated using the Exponential backoff algorithm.
                exponentialDelay()
                //If you want to add some additional params in header
                modifyRequest { request ->
                    request.headers.append("x-retry-count", 2.toString())
                }
            }

            //If you want to change user agent
            install(UserAgent) {
                agent = "Ktor"
            }

            install(DefaultRequest) {
                headers.appendIfNameAbsent("X-custom-header", "Some Value")
                contentType(ContentType.Application.Json)
            }

            ResponseObserver {
                val timeDifference = it.responseTime.timestamp - it.requestTime.timestamp
                val protocolVersion = it.version
            }
        }

    }
}


val localDBModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            MalakisiDatabase::class.java,
            "malakisi.db"
        ).fallbackToDestructiveMigration().build()
    }

    single<MalakisiLocalDao> {
        val database = get<MalakisiDatabase>()
        database.malakisiDao()
    }
}

val repositoryModule = module {
    factory<MalakisiRepository> { MalakisiRepositoryImpl(get(), get()) }
}

val viewModelModule = module {
    viewModel{
        MainActivityViewModel(get())
    }

    viewModel{
        ListProgramScreenViewModel(get())
    }

    viewModel{
        DetailsProgramViewModel(get())
    }
}