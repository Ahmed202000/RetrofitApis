package com.example.countryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.countryapp.Models.Content;
import com.example.countryapp.Models.Country;
import com.example.countryapp.Network.APIinterface;
import com.example.countryapp.Network.RetrofitClient;

import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView txtCountryName;
    private ImageView imgCountry;
    private Button btnCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCountryName=findViewById(R.id.CountryName);
        imgCountry=findViewById(R.id.imgCountry);
        btnCheck=findViewById(R.id.Check);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lodeCountry();
            }
        });
    }
    private void lodeCountry()
    {
        APIinterface apIinterface= RetrofitClient.getClint().create(APIinterface.class);
        retrofit2. Call<Content> call= apIinterface.getCountry();
        call.enqueue(new Callback<Content>(){

            public void onResponse (retrofit2.Call<Content>call, Response<Content>response)
            {
                if (response.isSuccessful())
                {
                    Content content=response.body();
                    Country country=content.getResponse().get(10);
                    txtCountryName.setText(country.getName());
                    Glide.with(getApplicationContext())
                            .load(country.getFlagPng())
                            .into(imgCountry);
                }
                else {
                    int statusCode=response.code();
                    String massgeCode=response.message();
                    Toast.makeText(MainActivity.this, "Code :"+String.valueOf(statusCode), Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "Message :"+massgeCode, Toast.LENGTH_SHORT).show();
                }

            }
            public void onFailure(retrofit2.Call<Content>call, Throwable t)
            {
                Log.i("Error Message is :",t.getMessage());
            }

    });
    }
}
