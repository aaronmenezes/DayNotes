package com.kyser.daynotes.services;


import com.kyser.daynotes.services.models.Note;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NoteService {

    @GET("getAllNotes")
    Call<List<Note>> getAllNotes();
}
