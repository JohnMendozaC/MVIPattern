package com.lupesoft.mvipattern.shared.domain

import com.lupesoft.mvppattern.movie.model.vo.Movie

sealed class MovieState {
  object LoadingState : MovieState()
  data class DataState(val data: List<Movie>) : MovieState()
  data class MessageState(val data: String) : MovieState()
  data class ErrorState(val data: String) : MovieState()
  data class ConfirmationState(val movie: Movie) : MovieState()
  object FinishState : MovieState()
}