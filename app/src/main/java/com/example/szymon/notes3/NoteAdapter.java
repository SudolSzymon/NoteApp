package com.example.szymon.notes3;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {
    public List<Note> noteList=new ArrayList<>() ;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView note;
        public TextView text;
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            note=view.findViewById(R.id.noteView);
            text=view.findViewById(R.id.noteText);
            title=view.findViewById(R.id.noteTitle);
        }
    }
    public NoteAdapter(List<Note> noteList) {
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public NoteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.text_note, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.MyViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.text.setText(note.getText());
        holder.title.setText(note.getTitle());

    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView view) {
        super.onAttachedToRecyclerView(view);
    }


}
