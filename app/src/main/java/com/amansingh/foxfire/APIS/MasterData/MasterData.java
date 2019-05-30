package com.amansingh.foxfire.APIS.MasterData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MasterData {

    @Expose
    @SerializedName("master_password")
    public String master_password;
    @Expose
    @SerializedName("master_id")
    public String master_id;
    @Expose
    @SerializedName("user_id")
    public String user_id;
    @Expose
    @SerializedName("id")
    public int id;
}
