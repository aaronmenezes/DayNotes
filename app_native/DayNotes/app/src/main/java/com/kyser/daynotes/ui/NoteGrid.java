package com.kyser.daynotes.ui;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupMenu;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.kyser.daynotes.databinding.NoteGridFragmentBinding;
import com.kyser.daynotes.services.NotesManager;
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

    private  NoteGridAdaptor.ItemEvent mItemEvent = new NoteGridAdaptor.ItemEvent() {
        @Override
        public void onItemClick(View itemView, Note note) {
            Bundle bundle = new Bundle();
            bundle.putInt("id",note.getId());
            bundle.putString("title",note.getName());
            bundle.putString("note",note.getBody());
            ((MainActivity)getActivity()).getNavController().navigate(R.id.action_noteGrid_to_noteView,bundle);
        }

        @Override
        public void onItemMenuClick(View itemView, Note note) {
            showOptionList(itemView,note);
        }
    };

    private void showOptionList(View itemView, Note note) {
        PopupMenu popupMenu = new PopupMenu(getContext(),itemView);
        popupMenu.getMenuInflater().inflate(R.menu.note_options,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(menuItem -> {
            if(menuItem.getItemId() == R.id.option_2)
                showDeleteDialog(note);
            if(menuItem.getItemId() == R.id.option_1)
                showPriorityDialog(note);
            popupMenu.dismiss();
            return true;
        });
        popupMenu.setOnDismissListener(popupMenu1 -> {});
        popupMenu.show();
    }

    private void showDeleteDialog(Note note) {
        new MaterialAlertDialogBuilder(getContext())
                .setTitle(getResources().getString(R.string.delete_dialog_title))
                .setMessage(getResources().getString(R.string.delete_dialog_desc))
                .setPositiveButton(getResources().getString(R.string.delete_dialog_yes),(dialogInterface, i) -> {deleteNote(note);})
                .setNegativeButton(getResources().getString(R.string.delete_dialog_no),(dialogInterface, i) -> {dialogInterface.dismiss();})
                .setNeutralButton(getResources().getString(R.string.delete_dialog_cancel),(dialogInterface, i) -> {dialogInterface.dismiss();})
                .show();
    }

    private void deleteNote(Note note) {
        NotesManager.getInstance().deleteNote(note.getId());
        updateNoteList();
    }

    private void showPriorityDialog(Note note){
        final EditText input = new EditText(getContext());
        input.setText(note.getPriority()+"");
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_NUMBER);
        new MaterialAlertDialogBuilder(getContext())
                .setTitle(getResources().getString(R.string.priority_dialog_title))
                .setMessage(getResources().getString(R.string.priority_dialog_desc))
                .setView(input)
                .setPositiveButton(getResources().getString(R.string.delete_dialog_yes),(dialogInterface, i) -> {
                    note.setPriority(Integer.parseInt(String.valueOf(input.getText())));
                    updateNotePriority(note);
                })
                .setNegativeButton(getResources().getString(R.string.delete_dialog_no),(dialogInterface, i) -> {dialogInterface.dismiss();})
                .setNeutralButton(getResources().getString(R.string.delete_dialog_cancel),(dialogInterface, i) -> {dialogInterface.dismiss();})
                .show();

    }

    private void updateNotePriority(Note note){
          NotesManager.getInstance().updateNotePriority(note.getId(),note.getPriority());
          updateNoteList();
    }
    private void updateNoteList() {
        mViewModel.getNoteList().observe( (MainActivity)getContext(),notes -> {
            for(Note t : notes)
                System.out.println(t.getName());
            ((NoteGridAdaptor)noteGridBinding.noteGrid.getAdapter()).setNoteList(notes);
            Snackbar.make(getView(), R.string.snack_bar_note_refresh, Snackbar.LENGTH_SHORT)
                    .show();
        });
    }

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
        updateNoteList();
    }
}