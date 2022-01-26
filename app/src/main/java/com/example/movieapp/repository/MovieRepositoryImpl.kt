package com.example.movieapp.repository

import com.example.movieapp.core.InternetCheck
import com.example.movieapp.data.local.LocalMovieDataSource
import com.example.movieapp.data.model.MovieList
import com.example.movieapp.data.model.toMovieEntity
import com.example.movieapp.data.remote.RemoteMovieDataSource

class MovieRepositoryImpl(
    private val dataSourceRemote: RemoteMovieDataSource,
    private val dataSourceLocal: LocalMovieDataSource
) : MovieRepository {

    override suspend fun getUpcomingMovies(): MovieList {
        return if(InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getUpComingMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("upcoming"))
            }
            return dataSourceLocal.getUpcomingMovies()
        }else {
            return dataSourceLocal.getUpcomingMovies()
        }
    }

    override suspend fun getTopRatedMovies(): MovieList {
        return if(InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getTopRatedMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("toprated"))
            }
            return dataSourceLocal.getTopRatedMovies()
        }else{
            return dataSourceLocal.getTopRatedMovies()
        }
    }

    override suspend fun getPopularMovies(): MovieList {
        return if(InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getPopularMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("popular"))
            }
            return dataSourceLocal.getPopularMovies()
        }else{
            return dataSourceLocal.getPopularMovies()
        }
    }
}