package com.example.notes;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Note implements Serializable {
    private String header;
    private String description;
    private String note;
    private long date;
    GregorianCalendar dateOfCreation;

    private Note() {
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

    public void setDate(Calendar cal) {
        date=cal.getTimeInMillis();
    }

    public static NoteBuilder getBuilder() {
        return new Note().new NoteBuilder();
    }

    public class NoteBuilder {
        private NoteBuilder() {        }

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
            if(header != null && description != null) {
                dateOfCreation = new GregorianCalendar();
                return Note.this;
            }
            return null;
        }
    }
}
