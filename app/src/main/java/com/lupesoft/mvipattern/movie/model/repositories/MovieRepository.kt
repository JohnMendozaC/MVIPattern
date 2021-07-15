package com.lupesoft.mvipattern.movie.model.repositories

import com.lupesoft.mvppattern.movie.model.vo.Movie
import com.lupesoft.mvipattern.movie.model.dataAccess.entities.MovieEntity

interface MovieRepository {

    fun getAllMovies(): List<Movie>

    fun insertAll(entities: List<MovieEntity>)
}