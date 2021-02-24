package com.kyser.daynotes.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyser.daynotes.R;
import com.kyser.daynotes.databinding.FragmentAddNoteBinding;
import com.kyser.daynotes.databinding.NoteViewFragmentBinding;
import com.kyser.daynotes.services.NotesManager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNote extends Fragment {

    private FragmentAddNoteBinding addNoteBinding;

    public AddNote() {  }

    public static AddNote newInstance() {
        return new AddNote();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        addNoteBinding = FragmentAddNoteBinding.inflate(inflater, container, false);
        addNoteBinding.back.setOnClickListener(view -> (getActivity()).onBackPressed());
        addNoteBinding.btnSave.setOnClickListener(view->addNewNote());
        return addNoteBinding.getRoot();
    }

    private void addNewNote() {
        SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss");
        String format = s.format(new Date());
        NotesManager.getInstance().addNewNote(addNoteBinding.noteTitleInp.getText().toString(),format,"1",addNoteBinding.noteBodyInp.getText().toString());
        getActivity().onBackPressed();
    }
}