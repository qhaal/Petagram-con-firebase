package com.developer.albert.mascotas.adapter;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.developer.albert.mascotas.R;
import com.developer.albert.mascotas.model.Mascota;
import com.developer.albert.mascotas.restApi.ConstantesRestApi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Albert on 4/03/2017.
 */

public class PerfilSandBoxInstagramAdaptador extends RecyclerView.Adapter<PerfilSandBoxInstagramAdaptador.MascotaViewHolder> {

    ArrayList<Mascota> mascotas;
    Activity activity;


    public PerfilSandBoxInstagramAdaptador(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    // Inflar el layout  y la pasara el viewHolder para que obtenga los views
    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_perfil_sandbox_instagram,parent,false);
        return new MascotaViewHolder(view);
    }
    // Asocia cada elemento de la lista con cada view
    @Override
    public void onBindViewHolder(final MascotaViewHolder holder, final int position) {


        final Mascota mascota = mascotas.get(position);

        Picasso.with(activity)
                .load(mascota.getFoto())
                .placeholder(R.drawable.mascota_1)
                .into(holder.imgFoto);
        holder.tvNombrePerfil.setText(mascota.getNombreMascota());
        holder.tvID.setText(""+mascota.getId());

        holder.imgFoto.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //notifyItemChanged(holderTemporal.getAdapterPosition());
                //Toast.makeText(activity,position + "", Toast.LENGTH_SHORT).show();
                ConstantesRestApi.ID_PERFIL_SELECCIONADO = "" + mascota.getId();
                ConstantesRestApi.mascotaSeleccionada = mascota;
                Snackbar.make(v, "Perfil Configurado: " + ConstantesRestApi.ID_PERFIL_SELECCIONADO, Snackbar.LENGTH_LONG).show();

            }
        });
    }
    // catidad de elementos que contiene la lista
    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFoto;
        private TextView tvNombrePerfil;
        private TextView tvID;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvNombrePerfil = (TextView) itemView.findViewById(R.id.tvNombrePerfil);
            tvID = (TextView) itemView.findViewById(R.id.tvID);

        }
    }

}
