package com.example.prash.notes;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by prash on 12/15/2017.
 */

public class Save_load_Data {
    static final int READ_BLOCK_SIZE = 100;

    public static final String File_Extension = ".bin";

    public static boolean save_Data(Context context, Note note) {




        String filename = String.valueOf(note.getmDatetime()) + File_Extension;

        try{
            FileOutputStream fOut = context.openFileOutput(filename, MODE_PRIVATE);

            ObjectOutputStream osw = new ObjectOutputStream(fOut);

            //--Writing to the file---

            osw.writeObject(note);
            osw.flush();
            osw.close();



            fOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static ArrayList<Note> Load_saved_notes(Context context){

        ArrayList<Note> notes = new ArrayList<>();

        File filesDir = context.getFilesDir();

        ArrayList<String> Files = new ArrayList<>();

        for(String file : filesDir.list()){
            if(file.endsWith(File_Extension)){
                Files.add(file);
            }
        }

        FileInputStream fIn;
        FileInputStream fin;
        ObjectInputStream oOs;


        for(int i = 0; i < Files.size(); i++)
        {
            try {

                    fIn = context.openFileInput(Files.get(i));
                    oOs = new ObjectInputStream(fIn);

                    notes.add(((Note)oOs.readObject()));


                fIn.close();

                oOs.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }





     return notes;
    }

    public static Note getNoteByName(Context context, String filename){
        File file = new File(context.getFilesDir(), filename);
        Note note = null;

        if(file.exists()){
            FileInputStream fis;
            ObjectInputStream oos;

            try{

                fis = context.openFileInput(filename);
                oos = new ObjectInputStream(fis);

                 note = (Note) oos.readObject();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

        return note;
    }

    public static void deleteNote(Context applicationContext, String filename) {
        File file= new File(applicationContext.getFilesDir(), filename);

        if(file.exists()) {
            file.delete();
        }


    }
}
