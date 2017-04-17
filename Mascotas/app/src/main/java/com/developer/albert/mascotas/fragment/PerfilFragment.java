package com.developer.albert.mascotas.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.albert.mascotas.R;
import com.developer.albert.mascotas.adapter.MascotaAdaptador;
import com.developer.albert.mascotas.adapter.MascotaAdaptadorPerfil;
import com.developer.albert.mascotas.adapter.PerfilSandBoxInstagramAdaptador;
import com.developer.albert.mascotas.model.Mascota;
import com.developer.albert.mascotas.presentador.IRecylerViewFragmentPresenter;
import com.developer.albert.mascotas.presentador.RecyclerViewFragmentPresenter;
import com.developer.albert.mascotas.restApi.ConstantesRestApi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment implements  IRecyclerViewFragmentView{

    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    private TextView txtNombrePerfil;
    private TextView txtID;
    private ImageView imgFotoPerfil;
    private IRecylerViewFragmentPresenter presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_perfil,container,false);

        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotasPerfil);
        imgFotoPerfil = (ImageView) v.findViewById(R.id.imgFotoPerfil);
        txtNombrePerfil = (TextView) v.findViewById(R.id.txtNombrePerfil);
        txtID = (TextView) v.findViewById(R.id.txtID);


        if (ConstantesRestApi.mascotaSeleccionada != null) {
            Picasso.with(getActivity())
                    .load(ConstantesRestApi.mascotaSeleccionada.getFoto())
                    .placeholder(R.drawable.mascota_1)
                    .into(imgFotoPerfil);

            txtNombrePerfil.setText(ConstantesRestApi.mascotaSeleccionada.getNombreMascota());
            txtID.setText(ConstantesRestApi.mascotaSeleccionada.getId());
        }


        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);

        try {
            if (ConstantesRestApi.ID_PERFIL_SELECCIONADO == "")
            {
                //Snackbar.make(getView(), "Por favor configure una cuenta, ir a menu. ", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Toast.makeText(getContext(),"Por favor configure una cuenta, ir a menu.", Toast.LENGTH_SHORT).show();
            }
            else
            {
                // el primer fragment trajo ya todas las mascotas
                if (ConstantesRestApi.mascotasSeleccionadas != null) {

                    MascotaAdaptadorPerfil adaptador = new MascotaAdaptadorPerfil(ConstantesRestApi.mascotasSeleccionadas, getActivity()  );
                    listaMascotas.setAdapter(adaptador);
                    //inicializarAdaptadorRV(crearAdaptador(ConstantesRestApi.mascotasSeleccionadas));
                }

            }

        }
        catch (Exception e)
        {

        }



        return v;

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
