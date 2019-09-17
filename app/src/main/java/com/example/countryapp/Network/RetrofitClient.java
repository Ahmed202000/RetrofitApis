package com.example.countryapp.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static Retrofit retrofit=null;

    public static Retrofit getClint()
    {
        if (retrofit==null)
        {
            retrofit=new Retrofit.Builder()
                    .baseUrl("http://countryapi.gear.host/v1/country/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
               return retrofit;
    }
}
