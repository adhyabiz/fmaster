package com.amansingh.foxfire.APIS;

import com.amansingh.foxfire.APIS.MasterData.MasterData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {
    //"http://demoadhya.pythonanywhere.com/api/master/{master_id}"
    @GET("master/{mid}")
    Call<MasterData> getMasterData(@Path("mid") int id);
}
