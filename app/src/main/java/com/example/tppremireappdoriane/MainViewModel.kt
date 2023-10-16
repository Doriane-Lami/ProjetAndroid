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
    val tvDetails = MutableStateFlow<TVDetails>(TVDetails())

    val api_key = "a8f00cd3af0d5728d07aa5c481f326a3"

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(TmdbAPI::class.java);

    fun getMovies() {
        viewModelScope.launch {
            movies.value = retrofit.derniersFilms(api_key).results
        }
    }

    fun getSeries() {
        viewModelScope.launch {
            tvs.value = retrofit.dernieresSeries(api_key).results
        }
    }

    fun getPersonnes() {
        viewModelScope.launch {
            personnes.value = retrofit.dernieresPersonnes(api_key).results
        }
    }


    fun searchMovies(motcle: String) {
        viewModelScope.launch {
            movies.value = retrofit.getFilmParMotCle(api_key, motcle).results
        }
    }

    fun getTVdetails(tvid: Int) {
        viewModelScope.launch {
            tvDetails.value = retrofit.getDetailsTV(tvid, api_key)
        }
    }

}