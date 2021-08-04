package ru.samitin.notesapp.model.domain;

public class CardData {
    private String name;
    private int picture;
    private String description;
    public CardData(String name,int picture,String description){
        this.name=name;
        this.description=description;
        this.picture=picture;
    }
    public String getName(){return name;}
    public int getPicture(){return picture;}
    public String getDescription(){return description;}
}
