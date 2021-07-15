package com.lupesoft.mvipattern.cart.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.lupesoft.mvipattern.cart.view.CartView
import com.lupesoft.mvipattern.shared.domain.MovieState
import com.lupesoft.mvppattern.movie.model.vo.Movie
import com.lupesoft.mvppattern.shared.model.dataAccess.utils.Status
import com.lupesoft.mvppattern.shared.model.dataAccess.utils.response.Resource
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class CartPresenter @Inject constructor(
    private val cartInteractor: CartInteractor
) : OnCartListener {

    private lateinit var cartView: CartView

    fun bind(cartView: CartView) {
        this.cartView = cartView
        cartInteractor.onCartListener = this
        observerDeleteAllMovie()
        observerDeleteMovie()
        cartInteractor.getAllMoviesIntoShoppingCart()
    }

    private fun observerDeleteAllMovie() {
        cartView.removeAllMoviesIntent().observe(cartView.getLifecycleOwner(), {
            cartInteractor.deleteAllMovie()
        })
    }

    private fun observerDeleteMovie() {
        cartView.deleteMovieIntent().observe(cartView.getLifecycleOwner(), { movieId ->
            cartInteractor.deleteMovie(movieId)
        })
    }

    private fun <T> actionEvent(result: Resource<T>, successAction: () -> Unit) {
        when (result.status) {
            Status.LOADING -> cartView.render(MovieState.LoadingState)
            Status.SUCCESS -> {
                successAction.invoke()
            }
            Status.ERROR -> cartView.render(MovieState.ErrorState(result.message ?: ""))
        }
        if (result.status != Status.LOADING && !result.message.isNullOrBlank()) {
            cartView.render(MovieState.MessageState(result.message))
        }
    }

    override fun onGetAllMoviesIntoShoppingCart(allMovies: LiveData<Resource<List<Movie>>>) {
        allMovies.observe(cartView.getLifecycleOwner(), { result ->
            actionEvent(result) {
                result.data?.let {
                    cartView.render(MovieState.DataState(it))
                } ?: run { cartView.render(MovieState.ErrorState("")) }

            }
        })
    }

    override fun onDeleteMovie(success: LiveData<Resource<Int>>) {
        success.observe(cartView.getLifecycleOwner(), { result ->
            actionEvent(result) {
                cartInteractor.getAllMoviesIntoShoppingCart()
            }
        })
    }
}