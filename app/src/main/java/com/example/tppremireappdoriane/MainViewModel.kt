package com.example.tppremireappdoriane

import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {



    val movies = MutableStateFlow<List<Movie>>(listOf())
    val tvs = MutableStateFlow<List<TV>>(listOf())
    val personnes = MutableStateFlow<List<Personne>>(listOf())
    val tv = MutableStateFlow<TVDetails>(TVDetails())
    val film = MutableStateFlow<FilmDetails>(FilmDetails())



    val api_key = "a8f00cd3af0d5728d07aa5c481f326a3"
    val language = "fr"

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(TmdbAPI::class.java);

    fun getMovies() {
        viewModelScope.launch {
            movies.value = retrofit.derniersFilms(api_key, language).results
        }
    }

    fun getSeries() {
        viewModelScope.launch {
            tvs.value = retrofit.dernieresSeries(api_key, language).results
        }
    }

    fun getPersonnes() {
        viewModelScope.launch {
            personnes.value = retrofit.dernieresPersonnes(api_key, language).results
        }
    }


    fun searchMovies(motcle: String) {
        viewModelScope.launch {
            movies.value = retrofit.getFilmParMotCle(api_key, motcle, language).results
        }
    }

    fun searchSeries(motcle: String) {
        viewModelScope.launch {
            tvs.value = retrofit.getTVParMotCle(api_key, motcle, language).results
        }
    }

    fun searchPersonnes(motcle: String) {
        viewModelScope.launch {
            personnes.value = retrofit.getPersonneParMotCle(api_key, motcle, language).results
        }
    }

    fun getTVdetails(tvid: String) {
        viewModelScope.launch {
            tv.value = retrofit.getDetailsTV(tvid, api_key, language)
        }
    }

    fun getFilmdetails(tvid: String) {
        viewModelScope.launch {
            film.value = retrofit.getDetailsFilms(tvid, api_key, language)
        }
    }



}