package com.app.movienetflix.movie.services

import androidx.lifecycle.MutableLiveData
import com.app.movienetflix.movie.Movie
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(val movieAPI: MovieAPI) {
    var movie: MutableLiveData<Movie> = MutableLiveData<Movie>()
    var movieList:MutableLiveData<List<Movie>> = MutableLiveData<List<Movie>>()
    fun getMovie(id: String) {
        movieAPI.getMovieById(id).enqueue(object : Callback<Movie> {
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                println(t.localizedMessage)
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                val response = response.body()
                val gson = Gson()
                val stringResponse = gson.toJson(response)
                val movieObject = gson.fromJson<Movie>(stringResponse,
                    Movie::class.java)
                movie.value = movieObject
            }
        })
    }

    fun getAllMovie(){
        movieAPI.getAllMovie().enqueue(object : Callback<List<Movie>> {
            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                println(t.localizedMessage)
                println(t.printStackTrace())
            }

            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                val response = response.body()
                val gson = Gson()
                val stringResponse = gson.toJson(response)
                val movieObject:List<Movie> = gson.fromJson(stringResponse,Array<Movie>::class.java).toList()
                movieList.value = movieObject
            }
        })
    }

    fun saveMovie(movie: Movie){
        movieAPI.createMovie(movie).enqueue(object : Callback<Movie>{
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if(response.code() == 200){
                    println("SUCCESS")
                }
            }
        })
    }
}