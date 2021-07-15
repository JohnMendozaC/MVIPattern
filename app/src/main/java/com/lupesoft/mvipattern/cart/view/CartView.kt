package com.lupesoft.mvipattern.cart.view

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lupesoft.mvipattern.shared.domain.MovieState
import com.lupesoft.mvppattern.movie.model.vo.Movie

interface CartView {
    fun getLifecycleOwner(): LifecycleOwner
    fun render(state: MovieState)
    fun removeAllMoviesIntent(): MutableLiveData<String>
    fun deleteMovieIntent(): MutableLiveData<Int>
}