package com.developer.albert.mascotas.restApi.model;


import com.developer.albert.mascotas.model.Contacto;
import com.developer.albert.mascotas.model.Mascota;

import java.util.ArrayList;

/**
 * Created by anahisalgado on 25/05/16.
 */
public class MascotaResponse {

    ArrayList<Mascota> mascotas;

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
}
