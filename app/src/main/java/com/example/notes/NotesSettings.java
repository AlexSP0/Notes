package com.example.notes;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

// класс для хранения констант и настроек, сохранение настроек в перференсах и их получение из преференс
public class NotesSettings {
    public static final String NOTE_PARAM = "NoteParam";
    public static final String NOTES_ARRAY_PARAM = "Notes_Array_Param";
    public static final String NOTES_SETTINGS_NAME_PARAM = "Notes_Settings";
    public static final int CARD_VIEW = 1;
    public static final int HEADER_VIEW = 0;
    public static final int NEW_NOTE_OPTION = -1;
    public static final int EDIT_NOTE_OPTION = 1;
    public NotesSettings.Set set; //Содержит все настройки
    private Context context;

    public NotesSettings(Context context) {

        this.context = context;
        set = new NotesSettings.Set();
        getSettingsFromSharedPrefs();
    }

    public boolean getSettingsFromSharedPrefs() { //получаем настройки из преференс
        SharedPreferences prefs = context.getSharedPreferences(NOTES_SETTINGS_NAME_PARAM, Context.MODE_PRIVATE);
        if(prefs.contains(NOTES_SETTINGS_NAME_PARAM)) {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            set = gson.fromJson(prefs.getString(NOTES_SETTINGS_NAME_PARAM, null), NotesSettings.Set.class);

        }
        return false;
    }
    public void setSettingsInSharedPrefs() { //сохраняем настройки в преференсах
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        SharedPreferences prefs = context.getSharedPreferences(NOTES_SETTINGS_NAME_PARAM, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(NOTES_SETTINGS_NAME_PARAM, gson.toJson(set));
        editor.apply();
    }

    public class Set {
        public int currentViewOfNotes; // Как просматривать заметки на главном экране
        public Set() { //Значения по умолчанию
            currentViewOfNotes = NotesSettings.HEADER_VIEW;
        }
    }
}
