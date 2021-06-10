package com.example.notes;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class AddNoteFragment extends Fragment {
    private NotesArray notesArray;
    private NotesSettings notesSettings;
    private EditText editHeader;
    private EditText editNoteText;
    private TextView textViewDate;
    private CheckBox isFavoriteCheckBox;
    private Button addNoteButton;
    private int newOrEditOption;
    private GregorianCalendar calendar;

    public AddNoteFragment() {

    }


    public static AddNoteFragment newInstance(NotesArray notesArray, NotesSettings notesSettings, int newOrEditOption) {
        AddNoteFragment fragment = new AddNoteFragment();
        fragment.notesArray = notesArray;
        fragment.notesSettings = notesSettings;
        fragment.calendar = new GregorianCalendar();
        fragment.newOrEditOption = newOrEditOption;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_note, container, false);
        editHeader = v.findViewById(R.id.add_note_header);
        editNoteText = v.findViewById(R.id.add_note_text);
        textViewDate = v.findViewById(R.id.add_note_date);
        textViewDate.setOnClickListener(v12 -> {
            calendar = (GregorianCalendar) callDate(calendar);
            textViewDate.setText(formatDate());
        });
        isFavoriteCheckBox = v.findViewById(R.id.add_note_checkbox_like);
        addNoteButton = v.findViewById(R.id.add_note_button);
        addNoteButton.setOnClickListener(v1 -> {
            setNoteData();
            ((MainActivity) getContext()).navigateFragment(R.id.main_drawer_main);
        });
        getNoteData();
        return v;
    }

    private void getNoteData() {
        if (newOrEditOption == NotesSettings.EDIT_NOTE_OPTION) {
            editHeader.setText(notesArray.getNote(notesArray.getCurrentNote()).getHeader());
            editNoteText.setText(notesArray.getNote(notesArray.getCurrentNote()).getNoteText());
            calendar = (GregorianCalendar) notesArray.getNote(notesArray.getCurrentNote()).getDate();
            textViewDate.setText(formatDate());
            if (notesArray.getNote(notesArray.getCurrentNote()).isFavorite()) {
                isFavoriteCheckBox.setChecked(true);
            }
            addNoteButton.setText("Save");
        } else {
            textViewDate.setText(formatDate());
        }
    }

    private void setNoteData() {
        if (newOrEditOption == NotesSettings.NEW_NOTE_OPTION) {
            Note newNote = Note.getBuilder().setHeader(editHeader.getText().toString()).setNote(editNoteText.getText().toString()).setDescription(" ").build();
            newNote.setDate(calendar);
            if (isFavoriteCheckBox.isChecked()) {
                newNote.setFavorite(true);
            }
            notesArray.addNote(newNote);
            notesArray.setCurrentNote(notesArray.getSize() - 1);
        } else {
            int cn = notesArray.getCurrentNote();
            Note newNote = Note.getBuilder().setHeader(editHeader.getText().toString()).setDescription(" ").setNote(editNoteText.getText().toString()).build();
            newNote.setId(notesArray.getNote(notesArray.getCurrentNote()).getId());
            newNote.setDate(calendar);
            notesArray.getNote(cn).clone(newNote);
            notesArray.updateNote(notesArray.getNote(notesArray.getCurrentNote()));
        }
    }

    private String formatDate() {
        SimpleDateFormat sdf = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            sdf = new SimpleDateFormat("dd-MM-yyyy'Время 'HH:mm:ss");
        }
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+4"));
        return sdf.format(calendar.getTime());
    }

    private Calendar callDate(Calendar oldDate) {
        Calendar cal = Calendar.getInstance();
        int mYear = oldDate.get(Calendar.YEAR);
        int mMonth = oldDate.get(Calendar.MONTH);
        int mDay = oldDate.get(Calendar.DAY_OF_MONTH);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            DatePickerDialog d = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    cal.set(year, month, dayOfMonth);
                }
            }, mYear, mMonth, mDay);
            d.show();
        }
        return cal;
    }
}