package com.example.notes;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static com.example.notes.NoteMapper.NoteFields.DESCRIPTION;
import static com.example.notes.NoteMapper.NoteFields.FAVORITE;
import static com.example.notes.NoteMapper.NoteFields.HEADER;
import static com.example.notes.NoteMapper.NoteFields.ID;
import static com.example.notes.NoteMapper.NoteFields.NOTE_TEXT;
import static com.example.notes.NoteMapper.NoteFields.TIMESTAMP;

public class NoteMapper {
    public static class NoteFields {
        public static final String ID = "NoteId";
        public static final String HEADER = "NoteHeader";
        public static final String DESCRIPTION = "NoteDescription";
        public static final String NOTE_TEXT = "NoteText";
        public static final String TIMESTAMP = "Timestamp";
        public static final String FAVORITE = "IsFavorite";
    }
    public static Map<String, Object> toDocument(Note note) {
        Map<String, Object> map = new HashMap<>();
        map.put(ID, note.getId());
        map.put(HEADER, note.getHeader());
        map.put(DESCRIPTION, note.getDescription());
        map.put(NOTE_TEXT, note.getNoteText());
        map.put(TIMESTAMP, note.getDate().getTimeInMillis());
        map.put(FAVORITE, note.isFavorite());
        return map;
    }
    public static Note toNote (Map<String, Object> data) {
        Note note = Note.getBuilder().setHeader((String)data.get(HEADER))
                .setDescription((String) data.get(DESCRIPTION))
                .build();
        note.setId((String)data.get(ID));
        note.setNoteText((String)data.get(NOTE_TEXT));
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTimeInMillis((long)data.get(TIMESTAMP));
        note.setDate(cal);
        note.setFavorite((boolean)data.get(FAVORITE));
        return note;
    }
}
