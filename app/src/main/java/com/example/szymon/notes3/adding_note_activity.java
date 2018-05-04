package com.example.szymon.notes3;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class adding_note_activity extends AppCompatActivity {
    private ArrayList<Note> noteList;
    private TextInputEditText noteInput;
    private TextInputEditText titleInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_note_activity);
        FloatingActionButton abandon = findViewById(R.id.abandon);
        FloatingActionButton create = findViewById(R.id.create);
        noteInput =findViewById(R.id.noteInput);
        titleInput=findViewById(R.id.titleInput);
        create.setOnClickListener(e->create());
        abandon.setOnClickListener(e->abandon());
        noteList= (ArrayList<Note>) getIntent().getSerializableExtra("noteList");

    }
    private void abandon(){
        setResult(2);
        this.finish();
    }
    private void create(){
        noteList.add(new Note(titleInput.getText().toString(),noteInput.getText().toString()));
        Intent intent = new Intent();
        intent.putExtra("noteList", noteList);
        setResult(RESULT_OK, intent);
        this.finish();


    }

    @Override
    public void onBackPressed(){
        abandon();
    }
}
