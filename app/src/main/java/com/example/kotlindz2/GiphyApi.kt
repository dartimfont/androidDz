package com.example.kotlindz2

import android.content.Context
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {
    @GET("gifs/trending")
    suspend fun getTrendingGifs(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): Response<GiphyResponse>
}

class GiphyRepository(private val api: GiphyApi, private val context: Context) {
    suspend fun getTrendingGifs(apiKey: String, limit: Int, offset: Int): Response<GiphyResponse> {
        return  api.getTrendingGifs(apiKey, limit, offset)
    }
}

data class GiphyResponse(
    val data: List<Gif>,
)

data class Gif(
    val id: String,
    val images: Images,
)

data class Images(
    val original: Image,
)

data class Image(
    val url: String,
    val width: String,
    val height: String,
)