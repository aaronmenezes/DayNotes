package com.kyser.daynotes.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NotesManager {

    final private String BASE_URL="https://localhost:8080";
    private static NotesManager instance;

    public static synchronized NotesManager getInstance() {
        if (instance == null) {
            instance = new NotesManager();
        }
        return instance;
    }

    private NotesManager() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
       // myApi = retrofit.create(Api.class);
    }

}
