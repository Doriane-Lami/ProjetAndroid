package com.example.tppremireappdoriane

import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbAPI {
    @GET("trending/movie/week")
    suspend fun derniersFilms(@Query("api_key") api_key: String): Movies

    @GET("trending/tv/week")
    suspend fun dernieresSeries(@Query("api_key") api_key: String): TVs

    @GET("trending/person/week")
    suspend fun dernieresPersonnes(@Query("api_key") api_key: String): Personnes

    @GET("search/movie")
    suspend fun getFilmParMotCle(@Query("api_key") api_key: String, @Query("query") motcle: String): Movies

    @GET("search/tv")
    suspend fun getTVParMotCle(@Query("api_key") api_key: String, @Query("query") motcle: String): TVs

    @GET("search/person")
    suspend fun getPersonneParMotCle(@Query("api_key") api_key: String, @Query("query") motcle: String): Personnes

    @GET("tv/"id"?append_to_response=credits")
    suspend fun getDetailsTV(@Query("api_key") api_key: String, @Query("id") id: String): TVDetails
}

//https://api.themoviedb.org/3/movie/157336?api_key=a8f00cd3af0d5728d07aa5c481f326a3&append_to_response=overview
