package com.developer.albert.mascotas;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.developer.albert.mascotas.restApi.ConstantesRestApi;
import com.developer.albert.mascotas.restApi.EndpointsApi;
import com.developer.albert.mascotas.restApi.adapter.RestApiAdapter;
import com.developer.albert.mascotas.restApi.model.UsuarioResponse;
import com.google.firebase.iid.FirebaseInstanceId;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecibirNotificaciones extends AppCompatActivity {
    private Button btnRegistrarServer;
    private static final String TAG = "FIREBASE_TOKEN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibir_notificaciones);

        btnRegistrarServer = (Button) findViewById(R.id.btnRegistrarServer);

    }


    public void lanzarNotificacion(View view)
    {
        Log.i("--->", "Solicitando Token");
        if (ConstantesRestApi.mascotaSeleccionada != null)
        {
            String token = FirebaseInstanceId.getInstance().getToken();
            enviarTokenRegistro(token);
            Snackbar.make(view, "Dispositivo Registrado correctamente", Snackbar.LENGTH_LONG).show();
        }
        else
        {
            Snackbar.make(view, "Por favor Configure una cuenta de Instagram", Snackbar.LENGTH_LONG).show();
        }

    }

    private void enviarTokenRegistro(String token){
        Log.d("TOKEN", token);
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endponits = restApiAdapter.establecerConexionRestAPIServer();
        Call<UsuarioResponse> usuarioResponseCall = endponits.registrarTokenIDServer(token,ConstantesRestApi.mascotaSeleccionada.getId());

        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                Log.d("ID_FIREBASE", "" +usuarioResponse.getId());
                Log.d("ID DISPOSITIVO","" + usuarioResponse.getId_dispositivo());
                Log.d("ID INSTAGRAM","" + usuarioResponse.getId_usuario_instagram());

            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Log.d(this.getClass().getName(), "back button pressed");
            Intent intent  = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}
