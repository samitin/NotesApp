package ru.samitin.notesapp.model.repository;

import ru.samitin.notesapp.model.domain.CardData;

public interface CardSource {
    CardData getCardData(int position);
    int size();
}
