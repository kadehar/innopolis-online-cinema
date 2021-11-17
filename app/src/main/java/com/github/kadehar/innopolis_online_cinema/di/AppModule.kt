package com.github.kadehar.innopolis_online_cinema.di

import com.github.kadehar.innopolis_online_cinema.base.consts.Constants.BASE_MOVIES_URL
import com.github.kadehar.innopolis_online_cinema.base.httpCache10Mb
import com.github.kadehar.innopolis_online_cinema.base.okHttp
import com.github.kadehar.innopolis_online_cinema.data.api.MoviesApi
import com.github.kadehar.innopolis_online_cinema.data.api.MoviesRemoteSource
import com.github.kadehar.innopolis_online_cinema.data.api.MoviesRepository
import com.github.kadehar.innopolis_online_cinema.data.api.MoviesRepositoryImpl
import com.github.kadehar.innopolis_online_cinema.domain.MoviesInteractor
import com.github.kadehar.innopolis_online_cinema.features.films_screen.ui.MoviesViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val appModule = module {
    single<OkHttpClient> {
        okHttp(cache = httpCache10Mb(androidContext()))
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_MOVIES_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<MoviesApi> {
        get<Retrofit>().create()
    }

    single<MoviesRemoteSource> {
        MoviesRemoteSource(get<MoviesApi>())
    }

    single<MoviesRepository> {
        MoviesRepositoryImpl(get<MoviesRemoteSource>())
    }

    single<MoviesInteractor> {
        MoviesInteractor(get<MoviesRepository>())
    }

    viewModel<MoviesViewModel> {
        MoviesViewModel(get<MoviesInteractor>())
    }
}



