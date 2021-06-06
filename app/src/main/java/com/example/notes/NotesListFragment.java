package com.example.notes;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

public class NotesListFragment extends Fragment {

    private NotesArray notesArray;
    private NotesSettings notesSettings;

    public NotesListFragment() {
        // Required empty public constructor
    }

    public static NotesListFragment newInstance(NotesArray arr, NotesSettings settings) {
        NotesListFragment fragment = new NotesListFragment();
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
        View view = inflater.inflate(R.layout.notes_list_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_lines);
        initRecyclerView(recyclerView, notesArray);
        return view;
    }

    private void initRecyclerView(RecyclerView recyclerView, NotesArray notesArray) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        NotesAdapter notesAdapter = new NotesAdapter(notesArray, notesSettings, this);
        recyclerView.setAdapter(notesAdapter);
        notesAdapter.setOnItemClickListener(new NotesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                notesArray.setCurrentNote(position);
                ((MainActivity) getContext()).navigateFragment(R.id.main_menu_current_note);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = requireActivity().getMenuInflater();
        menuInflater.inflate(R.menu.notes_context_menu, menu);
        // AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        //notesArray.setCurrentNote(info.position);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add_new_note_context_menu:
                ((MainActivity) getContext()).navigateFragment(R.id.main_drawer_add);
                break;
            case R.id.edit_note_context_menu:
                ((MainActivity) getContext()).navigateFragment(R.id.main_drawer_edit);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNotes(view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initNotes(View view) {

    }
}