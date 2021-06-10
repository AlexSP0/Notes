package com.example.notes;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
//Класс заметки


public class Note implements Serializable {
    private String id;
    private String header;
    private String description;
    private String note;
    private GregorianCalendar dateOfCreation;
    private boolean isFavorite;


    private Note() {
    }

    public void clone(Note note) {
        id = note.getId();
        header = note.getHeader();
        description = note.getDescription();
        this.note = note.getNoteText();
        dateOfCreation = (GregorianCalendar) note.getDate();
        isFavorite = note.isFavorite();
    }

    public String getId() {
        return id;
    }

    public String getHeader() {
        return header;
    }

    public String getDescription() {
        return description;
    }

    public String getNoteText() {
        return note;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNoteText(String text) {
        note = text;
    }

    public void setDate(Calendar cal) {
        dateOfCreation.setTimeInMillis(cal.getTimeInMillis());
    }

    public Calendar getDate() {
        return dateOfCreation;
    }

    public static NoteBuilder getBuilder() {
        return new Note().new NoteBuilder();
    }

    public class NoteBuilder {
        private NoteBuilder() {
        }

        public NoteBuilder setHeader(String header) {
            Note.this.header = header;
            return this;
        }

        public NoteBuilder setDescription(String description) {
            Note.this.description = description;
            return this;
        }

        public NoteBuilder setNote(String note) {
            Note.this.note = note;
            return this;
        }

        public Note build() {
            if (header != null && description != null) {
                dateOfCreation = new GregorianCalendar();
                isFavorite = false;
                return Note.this;
            }
            return null;
        }
    }
}
