package com.lupesoft.mvipattern.cart.view.dimodule


import com.lupesoft.mvipattern.cart.presenter.CartInteractor
import com.lupesoft.mvipattern.movie.model.networking.daos.MovieDaoRetrofit
import com.lupesoft.mvipattern.movie.model.repositories.MovieRepository
import com.lupesoft.mvipattern.movie.model.repositories.ShoppingCartRepository
import com.lupesoft.mvipattern.movie.presenter.MovieInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@InstallIn(FragmentComponent::class)
@Module
class CartModule {

    @Provides
    fun provideMovieModel(
        movieRepository: MovieRepository,
        movieDaoRetrofit: MovieDaoRetrofit,
        shoppingCartRepository: ShoppingCartRepository
    ): MovieInteractor {
        return MovieInteractor(
            movieRepository,
            movieDaoRetrofit,
            shoppingCartRepository
        )
    }

    @Provides
    fun provideShoppingCartModel(
        shoppingCartRepository: ShoppingCartRepository
    ): CartInteractor {
        return CartInteractor(shoppingCartRepository)
    }
}