package com.kyser.daynotes.viewmodel;

import com.kyser.daynotes.services.NotesManager;
import com.kyser.daynotes.services.models.Note;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NoteGridViewModel extends ViewModel {

    private MutableLiveData<List<Note>> noteList;

    public LiveData<List<Note>> getNoteList(){
        if(noteList==null)
            noteList= new MutableLiveData<>();
        NotesManager.getInstance().getAllNotes(noteList);
        return noteList;
    }
}