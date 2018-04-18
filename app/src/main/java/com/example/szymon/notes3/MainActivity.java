package com.example.szymon.notes3;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<Note> noteList=new ArrayList<>();
    private RecyclerView recyclerView;
    private NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.RV);
        Button addNotebutton=findViewById(R.id.addNote);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        adapter = new NoteAdapter(noteList);
        recyclerView.setAdapter(adapter);
        addNotebutton.setOnClickListener(e->addNote());
    }
    private void addNote(){
        Intent intent = new Intent(this, adding_note_activity.class);
        intent.putExtra("noteList", noteList);
        startActivityForResult(intent,0);


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if(resultCode == RESULT_OK) {
                noteList= (ArrayList<Note>) data.getSerializableExtra("noteList");

            }
        }
        recyclerView.swapAdapter(new NoteAdapter(noteList),true);
    }
}
