package com.developer.albert.mascotas.restApi;


import com.developer.albert.mascotas.restApi.model.MascotaResponse;
import com.developer.albert.mascotas.restApi.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by anahisalgado on 25/05/16.
 */
public interface EndpointsApi {

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<MascotaResponse> getRecentMedia();


    @GET(ConstantesRestApi.URL_FINAL_SEARCH)
    Call<MascotaResponse> getRecentMediaBusqueda(@Query("q") String perfil);


    @GET(ConstantesRestApi.URL_FINAL_MEDIA_BY_ID)
    Call<MascotaResponse> getRecentMediaByID(@Path("user-id") String id);

    // ==============================================================================
    // SERVIDOR HEROKU Y NOTIFICACIONES
    // ==============================================================================
    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_REGISTRAR_USUARIO)
    Call<UsuarioResponse> registrarTokenIDServer(@Field("id_dispositivo") String token, @Field("id_usuario_instagram") String animal);

//    Call<UsuarioResponse> registrarTokenID(@Field("token") String token, @Field("animal") String animal);

//    @GET(ConsantesRestAPI.KEY_TOQUE_ANIMAL)
//    Call<UsuarioResponse> toqueAnimal(@Path("id") String id, @Path("animal") String animal);

}
