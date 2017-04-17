package com.developer.albert.mascotas.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import com.developer.albert.mascotas.fragment.IRecyclerViewFragmentView;
import com.developer.albert.mascotas.model.ConstructorMascotas;
import com.developer.albert.mascotas.model.Contacto;
import com.developer.albert.mascotas.model.Mascota;
import com.developer.albert.mascotas.restApi.ConstantesRestApi;
import com.developer.albert.mascotas.restApi.EndpointsApi;
import com.developer.albert.mascotas.restApi.adapter.RestApiAdapter;
import com.developer.albert.mascotas.restApi.model.MascotaResponse;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by anahisalgado on 21/04/16.
 */
public class RecyclerViewFragmentPresenter implements IRecylerViewFragmentPresenter {

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;
    private ArrayList<Contacto> contactos;
    private String perfil;

    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context,Integer tipo, String perfil) {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        this.perfil = perfil;
        //obtenerMascotas(Opcion);
        //tipo=0 ---> Carga Fotografias la cuenta configurada por defecto (Mi Cuenta)
        //tipo=1 ---> Carga las fotografias para un ID especificado
        //tipo=2 ---> Realiza la Busqueda
        if(tipo==0)
        {
            obtenerMediosRecientes();
        }
        if(tipo==1)
        {
            obtenerMediosRecientesPorID(ConstantesRestApi.ID_PERFIL_SELECCIONADO);
        }
        if(tipo==2)
        {
            buscarPerfil(perfil);
        }


    }

    @Override
    public void obtenerMascotas(Integer opcion) {
        if (opcion == 1)
        {
            constructorMascotas = new ConstructorMascotas(context);
            mascotas = constructorMascotas.obtenerDatos();
            mostrarMascotasRV();
        }
        else
        {
            constructorMascotas = new ConstructorMascotas(context);
            mascotas = constructorMascotas.obtenerDatosFavoritos();
            mostrarMascotasRV();
        }

    }

    @Override
    public void obtenerMediosRecientes() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<MascotaResponse> contactoResponseCall = endpointsApi.getRecentMedia();

        contactoResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotas = mascotaResponse.getMascotas();
                mostrarMascotasRV();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "¡Al pasó en la conexión! Intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION", t.toString());
            }
        });

    }

    @Override
    public void obtenerMediosRecientesPorID(String id) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<MascotaResponse> contactoResponseCall = endpointsApi.getRecentMediaByID(id);

        contactoResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotas = mascotaResponse.getMascotas();
                ConstantesRestApi.mascotasSeleccionadas = mascotas;
                mostrarMascotasRV();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "¡Al pasó en la conexión! Intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION", t.toString());
            }
        });

    }



    @Override
    public void buscarPerfil(String nombrePerfil) {

        RestApiAdapter restApiAdapter1 = new RestApiAdapter();
        Gson gsonMediaRecent1 = restApiAdapter1.construyeGsonDeserializadorMediaRecentBusqueda();
        EndpointsApi endpointsApi1 = restApiAdapter1.establecerConexionRestApiInstagram(gsonMediaRecent1);
        Call<MascotaResponse> contactoResponseCall1 = endpointsApi1.getRecentMediaBusqueda(nombrePerfil);

        contactoResponseCall1.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotas = mascotaResponse.getMascotas();
                mostrarMascotasPerfil();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "¡Al pasó en la conexión! Intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION", t.toString());
            }
        });
    }

    @Override
    public void mostrarMascotasRV() {
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(mascotas));
        if(this.perfil=="")
        {
            iRecyclerViewFragmentView.generarGridLayout();
        }
        else // 2 --> busqueda
        {
            iRecyclerViewFragmentView.generarLinearLayoutVertical();
        }

    }

    @Override
    public void mostrarMascotasPerfil() {
        iRecyclerViewFragmentView.inicializarAdaptadorPerfil(iRecyclerViewFragmentView.crearAdaptadorPerfil(mascotas));
        if(this.perfil=="")
        {
            iRecyclerViewFragmentView.generarGridLayout();
        }
        else // 2 --> busqueda
        {
            iRecyclerViewFragmentView.generarLinearLayoutVertical();
        }
    }

}