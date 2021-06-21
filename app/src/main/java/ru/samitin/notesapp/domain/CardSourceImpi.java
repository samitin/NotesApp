package ru.samitin.notesapp.domain;

import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

import ru.samitin.notesapp.R;

public class CardSourceImpi implements CardSource{
    private List<CardData>cardDataList;
    private Resources resources;
    public CardSourceImpi(Resources resources){
        cardDataList=new ArrayList<>(7);
        this.resources=resources;
        init();
    }
    private void init(){
        String[]names=resources.getStringArray(R.array.name);
        int[]images=getImageArray();
        String[]description=resources.getStringArray(R.array.description);
        for (int i=0;i<names.length;i++)
            cardDataList.add(new CardData(names[i],images[i],description[i]));
    }
    private int[] getImageArray(){
        TypedArray pictures=resources.obtainTypedArray(R.array.picturis);
        int[]images=new int[size()];
        for (int i=0;i<images.length;i++)
            images[i]=pictures.getResourceId(i,0);
        return images;
    }

    @Override
    public List<CardData> getCardDates() {
        return cardDataList;
    }

    @Override
    public int size() {
        return cardDataList.size();
    }
}
