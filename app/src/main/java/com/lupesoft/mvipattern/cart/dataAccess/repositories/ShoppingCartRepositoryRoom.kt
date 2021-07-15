package com.lupesoft.mvipattern.cart.dataAccess.repositories

import com.lupesoft.mvipattern.movie.model.repositories.ShoppingCartRepository
import com.lupesoft.mvppattern.movie.model.vo.Movie
import com.lupesoft.mvipattern.cart.dataAccess.daos.ShoppingCartDao
import com.lupesoft.mvipattern.movie.model.dataAccess.dtos.toDomainModel
import com.lupesoft.mvipattern.cart.dataAccess.entities.ShoppingCarEntity
import javax.inject.Inject

class ShoppingCartRepositoryRoom @Inject constructor(
    private val shoppingCartDao: ShoppingCartDao
) : ShoppingCartRepository {

    override fun getAllMoviesIntoShoppingCart(): List<Movie> {
        return shoppingCartDao.getAllMoviesIntoShoppingCart().toDomainModel()
    }

    override fun addMovie(idMovie: Int): Long {
        return shoppingCartDao.addMovie(ShoppingCarEntity(idMovie))
    }

    override fun deleteMovie(idMovie: Int): Int {
        return shoppingCartDao.deleteMovie(idMovie)
    }

    override fun deleteAllMovie(): Int {
        return shoppingCartDao.deleteAllMoviesIntoShoppingCart()
    }
}