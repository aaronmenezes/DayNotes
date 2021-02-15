package com.kyser.daynotes.ui;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyser.daynotes.services.models.Note;
import com.kyser.daynotes.viewmodel.NoteGridViewModel;
import com.kyser.daynotes.R;

public class NoteGrid extends Fragment {

    private NoteGridViewModel mViewModel;

    public static NoteGrid newInstance() {
        return new NoteGrid();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.note_grid_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider.NewInstanceFactory().create( NoteGridViewModel.class);
        mViewModel.getNoteList().observe( (MainActivity)getContext(),notes -> {
            for(Note t : notes)
              System.out.println(t.getName());
        });
    }

}