package com.developer.albert.mascotas.model;

/**
 * Created by Albert on 5/03/2017.
 */

public class Mascota {
    private String id;
    private String nombreMascota;
    private String foto;
    private int likes;


    public Mascota(String id, String nombreMascota, String foto, int likes) {
        this.id = id;
        this.nombreMascota = nombreMascota;
        this.foto = foto;
        this.likes = likes;
    }

    //
//    public Mascota(int id, int foto, String nombreMascota, int puntuacion) {
//        this.id = id;
//        this.foto = foto;
//        this.nombreMascota = nombreMascota;
//        this.puntuacion = puntuacion;
//    }
    public Mascota() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
