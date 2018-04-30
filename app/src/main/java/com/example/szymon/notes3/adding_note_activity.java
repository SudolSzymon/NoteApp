package com.example.szymon.notes3;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class adding_note_activity extends AppCompatActivity {
    private ArrayList<Note> noteList;
    private TextInputEditText input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_note_activity);
        Button abandon = findViewById(R.id.abandon);
        Button create = findViewById(R.id.create);
        input=findViewById(R.id.noteInput);
        create.setOnClickListener(e->create());
        abandon.setOnClickListener(e->abandon());
        noteList= (ArrayList<Note>) getIntent().getSerializableExtra("noteList");

    }
    private void abandon(){
        setResult(2);
        this.finish();
    }
    private void create(){
        noteList.add(new Note(input.getText().toString()));
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
