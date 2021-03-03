package com.kyser.daynotes.services;

import com.kyser.daynotes.services.models.Note;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

public class NotesManager {

//    final private String BASE_URL="http://10.0.2.2:8080";
    final private String BASE_URL="http://192.168.1.6:8080";
    private static NotesManager instance;
    private final NoteService noteService;

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
        noteService = retrofit.create(NoteService.class);
    }

    public void getAllNotes(MutableLiveData<List<Note>> notelist){
        noteService.getAllNotes().enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                notelist.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void addNewNote(String name , String date ,String priority , String body){
        noteService.addNewNote(name,date,priority,body).enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {}

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void updateNote(int id, String name , String body){
        noteService.updateNote(id, name,body).enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) { }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void deleteNote(int id){
        noteService.deleteNote(id).enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) { }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

}
