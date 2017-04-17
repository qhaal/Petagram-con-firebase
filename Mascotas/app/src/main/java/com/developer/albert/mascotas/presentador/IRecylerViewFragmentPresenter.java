package com.developer.albert.mascotas.presentador;

/**
 * Created by anahisalgado on 21/04/16.
 */
public interface IRecylerViewFragmentPresenter {

    public void obtenerMascotas(Integer Opcion);
    void obtenerMediosRecientes();
    void obtenerMediosRecientesPorID(String id);
    void buscarPerfil(String nombrePerfil);
    public void mostrarMascotasRV();
    public void mostrarMascotasPerfil();
}
