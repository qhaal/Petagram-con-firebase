package com.developer.albert.mascotas.restApi.adapter;

import com.developer.albert.mascotas.restApi.ConstantesRestApi;
import com.developer.albert.mascotas.restApi.EndpointsApi;
import com.developer.albert.mascotas.restApi.deserializador.MascotaDeserializador;
import com.developer.albert.mascotas.restApi.deserializador.MascotaDeserializadorBusqueda;
import com.developer.albert.mascotas.restApi.model.MascotaResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anahisalgado on 25/05/16.
 */
public class RestApiAdapter {

    public EndpointsApi establecerConexionRestApiInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndpointsApi.class);
    }

    public EndpointsApi establecerConexionRestAPIServer(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL_SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;

        return retrofit.create(EndpointsApi.class);

    }

    public Gson construyeGsonDeserializadorMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDeserializador());
        return gsonBuilder.create();
    }
    public Gson construyeGsonDeserializadorMediaRecentBusqueda(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDeserializadorBusqueda());
        return gsonBuilder.create();
    }
}
