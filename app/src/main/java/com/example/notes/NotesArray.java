package com.example.notes;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//Класс, который хранит все заметки
public class NotesArray {
    private ArrayList<Note> notesArray;
    private int currentNote;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private NotesDbResponse dbResponse;
    public static final String NOTES_COLLECTION = "NOTES";

    public NotesArray() {
        notesArray = new ArrayList<>();
        currentNote = 0;
        //initDefaultNotes(); //Заглушка, создаем несколько заметок для работы
        getNotesFromDb();
    }

    public void setSuccessListener(NotesDbResponse resp) {
        dbResponse = resp;
    }

    public void getNotesFromDb() {
        CollectionReference ref = db.collection(NOTES_COLLECTION);
        ref.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for (QueryDocumentSnapshot doc : task.getResult()) {
                        Map map = doc.getData();
                        Note note = NoteMapper.toNote(map);
                        note.setId(doc.getId());
                        notesArray.add(note);
                    }
                    if(dbResponse != null) {
                        dbResponse.initialized();
                    }
                }
            }
        });
    }

    public void addNote(Note note) {
        notesArray.add(note);
        CollectionReference ref = db.collection(NOTES_COLLECTION);
        DocumentReference docRef = ref.document();
        note.setId(docRef.getId());
        docRef.set(NoteMapper.toDocument(note));
        if(dbResponse != null) {
            dbResponse.initialized();
        }
    }
    public void deleteNote(int index) {
        CollectionReference ref = db.collection(NOTES_COLLECTION);
        DocumentReference docRef = ref.document(getNote(getCurrentNote()).getId());
        docRef.delete();
        clearNotes();
        getNotesFromDb();
    }

    private void clearNotes() {
        notesArray = new ArrayList<>();
    }

    public void updateNote(Note note) {
        CollectionReference ref = db.collection(NOTES_COLLECTION);
        DocumentReference docRef = ref.document(note.getId());
        docRef.set(NoteMapper.toDocument(note));
        if(dbResponse != null) {
            dbResponse.initialized();
        }

   }

    public Note getNote(int index) {
        return notesArray.get(index);
    }

    public int getSize() {
        return notesArray.size();
    }

    public int getCurrentNote() {
        return currentNote;
    }
    public void setCurrentNote(int i) {
        currentNote = i;
    }

    private void initDefaultNotes() {
        Note.NoteBuilder builder = Note.getBuilder();
        addNote(builder.setHeader("Заметка №1").setDescription("Описание заметки №1").setNote("Содержание заметки №1").build());
        builder = Note.getBuilder();
        addNote(builder.setHeader("Заметка №2").setDescription("Описание заметки №2").setNote("Содержание заметки №2").build());
        builder = Note.getBuilder();
        addNote(builder.setHeader("Заметка №3").setDescription("Описание заметки №3").setNote("Содержание заметки №3").build());
        builder = Note.getBuilder();
        addNote(builder.setHeader("Заметка №4").setDescription("Описание заметки №4").setNote("Содержание заметки №4").build());
        builder = Note.getBuilder();
        addNote(builder.setHeader("Заметка №5").setDescription("Описание заметки №5").setNote("Содержание заметки №5").build());
        builder = Note.getBuilder();
        addNote(builder.setHeader("Заметка №6").setDescription("Описание заметки №6").setNote("Содержание заметки №6").build());
        builder = Note.getBuilder();
        addNote(builder.setHeader("Заметка №7").setDescription("Описание заметки №7").setNote("Содержание заметки №7").build());
        builder = Note.getBuilder();
        addNote(builder.setHeader("Заметка №8").setDescription("Описание заметки №8").setNote("Содержание заметки №8").build());
        builder = Note.getBuilder();
        addNote(builder.setHeader("Заметка №9").setDescription("Описание заметки №9").setNote("Содержание заметки №9").build());
        builder = Note.getBuilder();
        addNote(builder.setHeader("Заметка №10").setDescription("Описание заметки №10").setNote("Содержание заметки №10").build());
        builder = Note.getBuilder();
        addNote(builder.setHeader("Заметка №11").setDescription("Описание заметки №11").setNote("Содержание заметки №11").build());
        builder = Note.getBuilder();
        addNote(builder.setHeader("Заметка №12").setDescription("Описание заметки №12").setNote("Содержание заметки №12").build());
        builder = Note.getBuilder();
        addNote(builder.setHeader("Заметка №13").setDescription("Описание заметки №13").setNote("Содержание заметки №13").build());
        builder = Note.getBuilder();
        addNote(builder.setHeader("Заметка №14").setDescription("Описание заметки №14").setNote("Содержание заметки №14").build());
        initExampleBase();
    }

    private void initExampleBase() { //Заглушка, для первоначальной загрузки заметок в базу данных
//        FirebaseFirestore store = FirebaseFirestore.getInstance();
//        CollectionReference ref = store.collection("NOTES");
//        for (Note note: notesArray) {
//            Map<String, Object> noteMap = NoteMapper.toDocument(note);
//            ref.document().set(noteMap);
//        }
    }
}
