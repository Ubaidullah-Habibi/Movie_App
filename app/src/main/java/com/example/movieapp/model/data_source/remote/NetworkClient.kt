package com.example.movieapp.model.data_source.remote

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

object NetworkClient {

    private val client = OkHttpInstance.client
    private val gson = Gson()

    suspend fun <T> makeRequest(url: String, classOfT: Class<T>): T? {
        return withContext(Dispatchers.IO) {
            val request = Request.Builder()
                .url(url)
                .get()
                .build()
            client.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
                    response.body?.string()?.let { json ->
                        gson.fromJson(json, classOfT)
                    }
                } else {
                    null
                }
            }
        }
    }

}

object OkHttpInstance {
    val client: OkHttpClient = OkHttpClient()
}