package com.lupesoft.mvipattern.cart.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lupesoft.mvipattern.R
import com.lupesoft.mvipattern.cart.presenter.CartPresenter
import com.lupesoft.mvipattern.cart.view.CartView
import com.lupesoft.mvipattern.databinding.FragmentShoppingCartLayoutBinding
import com.lupesoft.mvipattern.shared.controler.extensions.isHide
import com.lupesoft.mvipattern.shared.controler.extensions.showMessage
import com.lupesoft.mvipattern.shared.domain.MovieState
import com.lupesoft.mvppattern.movie.model.vo.Movie
import com.lupesoft.mvppattern.presenter.adapters.MovieAdapter
import com.lupesoft.mvppattern.presenter.adapters.setDataMovie
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ShoppingCartFragment : Fragment(), CartView {

    @Inject
    lateinit var cartPresenter: CartPresenter

    private lateinit var binding: FragmentShoppingCartLayoutBinding

    private val liveDataDeleteMovies : MutableLiveData<String> = MutableLiveData()

    private val liveDataDeleteMovie : MutableLiveData<Int> = MutableLiveData()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoppingCartLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartPresenter.bind(this)
        subscribeActionShoppingCart()
        subscribeActionRemoveAllMovies()
    }


    private fun subscribeActionRemoveAllMovies() {
        with(binding) {
            fabRemoveAllMovies.setOnClickListener {
                loaderShopping.root.isHide(true)
                liveDataDeleteMovies.postValue("")
            }
        }
    }

    private fun subscribeActionShoppingCart() {
        with(binding) {
            shoppingCartMovieList.adapter = MovieAdapter(
                { _, movieId ->
                    liveDataDeleteMovie.postValue(movieId)
                }, menuRes = R.menu.popup_menu_shopping
            )
        }
    }

    override fun getLifecycleOwner(): LifecycleOwner = viewLifecycleOwner

    override fun render(state: MovieState) {
        when (state) {
            is MovieState.LoadingState -> renderLoadingState()
            is MovieState.DataState -> renderDataState(state.data)
            is MovieState.MessageState -> renderMessage(state.data)
            is MovieState.ErrorState -> renderMessage(state.data)
            is MovieState.FinishState -> renderFinishState()
            else -> {
                renderMessage()
            }
        }
    }

    override fun removeAllMoviesIntent(): MutableLiveData<String> = liveDataDeleteMovies

    override fun deleteMovieIntent(): MutableLiveData<Int> = liveDataDeleteMovie

    private fun renderDataState(data: List<Movie>?) {
        binding.shoppingCartMovieList.setDataMovie(data, binding.listEmptyCart)
        renderFinishState()
    }

    private fun renderLoadingState() {
        binding.loaderShopping.root.isHide(false)
    }

    private fun renderFinishState() {
        binding.loaderShopping.root.isHide(true)
    }

    private fun renderMessage(message: String? = null) {
        (message
            ?: requireContext().getString(R.string.something_unexpected_happened)
                ).showMessage(requireContext())
    }
}