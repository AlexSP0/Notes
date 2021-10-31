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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Month;
import java.time.Year;
import java.util.Calendar;

import static com.example.notes.NotesArray.*;
//Фрагмент для вывода заголовков заметок

public class NotesFragment extends Fragment {

    private NotesArray notesArray;
    private NotesSettings notesSettings;
    private boolean isLandscapeFlag;

    public NotesFragment() {

    }

    public static NotesFragment newInstance(NotesArray arr, NotesSettings settings) {
        NotesFragment fragment = new NotesFragment();
        fragment.notesArray = arr;
        fragment.notesSettings = settings;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNotes(view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isLandscapeFlag = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    private void initNotes(View view){

//        LinearLayout linearLayout = (LinearLayout) view;
//        for (int i = 0; i < notesArray.getSize(); i++) {
//            TextView tv = new TextView(getContext());
//            tv.setText(notesArray.getNote(i).getHeader());
//            tv.setTextSize(30);
//            final int fi = i;
//            tv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    notesArray.setCurrentNote(fi);
//                    ((MainActivity)getContext()).navigateFragment(R.id.main_menu_current_note);
//                }
//            });
//            tv.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    notesArray.getNote(fi).setDate(callDate(notesArray.getNote(fi).getDate()));
//                    return false;
//                }
//            });
//            linearLayout.addView(tv);
//        }
        //showList(view);


    }
    private void showCards(View view) {

    }
    private void showList(View view) {
//        LinearLayout linearLayout = (LinearLayout) view;
//        LayoutInflater layoutInflater = getLayoutInflater();
//        for (int i = 0; i < notesArray.getSize(); i++) {
//            final int fi = i;
//            View note_header = layoutInflater.inflate(R.layout.note_header, linearLayout, false);
//            TextView tv = note_header.findViewById(R.id.note_header_textview);
//            tv.setText(notesArray.getNote(fi).getHeader());
//            tv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    notesArray.setCurrentNote(fi);
//                    ((MainActivity)getContext()).navigateFragment(R.id.main_menu_current_note);
//                }
//            });
//            tv.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    notesArray.getNote(fi).setDate(callDate(notesArray.getNote(fi).getDate()));
//                    return false;
//                }
//            });
//            linearLayout.addView(note_header);
//        }

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