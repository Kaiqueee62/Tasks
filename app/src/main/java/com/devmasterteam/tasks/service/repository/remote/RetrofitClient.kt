package com.devmasterteam.tasks.service.repository.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitClient private constructor(){
    companion object {

        private lateinit var INSTANCE: Retrofit

        private fun getRetrofitInstance(): Retrofit {
            val httpClient = OkHttpClient.Builder().build()

            //significa se o INSTANCE foi inicializado ou não
            if(!::INSTANCE.isInitialized){
                synchronized(RetrofitClient::class) {
                    INSTANCE = Retrofit.Builder()
                        .baseUrl("http://devmasterteam.com/CursoAndroidAPI/")
                        .client(httpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
            }
            return INSTANCE
        }

        fun <T> getService(serviceClass: Class<T>): T {
            return getRetrofitInstance().create(serviceClass)
        }

    }
    }