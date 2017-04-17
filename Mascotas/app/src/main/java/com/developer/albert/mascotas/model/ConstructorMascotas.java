package com.developer.albert.mascotas.model;

import android.content.ContentValues;
import android.content.Context;


import com.developer.albert.mascotas.R;
import com.developer.albert.mascotas.db.BaseDatos;
import com.developer.albert.mascotas.db.ConstantesBaseDatos;

import java.util.ArrayList;


/**
 * Created by anahisalgado on 21/04/16.
 */
public  class ConstructorMascotas {

    private static final int LIKE = 1;
    private Context context;

    public ConstructorMascotas(Context context) {
        this.context = context;
    }

    public final ArrayList<Mascota> obtenerDatos() {
        BaseDatos db = new BaseDatos(context);
        insertarTresMascotas(db);
        return  db.obtenerTodosLasMascotas();
    }
    public final ArrayList<Mascota> obtenerDatosFavoritos() {
        BaseDatos db = new BaseDatos(context);
        insertarTresMascotas(db);
        return  db.obtenerTopMascotasFavoritas();
    }



    public void insertarTresMascotas(BaseDatos db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.mascota_1);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Pedro");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_PUNTUACION, "0");



        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.mascota_2);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Juan");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_PUNTUACION, "0");


        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.mascota_3);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Andres");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_PUNTUACION, "0");

        db.insertarMascota(contentValues);
    }

    public void actualizarPuntuacionMascota(Mascota mascota, ContentValues contentValues){
        BaseDatos db = new BaseDatos(context);
        db.actualizarPuntuacion(mascota,contentValues);
    }
//
//    public int obtenerLikesContacto(Contacto contacto){
//        BaseDatos db = new BaseDatos(context);
//        return db.obtenerLikesContacto(contacto);
//    }


}
