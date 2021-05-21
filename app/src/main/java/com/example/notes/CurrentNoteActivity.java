package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
//Активити для вывода фаргмента с содержимым заметки
public class CurrentNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_note);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        //if(savedInstanceState == null) {
            CurrentNoteFragment fragment = CurrentNoteFragment.newInstance((Note)getIntent().getSerializableExtra(CurrentNoteFragment.NOTE_PARAM));
            fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_current_note,fragment)
                    .commit();
        //}
    }

}