package com.kyser.daynotes.ui;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyser.daynotes.databinding.NoteGridFragmentBinding;
import com.kyser.daynotes.services.models.Note;
import com.kyser.daynotes.ui.adaptor.NoteGridAdaptor;
import com.kyser.daynotes.ui.components.SpaceItemDecoration;
import com.kyser.daynotes.viewmodel.NoteGridViewModel;
import com.kyser.daynotes.R;

public class NoteGrid extends Fragment {

    private NoteGridViewModel mViewModel;
    private NoteGridFragmentBinding noteGridBinding;

    public static NoteGrid newInstance() {
        return new NoteGrid();
    }

    private  NoteGridAdaptor.ItemEvent mItemEvent = note -> {
        Bundle bundle = new Bundle();
        bundle.putInt("id",note.getId());
        bundle.putString("title",note.getName());
        bundle.putString("note",note.getBody());
        ((MainActivity)getActivity()).getNavController().navigate(R.id.action_noteGrid_to_noteView,bundle);
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        noteGridBinding = NoteGridFragmentBinding.inflate(inflater, container, false);
        return noteGridBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        noteGridBinding.noteGrid.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        noteGridBinding.noteGrid.setAdapter(new NoteGridAdaptor(getContext(),mItemEvent));
        noteGridBinding.noteGrid.addItemDecoration(new SpaceItemDecoration(20,20,true));
        mViewModel = new ViewModelProvider.NewInstanceFactory().create( NoteGridViewModel.class);
        mViewModel.getNoteList().observe( (MainActivity)getContext(),notes -> {
            for(Note t : notes)
                System.out.println(t.getName());
            ((NoteGridAdaptor)noteGridBinding.noteGrid.getAdapter()).setNoteList(notes);
        });
    }
}