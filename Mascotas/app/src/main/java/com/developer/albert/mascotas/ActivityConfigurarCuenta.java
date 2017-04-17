package com.developer.albert.mascotas;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.developer.albert.mascotas.adapter.MascotaAdaptador;
import com.developer.albert.mascotas.adapter.PerfilSandBoxInstagramAdaptador;
import com.developer.albert.mascotas.fragment.IRecyclerViewFragmentView;
import com.developer.albert.mascotas.model.Mascota;
import com.developer.albert.mascotas.presentador.IRecylerViewFragmentPresenter;
import com.developer.albert.mascotas.presentador.RecyclerViewFragmentPresenter;
import com.developer.albert.mascotas.restApi.ConstantesRestApi;

import java.util.ArrayList;


public class ActivityConfigurarCuenta extends AppCompatActivity implements IRecyclerViewFragmentView {

    TextInputEditText edtNombreCuenta;
    Button btnProcesarCuenta;

    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    private IRecylerViewFragmentPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cuenta);

        edtNombreCuenta = (TextInputEditText) findViewById(R.id.edtNombreCuenta);
        btnProcesarCuenta = (Button) findViewById(R.id.btnProcesarCuenta);
    }

    public void ProcesarCuenta(View view) {

        if (edtNombreCuenta.length() > 0)
        {
            //obtener datos
            listaMascotas = (RecyclerView) findViewById(R.id.rvMascotas);
            presenter = new RecyclerViewFragmentPresenter(this,this,2,edtNombreCuenta.getText().toString());
        }
        else
        {
            Snackbar.make(view, "Por favor ingrese un Nombre ", Snackbar.LENGTH_LONG).show();
        }

    }

    @Override
    public void generarLinearLayoutVertical() {
        //GridLayoutManager glm = new GridLayoutManager(this,2);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);
    }

    @Override
    public void generarGridLayout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        listaMascotas.setLayoutManager(gridLayoutManager);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, this );
        return adaptador;
    }

    @Override
    public PerfilSandBoxInstagramAdaptador crearAdaptadorPerfil(ArrayList<Mascota> mascotas) {
        PerfilSandBoxInstagramAdaptador adaptador = new PerfilSandBoxInstagramAdaptador(mascotas, this );
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        listaMascotas.setAdapter(adaptador);
    }

    @Override
    public void inicializarAdaptadorPerfil(PerfilSandBoxInstagramAdaptador adaptador) {
        listaMascotas.setAdapter(adaptador);
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
