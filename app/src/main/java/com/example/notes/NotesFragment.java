package com.example.notes;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import static com.example.notes.NotesArray.*;

public class NotesFragment extends Fragment {
    public static final String NOTES_ARRAY_PARAM = "Notes_Array_Param";
    private NotesArray notesArray;
    private boolean isLandscapeFlag;

    public NotesFragment() {

    }

    // TODO: Rename and change types and number of parameters
    public static NotesFragment newInstance(Note arr) {
        NotesFragment fragment = new NotesFragment();
        Bundle args = new Bundle();
        args.putSerializable(NOTES_ARRAY_PARAM, arr);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            notesArray = (NotesArray) savedInstanceState.getSerializable(NOTES_ARRAY_PARAM);
        } else {
            //Заглушка!!! Если заметок в массиве нет, то делаем три тестовых заметки.
            Note n1 = Note.getBuilder().setHeader("Header1").setDescription("Description1").setNote("Note1").build();
            Note n2 = Note.getBuilder().setHeader("Header2").setDescription("Description2").setNote("Note2").build();
            Note n3 = Note.getBuilder().setHeader("Header3").setDescription("Description3").setNote("Note3").build();
            notesArray = new NotesArray();
            notesArray.addNote(n1);
            notesArray.addNote(n2);
            notesArray.addNote(n3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @org.jetbrains.annotations.NotNull View view,
                              @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNotes(view);
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isLandscapeFlag = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    private void initNotes(View view){
        LinearLayout linearLayout = (LinearLayout) view;
        for (int i = 0; i < notesArray.getSize(); i++) {
            TextView tv = new TextView(getContext());
            tv.setText(notesArray.getNote(i).getHeader());
            tv.setTextSize(30);
            final int fi = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowCurrentNote(notesArray.getNote(fi));
                }
            });
            tv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    notesArray.getNote(fi).setDate(callDate());
                    return false;
                }
            });
            linearLayout.addView(tv);
        }
    }
    private void ShowCurrentNote(Note note) {
        if(isLandscapeFlag) {
            ShowLandCurrentNote(note);
        } else {
            ShowPortCurrentNote(note);
        }
    }
    private void ShowPortCurrentNote(Note note) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), CurrentNoteActivity.class);
        intent.putExtra(CurrentNoteFragment.NOTE_PARAM, note);
        startActivity(intent);
    }
    private void ShowLandCurrentNote(Note note) {
        CurrentNoteFragment f = CurrentNoteFragment.newInstance(note);
        FragmentManager fragmentManager= requireActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.land_fragment_current_note, f)
                .commit();
    }
    private Calendar callDate() {
        Calendar cal = Calendar.getInstance();
        int mYear = cal.get(Calendar.YEAR);
        int mMonth = cal.get(Calendar.MONTH);
        int mDay = cal.get(Calendar.DAY_OF_MONTH);
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