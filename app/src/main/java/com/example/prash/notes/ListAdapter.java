package com.example.prash.notes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by prash on 12/15/2017.
 */

public class ListAdapter extends ArrayAdapter<Note> {


    private TextView context;
    private TextView date;
    private TextView title;

    public ListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Note> notes) {
        super(context, resource, notes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        if(convertView == null) {
           convertView = LayoutInflater.from(getContext()).inflate(R.layout.items_notes, null);
        }

        Note note = getItem(position);

        if(note != null)
        {

            date = (TextView) convertView.findViewById(R.id.list_date);
            context = (TextView) convertView.findViewById(R.id.list_notes);
            title = (TextView)  convertView.findViewById(R.id.list_title);


            date.setText(note.getDatetimeFormat(getContext()));
            title.setText(note.getmTitle());
            context.setText(note.getmNote());

            EncyprDecrypt encd= new EncyprDecrypt();
            String key = "abcdefghinklmnop";
            String codedtext = title.getText().toString();
            String codeNotes = context.getText().toString();

            String decryptedtmessage = encd.decrypt(codedtext, key);
            String decryptedNote = encd.decrypt(codeNotes, key);

            title.setText(decryptedtmessage);
            context.setText(decryptedNote);

        }

        return convertView;


    }
}
