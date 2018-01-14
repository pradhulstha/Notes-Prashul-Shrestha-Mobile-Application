package com.example.prash.notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView list_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list_view = (ListView) findViewById(R.id.list_view_notes);

    }


    public void addNote(View view) {

        Intent save_note = new Intent(MainActivity.this, NoteActivity.class);
        startActivity(save_note);
    }

    @Override
    protected void onResume() {
        super.onResume();

        list_view.setAdapter(null);

        ArrayList<Note> notes = Save_load_Data.Load_saved_notes(this);


        if(notes == null )
        {
            Toast.makeText(this, "No Notes Saved", Toast.LENGTH_SHORT).show();

        }else {
            ListAdapter na = new ListAdapter(this, R.layout.items_notes, notes);
            list_view.setAdapter(na);

            list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    String filename = ((Note)list_view.getItemAtPosition(i)).getmDatetime() + Save_load_Data.File_Extension;

                    Intent viewNote = new Intent(getApplicationContext(), View_Delete.class);
                    viewNote.putExtra("Note File", filename);

                    startActivity(viewNote);

                }
            });
        }
    }
}
