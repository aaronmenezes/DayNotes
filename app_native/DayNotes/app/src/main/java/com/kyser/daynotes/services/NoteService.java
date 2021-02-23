package com.kyser.daynotes.services;


import com.kyser.daynotes.services.models.Note;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NoteService {

    @GET("getAllNotes")
    Call<List<Note>> getAllNotes();

    @GET("addNewNote")
    Call<List<Note>> addNewNote(@Query("name") String name , @Query("date") String date , @Query("priority") String priority , @Query("body") String body);

    @GET("updateNote")
    Call<List<Note>> updateNote(@Query("id") int id , @Query("name") String name ,  @Query("body") String body);
}
