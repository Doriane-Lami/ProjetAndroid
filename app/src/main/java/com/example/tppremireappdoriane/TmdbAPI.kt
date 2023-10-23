package com.example.tppremireappdoriane

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbAPI {
    @GET("trending/movie/week")
    suspend fun derniersFilms(@Query("api_key") api_key: String, @Query("language") language: String): Movies

    @GET("trending/tv/week")
    suspend fun dernieresSeries(@Query("api_key") api_key: String, @Query("language") language: String): TVs

    @GET("trending/person/week")
    suspend fun dernieresPersonnes(@Query("api_key") api_key: String, @Query("language") language: String): Personnes2

    @GET("search/movie")
    suspend fun getFilmParMotCle(@Query("api_key") api_key: String, @Query("query") motcle: String, @Query("language") language: String): Movies

    @GET("search/tv")
    suspend fun getTVParMotCle(@Query("api_key") api_key: String, @Query("query") motcle: String, @Query("language") language: String): TVs

    @GET("search/person")
    suspend fun getPersonneParMotCle(@Query("api_key") api_key: String, @Query("query") motcle: String, @Query("language") language: String): Personnes2

    @GET("tv/{id}?append_to_response=credits")
    suspend fun getDetailsTV(@Path("id") id: String, @Query("api_key") api_key: String, @Query("language") language: String): TVDetails

    @GET("movie/{id}?append_to_response=credits")
    suspend fun getDetailsFilms(@Path("id") id: String, @Query("api_key") api_key: String, @Query("language") language: String): FilmDetails

}

