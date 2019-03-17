package com.goodbook.book.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CardDto {

    private Map<Integer, Integer> card;

    public CardDto() {
        card = new HashMap<>();
    }

    public void addBook(int id) {
        int count = 1;
        if (card.containsKey(id)) {
            count = card.get(id) + 1;
        }
        card.put(id, count);
    }

    public int getCountOneBookInCard(int id){
        if (card.containsKey(id)) {
            return card.get(id);
        }
        return 0;
    }

    public boolean removeBook(int id) {
        if (card.containsKey(id)) {
            card.remove(id);
            return true;
        }
        return false;
    }

    public boolean decrementCount(int id) {
        if (card.containsKey(id)) {
            int count = card.get(id) - 1;
            if (count==0) {
                card.remove(id);
            } else {
                card.put(id, count);
            }
            return  true;
        }
        return false;
    }

    public Map<Integer, Integer> getCard() {
        return card;
    }

    public void clearCard() {
        card.clear();
    }
}
