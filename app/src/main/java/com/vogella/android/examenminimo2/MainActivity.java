package com.vogella.android.examenminimo2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.vogella.android.examenminimo2.adapter.ElementAdapter;
import com.vogella.android.examenminimo2.models.Element;
import com.vogella.android.examenminimo2.models.Museums;
import com.vogella.android.examenminimo2.webservice.WebServiceClient;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ElementAdapter adapter;
    private List<Element> elementos;

    private ProgressBar progressBar;

    private Retrofit retrofit;
    private HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder httpClientBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.progressBar = findViewById(R.id.progressBar);
        setUpElementAdapter();
        lanzarPeticion(); //setUpRetrofit
    }

    private void lanzarPeticion(){
        //para ver que datos se estan recibiendo en pantalla
        loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://do.diba.cat/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();

        WebServiceClient client = retrofit.create(WebServiceClient.class);
        Call<Museums> call = client.getElements("1", "250");//getElements de la interfaz
        this.progressBar.setVisibility(View.VISIBLE);
        Log.d("PROG", "PONEMOS LOADER");
        call.enqueue(new Callback<Museums>() {
            @Override
            public void onResponse(Call<Museums> call, Response<Museums> response) {

                if (response.isSuccessful()) {
                    //getElements nos devuelve la lista de elementos museums en model
                    Log.d("PROG", "QUITAMOS LOADER");
                    MainActivity.this.progressBar.setVisibility(View.GONE);
                    adapter.setData(response.body().getElements()); //getElements de museo
                }
            }
            @Override
            public void onFailure(Call<Museums> call, Throwable t) {
                Log.d("TAG1", "Error: " + t.getMessage());
            }
        });
    }

    private void setUpElementAdapter(){
        elementos = new ArrayList<>();
        adapter = new ElementAdapter(this, elementos);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager lim = new LinearLayoutManager(this);
        lim.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(lim);
        recyclerView.setAdapter(adapter);
    }
}
