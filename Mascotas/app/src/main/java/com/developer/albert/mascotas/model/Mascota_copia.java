package com.developer.albert.mascotas.model;

/**
 * Created by Albert on 5/03/2017.
 */

public class Mascota_copia {
    private int id;
    private int foto;
    private String nombreMascota;
    private int puntuacion;

    public Mascota_copia(int id, int foto, String nombreMascota, int puntuacion) {
        this.id = id;
        this.foto = foto;
        this.nombreMascota = nombreMascota;
        this.puntuacion = puntuacion;
    }
    public Mascota_copia() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}
