package com.developer.albert.mascotas.fragment;


import com.developer.albert.mascotas.adapter.MascotaAdaptador;
import com.developer.albert.mascotas.adapter.PerfilSandBoxInstagramAdaptador;
import com.developer.albert.mascotas.model.Mascota;

import java.util.ArrayList;

/**
 * Created by anahisalgado on 21/04/16.
 */
public interface IRecyclerViewFragmentView {

    public void generarLinearLayoutVertical();

    public void generarGridLayout();

    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);
    public PerfilSandBoxInstagramAdaptador crearAdaptadorPerfil(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);
    public void inicializarAdaptadorPerfil(PerfilSandBoxInstagramAdaptador adaptador);
}
