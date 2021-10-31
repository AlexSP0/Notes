package com.example.notes

import android.content.Context
import com.google.gson.GsonBuilder

// класс для хранения констант и настроек, сохранение настроек в перференсах и их получение из преференс
class NotesSettings(private val context: Context) {
    @JvmField
    var set //Содержит все настройки
            : Set

    //получаем настройки из преференс
    val settingsFromSharedPrefs: Boolean
        get() { //получаем настройки из преференс
            val prefs = context.getSharedPreferences(NOTES_SETTINGS_NAME_PARAM, Context.MODE_PRIVATE)
            if (prefs.contains(NOTES_SETTINGS_NAME_PARAM)) {
                val builder = GsonBuilder()
                val gson = builder.create()
                set = gson.fromJson(prefs.getString(NOTES_SETTINGS_NAME_PARAM, null), Set::class.java)
            }
            return false
        }

    fun setSettingsInSharedPrefs() { //сохраняем настройки в преференсах
        val builder = GsonBuilder()
        val gson = builder.create()
        val prefs = context.getSharedPreferences(NOTES_SETTINGS_NAME_PARAM, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(NOTES_SETTINGS_NAME_PARAM, gson.toJson(set))
        editor.apply()
    }

    inner class Set {
        @JvmField
        var currentViewOfNotes // Как просматривать заметки на главном экране
                : Int

        init { //Значения по умолчанию
            currentViewOfNotes = HEADER_VIEW
        }
    }

    companion object {
        const val NOTE_PARAM = "NoteParam"
        const val NOTES_ARRAY_PARAM = "Notes_Array_Param"
        const val NOTES_SETTINGS_NAME_PARAM = "Notes_Settings"
        const val CARD_VIEW = 1
        const val HEADER_VIEW = 0
        const val NEW_NOTE_OPTION = -1
        const val EDIT_NOTE_OPTION = 1
    }

    init {
        set = Set()
        settingsFromSharedPrefs
    }
}