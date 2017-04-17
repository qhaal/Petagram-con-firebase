package com.developer.albert.mascotas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.developer.albert.mascotas.adapter.MascotaAdaptador;
import com.developer.albert.mascotas.adapter.PerfilSandBoxInstagramAdaptador;
import com.developer.albert.mascotas.fragment.IRecyclerViewFragmentView;
import com.developer.albert.mascotas.model.Mascota;
import com.developer.albert.mascotas.presentador.IRecylerViewFragmentPresenter;
import com.developer.albert.mascotas.presentador.RecyclerViewFragmentPresenter;

import java.util.ArrayList;

public class MascotasFavoritas extends AppCompatActivity  implements IRecyclerViewFragmentView{
    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotasFavoritas;
    private IRecylerViewFragmentPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas_favoritas);

       // getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        listaMascotasFavoritas = (RecyclerView) findViewById(R.id.rvMascotasFavoritas);
        presenter = new RecyclerViewFragmentPresenter(this, getApplicationContext(),1,"");

    }


    @Override
    public void generarLinearLayoutVertical() {
        //GridLayoutManager glm = new GridLayoutManager(this,2);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotasFavoritas.setLayoutManager(llm);
    }

    @Override
    public void generarGridLayout() {

    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, this);
        return adaptador;
    }

    @Override
    public PerfilSandBoxInstagramAdaptador crearAdaptadorPerfil(ArrayList<Mascota> mascotas) {
        return null;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        listaMascotasFavoritas.setAdapter(adaptador);
    }

    @Override
    public void inicializarAdaptadorPerfil(PerfilSandBoxInstagramAdaptador adaptador) {

    }
}
