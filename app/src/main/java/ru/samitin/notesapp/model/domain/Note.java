package ru.samitin.notesapp.model.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {
    private String noteName;
    private String noteDescription;
    private String noteDate;
    private String note;
    public Note(String noteName,String noteDescription,String noteDate,String note){
       this.noteName=noteName;
       this.noteDescription=noteDescription;
       this.noteDate=noteDate;
       this.note=note;
    }

    protected Note(Parcel in) {
       noteName=in.readString();
       noteDescription=in.readString();
       noteDate=in.readString();
       note=in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getNoteName());
        parcel.writeString(getNoteDescription());
        parcel.writeString(getNoteDate());
        parcel.writeString(getNote());
    }

    public String getNoteName() { return noteName; }
    public String getNoteDescription() { return noteDescription; }
    public String getNoteDate() { return noteDate; }
    public String getNote() { return note; }

}
