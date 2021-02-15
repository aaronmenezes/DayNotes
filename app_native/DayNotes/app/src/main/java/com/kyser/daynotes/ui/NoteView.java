package com.kyser.daynotes.ui;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyser.daynotes.viewmodel.NoteViewViewModel;
import com.kyser.daynotes.R;

public class NoteView extends Fragment {

    private NoteViewViewModel mViewModel;

    public static NoteView newInstance() {
        return new NoteView();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.note_view_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NoteViewViewModel.class);
        // TODO: Use the ViewModel
    }

}