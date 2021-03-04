package com.kyser.daynotes.ui.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyser.daynotes.databinding.NoteGridItemBinding;
import com.kyser.daynotes.services.models.Note;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteGridAdaptor extends RecyclerView.Adapter<NoteGridAdaptor.Holder> {

    private List<Note> noteList;
    private Context mContext;
    private ItemEvent itemEvent;

    public interface ItemEvent{
        public void onItemClick(View itemView, Note note);
        public void onItemMenuClick(View itemView, Note note);
    }

    public NoteGridAdaptor(Context mContext) {
        this.mContext = mContext;
    }
    public NoteGridAdaptor(Context mContext,ItemEvent itemEvent) {
        this.mContext = mContext;
        this.itemEvent = itemEvent;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(NoteGridItemBinding.inflate(LayoutInflater.from(parent.getContext()),  parent, false))  ;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.itemBinding.itemTitle.setText(noteList.get(position).getName());
        holder.itemBinding.itemBody.setText(noteList.get(position).getBody());
        holder.itemBinding.getRoot().setOnClickListener(view ->  itemEvent.onItemClick(view,noteList.get(position)));
        holder.itemBinding.itemMenu.setOnClickListener(view -> itemEvent.onItemMenuClick(view,noteList.get(position)));
    }

    @Override
    public int getItemCount() {
        return noteList==null?0:noteList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private NoteGridItemBinding itemBinding;

        public Holder(@NonNull NoteGridItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding=itemBinding;
        }
    }
}
