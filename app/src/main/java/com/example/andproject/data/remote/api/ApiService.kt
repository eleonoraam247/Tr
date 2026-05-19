package com.example.andproject.data.remote.api

import com.example.andproject.data.remote.dto.QuoteDto
import retrofit2.http.GET

interface ApiService {
    @GET("quotes/random")
    suspend fun getRandomQuote(): QuoteDto

    companion object {
        const val BASE_URL = "https://api.example.com/" // Replace with actual API URL
    }
}
