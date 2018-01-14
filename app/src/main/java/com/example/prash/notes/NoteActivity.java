package com.example.prash.notes;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;


public class NoteActivity extends AppCompatActivity {



    EncyprDecrypt encryptDecrypt;
    public Note loadedNote;

    private EditText title_edit;
    private EditText note_text;
    Note note;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        title_edit = (EditText) findViewById(R.id.title_edit);
        note_text = (EditText)findViewById(R.id.edit_note);


        encryptDecrypt = new EncyprDecrypt();


        // end btnEncrypt click listener

      /*  btnDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = "abcdefghinklmnop";
                String codedtext = etxtEncryptedText.getText().toString();
                String decryptedtmessage = encryptDecrypt.decrypt(codedtext, key);
                etxtDecryptedText.setText(decryptedtmessage);
            }
        }); */// end btnEncrypt click listener



    }


    public void addNote(View view) {

        String mNote_filename = getIntent().getStringExtra("Note File");

        String key = "abcdefghinklmnop";
        String plaintext = title_edit.getText().toString();
        String notes = note_text.getText().toString();

        if(plaintext.isEmpty() && notes.isEmpty()){

            Toast.makeText(getApplicationContext(), "Enter the Note", Toast.LENGTH_SHORT).show();
        }else {
            String encryptedTitle = encryptDecrypt.encrypt(plaintext, key);
            String encryptedNote = encryptDecrypt.encrypt(notes, key);

            if (mNote_filename != null) {
                loadedNote = Save_load_Data.getNoteByName(getApplicationContext(), mNote_filename);

                if (loadedNote == null) {
                    note = new Note(System.currentTimeMillis(), encryptedTitle, encryptedNote);

                } else {
                    note = new Note(loadedNote.getmDatetime(), encryptedTitle, encryptedNote);
                }
            }

        }



        boolean save_successful;
        Toast.makeText(getBaseContext() , "Saving", Toast.LENGTH_SHORT).show();
        save_successful = Save_load_Data.save_Data(NoteActivity.this, note);

        if(save_successful) {
            Toast.makeText(NoteActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
            finish();
        }

        else
            Toast.makeText(NoteActivity.this, "Please Try Again", Toast.LENGTH_SHORT).show();

    }
}



