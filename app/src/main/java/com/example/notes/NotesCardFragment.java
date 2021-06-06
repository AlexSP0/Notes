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

import org.jetbrains.annotations.NotNull;

public class NotesCardFragment extends Fragment {
    private NotesArray notesArray;
    private NotesSettings notesSettings;
    private NotesCardAdapter adapter;
    private boolean isLandscapeFlag;


    public NotesCardFragment() {
        // Required empty public constructor
    }

    public static NotesCardFragment newInstance(NotesArray arr, NotesSettings settings) {
        NotesCardFragment fragment = new NotesCardFragment();
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
        View view = inflater.inflate(R.layout.fragment_notes_card, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_card);
        initRecyclerView(recyclerView);
        return view;
    }

    private void initRecyclerView(RecyclerView recyclerView) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NotesCardAdapter(notesArray, notesSettings, this);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new NotesCardAdapter.OnItemClickListener() {
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

    private void initNotes(View view) {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isLandscapeFlag = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }
}