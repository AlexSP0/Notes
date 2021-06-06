package com.example.notes;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;


public class NotesCardAdapter extends RecyclerView.Adapter<NotesCardAdapter.ViewHolder> {
    private NotesArray notesArray;
    private NotesSettings notesSettings;
    private Fragment fragment;
    private NotesCardAdapter.OnItemClickListener itemClickListener;

    public NotesCardAdapter(NotesArray notesArray, NotesSettings notesSettings, Fragment fragment) {
        this.notesArray = notesArray;
        this.notesSettings = notesSettings;
        this.fragment = fragment;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_card_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NotesCardAdapter.ViewHolder holder, int position) {
        holder.setCardData(notesArray.getNote(position));
    }

    @Override
    public int getItemCount() {
        return notesArray.getSize();
    }

    public void setOnItemClickListener(NotesCardAdapter.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView header;
        TextView body;
        ImageView image;
        CheckBox favorite;
        TextView date;
        Note note;

        @SuppressLint("ResourceType")
        public ViewHolder(View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.card_header_text);
            body = itemView.findViewById(R.id.card_note_text);
            image = itemView.findViewById(R.drawable.ic_launcher_foreground);
            favorite = itemView.findViewById(R.id.like);
            date = itemView.findViewById(R.id.card_text_footer);
            header.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
            header.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    v.showContextMenu();
                    notesArray.setCurrentNote(getAdapterPosition());
                    return true;
                }
            });
            registerContextMenu(itemView);
        }

        private void registerContextMenu(View itemView) {
            if (fragment != null) {
                fragment.registerForContextMenu(itemView);
            }
        }

        public void setCardData(Note note) {
            this.note = note;
            header.setText(note.getHeader());
            body.setText(note.getNoteText());
            if (note.isFavorite()) {
                favorite.setChecked(true);
            } else {
                favorite.setChecked(false);
            }
            date.setText(formatDate(note.getDate()));
        }

        public Note getNote() {
            return note;
        }
    }

    private String formatDate(Calendar calendar) {
        SimpleDateFormat sdf = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            sdf = new SimpleDateFormat("dd-MM-yyyy'Время 'HH:mm:ss");
        }
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+4"));
        return sdf.format(calendar.getTime());
    }
}
