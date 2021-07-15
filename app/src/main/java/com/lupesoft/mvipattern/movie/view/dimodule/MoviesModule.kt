package com.lupesoft.mvipattern.movie.view.dimodule

import com.lupesoft.mvipattern.cart.dataAccess.repositories.ShoppingCartRepositoryRoom
import com.lupesoft.mvipattern.movie.model.dataAccess.repositories.MovieRepositoryRoom
import com.lupesoft.mvipattern.movie.model.repositories.MovieRepository
import com.lupesoft.mvipattern.movie.model.repositories.ShoppingCartRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent


@InstallIn(FragmentComponent::class)
@Module
abstract class MoviesModule {

    @Binds
    abstract fun provideMovieRepository(movieRepositoryRoom: MovieRepositoryRoom): MovieRepository

    @Binds
    abstract fun provideShoppingCartRepository(shoppingCartRepositoryRoom: ShoppingCartRepositoryRoom): ShoppingCartRepository

}