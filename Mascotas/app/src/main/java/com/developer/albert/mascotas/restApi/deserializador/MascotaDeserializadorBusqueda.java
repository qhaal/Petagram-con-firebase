package com.developer.albert.mascotas.restApi.deserializador;


import com.developer.albert.mascotas.model.Mascota;
import com.developer.albert.mascotas.restApi.JsonKeys;
import com.developer.albert.mascotas.restApi.model.MascotaResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by anahisalgado on 25/05/16.
 */
public class MascotaDeserializadorBusqueda implements JsonDeserializer<MascotaResponse> {

    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MascotaResponse contactoResponse = gson.fromJson(json, MascotaResponse.class);
        JsonArray contactoResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        contactoResponse.setMascotas(deserializarContactoDeJsonBusqueda(contactoResponseData));


        return contactoResponse;
    }

    private ArrayList<Mascota> deserializarContactoDeJsonBusqueda(JsonArray contactoResponseData){
        ArrayList<Mascota> mascotas = new ArrayList<>();
        for (int i = 0; i < contactoResponseData.size() ; i++) {
            JsonObject contactoResponseDataObject = contactoResponseData.get(i).getAsJsonObject();

            String id               = contactoResponseDataObject.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto   = contactoResponseDataObject.get(JsonKeys.USER_FULLNAME).getAsString();
            String urlFoto          = contactoResponseDataObject.get(JsonKeys.USER_PROFILE_PICTURE).getAsString();

            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(id);
            mascotaActual.setNombreMascota(nombreCompleto);
            mascotaActual.setFoto(urlFoto);
            mascotaActual.setLikes(0);

            mascotas.add(mascotaActual);
        }
        return mascotas;
    }
}
