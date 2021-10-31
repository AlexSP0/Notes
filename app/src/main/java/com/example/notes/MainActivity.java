package com.example.notes;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {
    private NotesArray notesArray;
    private NotesSettings notesSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notesSettings = new NotesSettings(this);  //Настройки приложения загружаем или создаем, если их нет.
        notesArray = new NotesArray(); //Загружаем заметки
        setContentView(R.layout.activity_main);
        initView();
        notesSettings.getSettingsFromSharedPrefs();
        navigateFragment(R.id.main_drawer_main);

    }

    private void initView() {
        initDrawer(initToolbar());
    }

    private void initDrawer(Toolbar toolbar) {
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                if (navigateFragment(id)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
                return false;
            }
        });
    }

    private Toolbar initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        return toolbar;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (navigateFragment(id)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean navigateFragment(int itemId) {
        Fragment fr;
        FragmentManager manager;
        switch (itemId) {
            case R.id.main_drawer_settings:
                fr = SettingsFragment.newInstance(notesArray, notesSettings);
                manager = getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.fragment_container, fr)
                        .commit();
                return true;
            case R.id.main_drawer_add:
                fr = AddNoteFragment.newInstance(notesArray, notesSettings, NotesSettings.NEW_NOTE_OPTION);
                manager = getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.fragment_container, fr)
                        .commit();
                return true;
            case R.id.main_drawer_edit:
                fr = AddNoteFragment.newInstance(notesArray, notesSettings, NotesSettings.EDIT_NOTE_OPTION);
                manager = getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.fragment_container, fr)
                        .commit();
                return true;
            case R.id.main_drawer_main:
                if (notesSettings.set.currentViewOfNotes == NotesSettings.CARD_VIEW) {
                    fr = NotesCardFragment.newInstance(notesArray, notesSettings);
                } else {
                    fr = NotesListFragment.newInstance(notesArray, notesSettings);
                }
                manager = getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.fragment_container, fr)
                        .commit();
                return true;
            case R.id.main_drawer_about:
                fr = AboutFragment.newInstance(notesArray, notesSettings);
                manager = getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.fragment_container, fr)
                        .commit();
                return true;
            case R.id.main_menu_search:
                Toast.makeText(this, "search", Toast.LENGTH_LONG);
                return true;
            case R.id.main_menu_sort:
                Toast.makeText(this, "sort", Toast.LENGTH_LONG);
                return true;
            case R.id.main_menu_current_note:
                fr = CurrentNoteFragment.newInstance(notesArray, notesSettings);
                manager = getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.fragment_container, fr)
                        .commit();
                return true;
            case R.id.current_note_menu_send:
                Toast.makeText(this, "CurrentNote  Send", Toast.LENGTH_LONG);
                return true;
            case R.id.current_note_menu_share:
                Toast.makeText(this, "CurrentNote Share", Toast.LENGTH_LONG);
                return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}