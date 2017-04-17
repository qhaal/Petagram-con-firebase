package com.developer.albert.mascotas.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.developer.albert.mascotas.model.Mascota;

import java.util.ArrayList;

/**
 * Created by albert on 19/03/17.
 */
public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTA + "(" +
                ConstantesBaseDatos.TABLE_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_MASCOTA_FOTO + " INTEGER," +
                ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE + " TEXT, " +
                ConstantesBaseDatos.TABLE_MASCOTA_PUNTUACION + " INTEGER " +
                ")";
        String queryCrearTablaMascotaFavorita = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTA_FAVORITA + "(" +
                ConstantesBaseDatos.TABLE_MASCOTA_FAVORITA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_MASCOTA_FAVORITA_FOTO + " INTEGER," +
                ConstantesBaseDatos.TABLE_MASCOTA_FAVORITA_NOMBRE + " TEXT, " +
                ConstantesBaseDatos.TABLE_MASCOTA_FAVORITA_PUNTUACION + " INTEGER " +
                ")";
//        String queryCrearTablaLikesContacto = "CREATE TABLE " + ConstantesBaseDatos.TABLE_LIKES_CONTACT + "(" +
//                ConstantesBaseDatos.TABLE_LIKES_CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                ConstantesBaseDatos.TABLE_LIKES_CONTACT_ID_CONTACTO + " INTEGER, " +
//                ConstantesBaseDatos.TABLE_LIKES_CONTACT_NUMERO_LIKES + " INTEGER, " +
//                "FOREIGN KEY (" + ConstantesBaseDatos.TABLE_LIKES_CONTACT_ID_CONTACTO + ") " +
//                "REFERENCES " + ConstantesBaseDatos.TABLE_CONTACTS + "(" + ConstantesBaseDatos.TABLE_CONTACTS_ID + ")" +
//                ")";

        db.execSQL(queryCrearTablaMascota);
        //db.execSQL(queryCrearTablaLikesContacto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.TABLE_MASCOTA);
        //db.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.TABLE_LIKES_CONTACT);
        onCreate(db);
    }

    public ArrayList<Mascota> obtenerTodosLasMascotas() {
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            Mascota mascotaActual = new Mascota();
//            mascotaActual.setId(registros.getInt(0));
//            mascotaActual.setFoto(registros.getInt(1));
//            mascotaActual.setNombreMascota(registros.getString(2));
//            mascotaActual.setPuntuacion(registros.getInt(3));

            mascotaActual.setId(registros.getString(0));
            mascotaActual.setFoto(registros.getString(1));
            mascotaActual.setNombreMascota(registros.getString(2));
            mascotaActual.setLikes(registros.getInt(3));

            mascotas.add(mascotaActual);

        }

        db.close();

        return mascotas;
    }
    public ArrayList<Mascota> obtenerTopMascotasFavoritas() {
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTA + " WHERE " + ConstantesBaseDatos.TABLE_MASCOTA_PUNTUACION + " > 0 "
                + " ORDER BY " +
                ConstantesBaseDatos.TABLE_MASCOTA_PUNTUACION + " DESC LIMIT 5";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            Mascota mascotaActual = new Mascota();
//            mascotaActual.setId(registros.getInt(0));
//            mascotaActual.setFoto(registros.getInt(1));
//            mascotaActual.setNombreMascota(registros.getString(2));
//            mascotaActual.setPuntuacion(registros.getInt(3));

            mascotaActual.setId(registros.getString(0));
            mascotaActual.setFoto(registros.getString(1));
            mascotaActual.setNombreMascota(registros.getString(2));
            mascotaActual.setLikes(registros.getInt(3));

            mascotas.add(mascotaActual);        }

        db.close();

        return mascotas;
    }

    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTA,null, contentValues);
        db.close();
    }
    public void actualizarPuntuacion(Mascota mascota,ContentValues contentValues){
        Log.e(">>>>PUNTU"  + ">>>>ID " + mascota.getId(), "onCreate");

        SQLiteDatabase db = this.getWritableDatabase();

        int rowsUpdated = db.update(ConstantesBaseDatos.TABLE_MASCOTA, contentValues,
                                ConstantesBaseDatos.TABLE_MASCOTA_ID + " = " + mascota.getId(), null);

        Log.e("Rows: "  + rowsUpdated, "   -- > actualizarPuntuacion");
        db.close();
    }


//    public int obtenerLikesContacto(Contacto contacto){
//        int likes = 0;
//
//        String query = "SELECT COUNT("+ConstantesBaseDatos.TABLE_LIKES_CONTACT_NUMERO_LIKES+")" +
//                        " FROM " + ConstantesBaseDatos.TABLE_LIKES_CONTACT +
//                        " WHERE " + ConstantesBaseDatos.TABLE_LIKES_CONTACT_ID_CONTACTO + "="+contacto.getId();
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor registros = db.rawQuery(query, null);
//
//        if (registros.moveToNext()){
//            likes = registros.getInt(0);
//        }
//
//        db.close();
//
//        return likes;
//    }
}