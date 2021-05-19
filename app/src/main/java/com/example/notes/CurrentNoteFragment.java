package com.example.notes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class CurrentNoteFragment extends Fragment {
    public static final String NOTE_PARAM = "NoteParam";

    // TODO: Rename and change types and number of parameters
    public static CurrentNoteFragment newInstance(Note currentNote) {
        CurrentNoteFragment fragment = new CurrentNoteFragment();
        Bundle args = new Bundle();
        args.putSerializable(NOTE_PARAM, currentNote);
        fragment.setArguments(args);
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
        Note note = (Note) getArguments().getSerializable(NOTE_PARAM);
        textview.setText(note.getNoteText());
        return view;
    }
}