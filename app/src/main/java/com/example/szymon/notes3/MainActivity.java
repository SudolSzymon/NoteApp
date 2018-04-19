package com.example.szymon.notes3;

import android.content.Context;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ArrayList<Note> noteList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context=this;
        String data=Open("data.txt");
        noteList=parseList(data);
        if(noteList==null){
            noteList=new ArrayList<>();
        }
        recyclerView = findViewById(R.id.RV);
        Button addNotebutton=findViewById(R.id.addNote);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        NoteAdapter adapter = new NoteAdapter(noteList);
        recyclerView.setAdapter(adapter);
        addNotebutton.setOnClickListener(e->addNote());
        recyclerView.addOnItemTouchListener(
                new RecyclerViewItemClickHandler(this, (view, position) -> {
                    Intent intent = new Intent(context, EditNote.class);
                    intent.putExtra("noteList", noteList);
                    intent.putExtra("noteText",noteList.get(position).getText());
                    intent.putExtra("position", position);
                    startActivityForResult(intent,0);
                }));


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
                Save("data.txt");
            }
            if (resultCode==RESULT_CANCELED){
                int position=data.getIntExtra("position",0);
                noteList.remove(position);
                Save("data.txt");
            }
        }
        recyclerView.swapAdapter(new NoteAdapter(noteList),true);
    }

    public void Save(String fileName) {
        try {
            OutputStreamWriter out =
                    new OutputStreamWriter(openFileOutput(fileName, 0));
            for(Note note:noteList){
                out.write(note.getText());
                out.write("#");
            }
            out.close();
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        } catch (Throwable t) {
            Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public String Open(String fileName) {
        String content = "";
        if (FileExists(fileName)) {
            try {
                InputStream in = openFileInput(fileName);
                if ( in != null) {
                    InputStreamReader tmp = new InputStreamReader( in );
                    BufferedReader reader = new BufferedReader(tmp);
                    String str;
                    StringBuilder buf = new StringBuilder();
                    while ((str = reader.readLine()) != null) {
                        buf.append(str).append("\n");
                    } in .close();
                    content = buf.toString();
                }
            } catch (FileNotFoundException ignored) {} catch (Throwable t) {
                Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
            }
        }
        return content;
    }
    private ArrayList<Note> parseList(String data){
        ArrayList<Note> list=new ArrayList<>();
        StringBuilder buf = new StringBuilder();
        for(int i=0;i<data.length();i++){
            if(data.charAt(i)=="#".charAt(0)){
                list.add(new Note(buf.toString()));
                buf=new StringBuilder();
            }
            else {
                buf.append(data.charAt(i));
            }

        }
        return list;

    }

    public boolean FileExists(String fname) {
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }
}
