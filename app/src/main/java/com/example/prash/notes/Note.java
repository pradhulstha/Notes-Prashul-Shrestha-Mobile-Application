package com.example.prash.notes;

import android.content.Context;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by prash on 12/15/2017.
 */

public class Note implements Serializable {

    private long mDatetime;
    private String mTitle;
    private String mNote;


    public Note(long datetime, String title,String contents) {
           mNote = contents;
         mTitle = title;
         mDatetime = datetime;

    }

    public String getmNote() {
        return mNote;
    }

    public void setmNote(String mNote) {
        this.mNote = mNote;
    }

    public void setmDatetime(long mDatetime) {
        this.mDatetime = mDatetime;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }



    public long getmDatetime() {
        return mDatetime;
    }

    public String getmTitle() {
        return mTitle;
    }




    public String getDatetimeFormat(Context context){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss",
                context.getResources().getConfiguration().locale);
         sdf.setTimeZone(TimeZone.getDefault());
         return sdf.format(new Date(mDatetime));
    }
}
