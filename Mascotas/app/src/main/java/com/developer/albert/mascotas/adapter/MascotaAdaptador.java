package com.developer.albert.mascotas.adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.developer.albert.mascotas.db.ConstantesBaseDatos;
import com.developer.albert.mascotas.model.ConstructorMascotas;
import com.developer.albert.mascotas.model.Mascota;
import com.developer.albert.mascotas.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Albert on 4/03/2017.
 */

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {

    ArrayList<Mascota> mascotas;
    Activity activity;


    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    // Inflar el layout  y la pasara el viewHolder para que obtenga los views
    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota,parent,false);
        return new MascotaViewHolder(view);
    }
    // Asocia cada elemento de la lista con cada view
    @Override
    public void onBindViewHolder(final MascotaViewHolder holder, final int position) {


        final Mascota mascota = mascotas.get(position);
        //holder.imgFoto.setImageResource(mascota.getFoto());
        Picasso.with(activity)
                .load(mascota.getFoto())
                .placeholder(R.drawable.mascota_1)
                .into(holder.imgFoto);
        //holder.tvNombreMascota.setText(mascota.getNombreMascota());
        holder.tvLikes.setText(""+mascota.getLikes());
//
//        holder.imgHuesoVacio.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//                //notifyItemChanged(holderTemporal.getAdapterPosition());
//                //Toast.makeText(activity,position + "", Toast.LENGTH_SHORT).show();
//                int puntuacionTemporal;
//                puntuacionTemporal = Integer.parseInt(holder.tvPuntuacion.getText().toString());
//                puntuacionTemporal = puntuacionTemporal + 1;
//                holder.tvPuntuacion.setText("" + puntuacionTemporal);
//
//                ContentValues contentValues = new ContentValues();
//                contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_PUNTUACION, puntuacionTemporal);
//
//                ConstructorMascotas constructorContactos = new ConstructorMascotas(activity);
//                constructorContactos.actualizarPuntuacionMascota(mascota,contentValues);
//
//            }
//        });
    }
    // catidad de elementos que contiene la lista
    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFoto;
        //private TextView tvNombreMascota;
        private TextView tvLikes;
        private ImageView imgHuesoVacio;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            //tvNombreMascota = (TextView) itemView.findViewById(R.id.tvNombreMascota);
            tvLikes = (TextView) itemView.findViewById(R.id.tvPuntuacion);
            //imgHuesoVacio = (ImageView) itemView.findViewById(R.id.imgHuesoVacio);

        }
    }

}
