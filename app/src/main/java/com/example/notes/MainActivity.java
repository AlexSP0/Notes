package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
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
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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

    private boolean navigateFragment(int itemId) {
        switch (itemId) {
            case R.id.main_drawer_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT);
                return true;
            case R.id.main_drawer_main:
                Toast.makeText(this, "DrawerMain", Toast.LENGTH_LONG);
                return true;
            case R.id.main_drawer_favorite:
                Toast.makeText(this, "favorite", Toast.LENGTH_LONG);
                return true;
            case R.id.main_menu_search:
                Toast.makeText(this, "search", Toast.LENGTH_LONG);
                return true;
            case R.id.main_menu_sort:
                Toast.makeText(this, "sort", Toast.LENGTH_LONG);
                return true;
            case R.id.main_menu_3:
                Toast.makeText(this, "3333333", Toast.LENGTH_LONG);
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