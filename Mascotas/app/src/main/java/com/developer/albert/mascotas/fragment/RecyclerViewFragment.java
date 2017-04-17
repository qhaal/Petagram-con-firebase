package com.developer.albert.mascotas.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.developer.albert.mascotas.R;
import com.developer.albert.mascotas.adapter.MascotaAdaptador;
import com.developer.albert.mascotas.adapter.PerfilSandBoxInstagramAdaptador;
import com.developer.albert.mascotas.model.Mascota;
import com.developer.albert.mascotas.presentador.IRecylerViewFragmentPresenter;
import com.developer.albert.mascotas.presentador.RecyclerViewFragmentPresenter;
import com.developer.albert.mascotas.restApi.ConstantesRestApi;

import java.util.ArrayList;

/**
 * Created by Albert on 12/03/2017.
 */

public class RecyclerViewFragment extends Fragment implements IRecyclerViewFragmentView{
    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    private IRecylerViewFragmentPresenter presenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_recyclerview,container,false);

        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);
        if (ConstantesRestApi.ID_PERFIL_SELECCIONADO == "")
        {
            //Snackbar.make(getView(), "Por favor configure una cuenta, ir a menu. ", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            Toast.makeText(getContext(),"Por favor configure una cuenta, ir a menu.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            presenter = new RecyclerViewFragmentPresenter(this, getContext(),1,"");
        }


        return v;
    }

    public void inicializarAdaptador(){
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas,getActivity());
        listaMascotas.setAdapter(adaptador);
    }

    public void inicializarListaContactos(){
        mascotas = new ArrayList<Mascota>();

//        mascotas.add(new Mascota(R.drawable.mascota_1,"Juan",0));
//        mascotas.add(new Mascota(R.drawable.mascota_2,"Perez",0));
//        mascotas.add(new Mascota(R.drawable.mascota_3,"Pawan",0));
//        mascotas.add(new Mascota(R.drawable.mascota_4,"Pedro",0));
//        mascotas.add(new Mascota(R.drawable.mascota_5,"Luis",0));
    }

    @Override
    public void generarLinearLayoutVertical() {
        //GridLayoutManager glm = new GridLayoutManager(this,2);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);
    }

    @Override
    public void generarGridLayout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        listaMascotas.setLayoutManager(gridLayoutManager);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, getActivity()  );
        return adaptador;
    }

    @Override
    public PerfilSandBoxInstagramAdaptador crearAdaptadorPerfil(ArrayList<Mascota> mascotas) {
        return null;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        listaMascotas.setAdapter(adaptador);
    }

    @Override
    public void inicializarAdaptadorPerfil(PerfilSandBoxInstagramAdaptador adaptador) {

    }
}
