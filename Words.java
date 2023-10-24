package com.example.dictionaryy;

import javafx.beans.property.SimpleStringProperty;

public class Words {
    private final SimpleStringProperty word;
    private final SimpleStringProperty meaning;

    public Words(String word, String meaning) {
        this.word = new SimpleStringProperty(word);
        this.meaning = new SimpleStringProperty(meaning);
    }

    public void setWord(String word) {
        this.word.set(word);
    }

    public String getWord() {
        return word.get();
    }

    public void setMeaning(String meaning) {
        this.meaning.set(meaning);
    }

    public String getMeaning() {
        return meaning.get();
    }
}
