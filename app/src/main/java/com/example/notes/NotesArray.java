package com.example.notes;

import java.io.Serializable;
import java.util.ArrayList;
//Класс, который хранит все заметки

public class NotesArray implements Serializable {
    private ArrayList<Note> notesArray;
    private int size;

    public NotesArray(){
        notesArray = new ArrayList<>();
    }

    public void addNote (Note note) {
        notesArray.add(note);
    }

    public Note getNote(int index) {
        return notesArray.get(index);
    }

    public int getSize() {
        return notesArray.size();
    }
}
