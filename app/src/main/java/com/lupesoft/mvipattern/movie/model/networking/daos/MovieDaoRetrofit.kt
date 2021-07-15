package com.lupesoft.mvipattern.movie.model.networking.daos

import com.lupesoft.mvipattern.BuildConfig.ApiKey
import com.lupesoft.mvipattern.movie.model.networking.vos.MoviesVo
import retrofit2.Response
import retrofit2.http.GET

interface MovieDaoRetrofit {
    @GET("list/3?api_key=${ApiKey}&language=en-US")
    suspend fun getAllMovies(): Response<MoviesVo>
}