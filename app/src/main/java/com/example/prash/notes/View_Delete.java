package com.example.prash.notes;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class View_Delete extends AppCompatActivity {

    EncyprDecrypt encryptDecrypt;
    private EditText Title;
    private EditText context;
    private Button add_note;
    Note note;

    private String mNote_filename;
    public Note loadedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__delete);

        Title = (EditText) findViewById(R.id.title_view);
        context = (EditText) findViewById(R.id.view_note);
        add_note = (Button)findViewById(R.id.save_btn);



        mNote_filename = getIntent().getStringExtra("Note File");

        if(mNote_filename != null){
          loadedNote = Save_load_Data.getNoteByName(this, mNote_filename);

           if(loadedNote != null){
               Title.setText(loadedNote.getmTitle());
               context.setText(loadedNote.getmNote());
               EncyprDecrypt encd= new EncyprDecrypt();
               String key = "abcdefghinklmnop";
               String codedtext = Title.getText().toString();
               String codeNotes = context.getText().toString();

               String decryptedtmessage = encd.decrypt(codedtext, key);
               String decryptedNote = encd.decrypt(codeNotes, key);

               Title.setText(decryptedtmessage);
               context.setText(decryptedNote);

           }


        }
        add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNote();
            }
        });


    }

    private void addNote() {
        encryptDecrypt = new EncyprDecrypt();


        String key = "abcdefghinklmnop";
        String plaintext = Title.getText().toString();
        String notes = context.getText().toString();
        String encryptedTitle = encryptDecrypt.encrypt(plaintext, key);
        String encryptedNote = encryptDecrypt.encrypt(notes, key);

        if(mNote_filename != null){
                note = new Note(loadedNote.getmDatetime(), encryptedTitle, encryptedNote);

        }



        boolean save_successful;
        Toast.makeText(getBaseContext() , "Saving", Toast.LENGTH_SHORT).show();
        save_successful = Save_load_Data.save_Data(View_Delete.this, note);

        if(save_successful) {
            Toast.makeText(View_Delete.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
            finish();
        }

        else
            Toast.makeText(View_Delete.this, "Please Try Again", Toast.LENGTH_SHORT).show();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_menu_delete_note:
                deleteNote();
            break;
        }
            return true;
    }

    private void deleteNote() {
        if(loadedNote == null)
            finish();
        else{

            AlertDialog.Builder diaglog = new AlertDialog.Builder(this)
                    .setTitle("Are you Sure?")
                    .setMessage("Delete this Note" + Title.getText().toString())
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Save_load_Data.deleteNote(getApplicationContext(),
                                    loadedNote.getmDatetime() + Save_load_Data.File_Extension);
                            Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    })
                    .setNegativeButton("No", null)
                    .setCancelable(false);
            diaglog.show();
        }
    }
}
