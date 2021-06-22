package ru.samitin.notesapp.domain;

import java.util.List;

public interface CardSource {
    CardData getCardData(int position);
    int size();
}
