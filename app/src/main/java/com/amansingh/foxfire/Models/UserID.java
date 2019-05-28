package com.amansingh.foxfire.Models;

import androidx.annotation.NonNull;

import com.google.firebase.database.Exclude;

public class UserID {

    @Exclude
    public String UserID;

    @NonNull
    public <T extends UserID> T withID(@NonNull final String id) {
        this.UserID = id;
        return (T) this;
    }
}
