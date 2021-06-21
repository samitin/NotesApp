package ru.samitin.notesapp.domain;

import java.util.List;

public interface CardSource {
    List<CardData>getCardDates();
    int size();
}
