package com.kyser.daynotes.ui;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyser.daynotes.databinding.NoteViewFragmentBinding;
import com.kyser.daynotes.services.NotesManager;
import com.kyser.daynotes.viewmodel.NoteViewViewModel;
import com.kyser.daynotes.R;

public class NoteView extends Fragment implements TextWatcher {

    private NoteViewViewModel mViewModel;
    private NoteViewFragmentBinding mFragmentBinding;
    private int mNoteId;
    private boolean mTextUpdate =false;

    public static NoteView newInstance() {
        return new NoteView();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,  @Nullable Bundle savedInstanceState) {
        mFragmentBinding = NoteViewFragmentBinding.inflate(inflater, container, false);
        return mFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mFragmentBinding.back.setOnClickListener(view -> backPressed());
        mNoteId = getArguments().getInt("id");
        mFragmentBinding.noteTitleInp.setText(getArguments().getString("title"));
        mFragmentBinding.noteBodyInp.setText(getArguments().getString("note"));
        mFragmentBinding.noteTitleInp.addTextChangedListener(this);
        mFragmentBinding.noteBodyInp.addTextChangedListener(this);
    }

    private void backPressed() {
        if (mTextUpdate)
        NotesManager.getInstance().updateNote(mNoteId,mFragmentBinding.noteTitleInp.getText().toString(),mFragmentBinding.noteBodyInp.getText().toString());
        getActivity().onBackPressed();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

    @Override
    public void afterTextChanged(Editable editable) {
        mTextUpdate = !mFragmentBinding.noteBodyInp.getText().toString().equals(getArguments().getString("note"));
        mTextUpdate = !mFragmentBinding.noteBodyInp.getText().toString().equals(getArguments().getString("title"));
    }
}