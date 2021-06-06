package com.example.notes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AboutFragment extends Fragment {
    private NotesArray notesArray;
    private NotesSettings notesSettings;
    public AboutFragment() {

    }

    public static AboutFragment newInstance(NotesArray notesArray, NotesSettings notesSettings) {
        AboutFragment fragment = new AboutFragment();
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
        return inflater.inflate(R.layout.fragment_about, container, false);
    }
}