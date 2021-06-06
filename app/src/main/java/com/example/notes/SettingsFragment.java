package com.example.notes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingsFragment extends Fragment {
    private NotesArray notesArray;
    private NotesSettings notesSettings;
    SwitchMaterial cardSwitch;

    public SettingsFragment() {
        // Required empty public constructor
    }


    public static SettingsFragment newInstance(NotesArray notesArray, NotesSettings notesSettings) {
        SettingsFragment fragment = new SettingsFragment();
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
        View v =inflater.inflate(R.layout.fragment_settings, container, false);
        cardSwitch = v.findViewById(R.id.card_switch);
        cardSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    notesSettings.set.currentViewOfNotes = NotesSettings.CARD_VIEW;
                } else {
                    notesSettings.set.currentViewOfNotes = NotesSettings.HEADER_VIEW;
                }
                notesSettings.setSettingsInSharedPrefs();
            }
        });
        if(notesSettings.set.currentViewOfNotes == NotesSettings.CARD_VIEW) {
            cardSwitch.setChecked(true);
        } else {
            cardSwitch.setChecked(false);
        }
        return v;
    }
}