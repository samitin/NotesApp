package ru.samitin.notesapp.model.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class CardData implements Parcelable {
    private String name;
    private int picture;
    private String description;
    public CardData(String name,int picture,String description){
        this.name=name;
        this.description=description;
        this.picture=picture;
    }

    protected CardData(Parcel in) {
        name = in.readString();
        picture = in.readInt();
        description = in.readString();
    }

    public static final Creator<CardData> CREATOR = new Creator<CardData>() {
        @Override
        public CardData createFromParcel(Parcel in) {
            return new CardData(in);
        }

        @Override
        public CardData[] newArray(int size) {
            return new CardData[size];
        }
    };

    public String getName(){return name;}
    public int getPicture(){return picture;}
    public String getDescription(){return description;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(picture);
        parcel.writeString(description);
    }
}
