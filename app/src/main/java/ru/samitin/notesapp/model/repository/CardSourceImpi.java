package ru.samitin.notesapp.model.repository;

import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

import ru.samitin.notesapp.R;
import ru.samitin.notesapp.model.domain.CardData;

public class CardSourceImpi implements CardSource {
    private List<CardData>cardDataList;
    private Resources resources;
    public CardSourceImpi(Resources resources){
        this.resources=resources;
        cardDataList=new ArrayList<>(7);
    }
    public CardSourceImpi init(){
        String[]titels=resources.getStringArray(R.array.name);
        String[]descriptions=resources.getStringArray(R.array.description);
        int[]images=getImageArray();
        for (int i=0;i<titels.length;i++)
            cardDataList.add(new CardData(titels[i],images[i],descriptions[i]));
        return this;
    }
    private int[] getImageArray(){
        TypedArray pictures=resources.obtainTypedArray(R.array.picturis);
        int size=pictures.length();
        int[] images=new int[size];
        for (int i=0;i<size;i++)
            images[i]=pictures.getResourceId(i,0);
        return images;
    }

    @Override
    public CardData getCardData(int position) {
        return cardDataList.get(position);
    }
    @Override
    public void deleteCardData(int position) {
        cardDataList.remove(position);
    }

    @Override
    public void updateCardData(int position, CardData cardData) {
        cardDataList.set(position, cardData);
    }

    @Override
    public void addCardData(CardData cardData) {
        cardDataList.add(cardData);
    }

    @Override
    public void clearCardData() {
        cardDataList.clear();
    }


    @Override
    public int size() {
        return cardDataList.size();
    }
}
