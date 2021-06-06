package com.example.notes;

import java.io.Serializable;
import java.util.ArrayList;

//Класс, который хранит все заметки
//********** Убрать Serializable ******************************************
public class NotesArray implements Serializable {
    private ArrayList<Note> notesArray;
    private int size;
    private int currentNote;

    public NotesArray() {
        notesArray = new ArrayList<>();
        currentNote = 0;
        initDefaultNotes(); //Заглушка, создаем несколько заметок для работы
        //Далее будет код, загружающий заметки из БД
    }

    public void addNote(Note note) {
        notesArray.add(note);
    }

    public Note getNote(int index) {
        return notesArray.get(index);
    }

    public int getSize() {
        return notesArray.size();
    }

    public int getCurrentNote() {
        return currentNote;
    }
    public void setCurrentNote(int i) {
        currentNote = i;
    }

    private void initDefaultNotes() {
        Note.NoteBuilder builder = Note.getBuilder();
        addNote(builder.setHeader("Заметка №1").setDescription("Описание заметки №1").setNote("Содержание заметки №1").build());
        builder = Note.getBuilder();
        addNote(builder.setHeader("Заметка №2").setDescription("Описание заметки №2").setNote("Содержание заметки №2").build());
        builder = Note.getBuilder();
        addNote(builder.setHeader("Заметка №3").setDescription("Описание заметки №3").setNote("Содержание заметки №3").build());
        builder = Note.getBuilder();
        addNote(builder.setHeader("Заметка №4").setDescription("Описание заметки №4").setNote("Содержание заметки №4").build());
        builder = Note.getBuilder();
        addNote(builder.setHeader("Заметка №5").setDescription("Описание заметки №5").setNote("Содержание заметки №5").build());
        builder = Note.getBuilder();
        addNote(builder.setHeader("Заметка №6").setDescription("Описание заметки №6").setNote("Содержание заметки №6").build());
        builder = Note.getBuilder();
        addNote(builder.setHeader("Заметка №7").setDescription("Описание заметки №7").setNote("Содержание заметки №7").build());
        builder = Note.getBuilder();
        addNote(builder.setHeader("Заметка №8").setDescription("Описание заметки №8").setNote("Содержание заметки №8").build());
        builder = Note.getBuilder();
        addNote(builder.setHeader("Заметка №9").setDescription("Описание заметки №9").setNote("Содержание заметки №9").build());
        builder = Note.getBuilder();
        addNote(builder.setHeader("Заметка №10").setDescription("Описание заметки №10").setNote("Содержание заметки №10").build());
        builder = Note.getBuilder();
        addNote(builder.setHeader("Заметка №11").setDescription("Описание заметки №11").setNote("Содержание заметки №11").build());
        builder = Note.getBuilder();
        addNote(builder.setHeader("Заметка №12").setDescription("Описание заметки №12").setNote("Содержание заметки №12").build());
        builder = Note.getBuilder();
        addNote(builder.setHeader("Заметка №13").setDescription("Описание заметки №13").setNote("Содержание заметки №13").build());
        builder = Note.getBuilder();
        addNote(builder.setHeader("Заметка №14").setDescription("Описание заметки №14").setNote("Содержание заметки №14").build());
    }
}
