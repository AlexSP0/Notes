package com.example.notes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
//фрагмент вывода содержимого одной заметки

public class CurrentNoteFragment extends Fragment {
    private NotesArray notesArray;
    private NotesSettings notesSettings;

    public static CurrentNoteFragment newInstance(NotesArray notesArray, NotesSettings notesSettings) {
        CurrentNoteFragment fragment = new CurrentNoteFragment();
        fragment.notesArray = notesArray;
        fragment.notesSettings = notesSettings;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_current_note, container, false);
        TextView textview = view.findViewById(R.id.current_note_text);
        textview.setText(notesArray.getNote(notesArray.getCurrentNote()).getNoteText());
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.current_note_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case R.id.current_note_menu_send:
                break;
            case R.id.current_note_menu_share:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}