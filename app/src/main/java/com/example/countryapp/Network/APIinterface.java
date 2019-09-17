package com.example.countryapp.Network;

import com.example.countryapp.Models.Content;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIinterface
{
    @GET("getCountries?pNativelange=eng")
    Call<Content> getCountry();
}
