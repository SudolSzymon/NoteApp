package com.example.szymon.notes3;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class EditNote extends AppCompatActivity {
    private static final int RESULT_DELETE = 5;
ArrayList<Note> noteList;
int position;
    TextInputEditText editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        editor=findViewById(R.id.editor);
        editor.setText(getIntent().getStringExtra("noteText"));
        findViewById(R.id.deletebutton).setOnClickListener(e->deleteNote());
        findViewById(R.id.saveButton).setOnClickListener(e->save());
        noteList= (ArrayList<Note>) getIntent().getSerializableExtra("noteList");
        position=getIntent().getIntExtra("position",0);
    }

    private void save() {
        noteList.get(position).setText(editor.getText().toString());
        Intent intent = new Intent();
        intent.putExtra("noteList", noteList);
        setResult(RESULT_OK, intent);
        this.finish();
    }

    private void deleteNote() {
        Intent intent = new Intent();
        intent.putExtra("position", position);
        setResult(RESULT_CANCELED,intent);
        this.finish();
    }
}
