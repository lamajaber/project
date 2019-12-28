package com.example.notes.NoteBook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.Adapter_Listener.NoteAdapter;
import com.example.notes.Adapter_Listener.NoteItemClickListener;
import com.example.notes.Add_View.ShowAllNotes;
import com.example.notes.Add_View.ViewMyNote;
import com.example.notes.Add_View.AllNotes;
import com.example.notes.Model.Note;
import com.example.notes.SignIn_Up.NotebookTutorialActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import com.example.notes.R;


public class HomePageActivity extends AppCompatActivity implements NoteBookItemClickListener, NoteItemClickListener {

    List<NoteBook> NotebookList;
    List<Note> noteList;

    RecyclerView NBRV;
    RecyclerView NRV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        getSupportActionBar().hide();
        NBRV = findViewById(R.id.bookRV);
        NRV = findViewById(R.id.notesRV);

        noteList = new ArrayList<>();
        NotebookList = new ArrayList<>();


        NotebookList.add(new NoteBook("add", R.drawable.addbook, "Create Notebook"));


    }

    @Override
    protected void onStart() {

        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        String path = user.getUid() + "/NoteBook";
        DatabaseReference ref = database.getReference(path);


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                NotebookList.clear();
                noteList.clear();

                NotebookList.add(new NoteBook("add", R.drawable.addbook, "Create Notebook"));

                for (DataSnapshot bookSnapshot : dataSnapshot.getChildren()) {
                    String id = bookSnapshot.child("id").getValue().toString();
                    for (DataSnapshot noteSnapshot : bookSnapshot.child("Note").getChildren()) {
                        String noteId = noteSnapshot.child("id").getValue().toString();
                        int imgNote = noteSnapshot.child("img").getValue(Integer.class);
                        String date = noteSnapshot.child("date").getValue(String.class);
                        String title = noteSnapshot.child("title").getValue(String.class);
                        String dec = noteSnapshot.child("dec").getValue(String.class);

                        Note note = new Note(noteId, imgNote, date, title, dec);
                        noteList.add(note);
                    }

                    int imgNoteBook = bookSnapshot.child("img").getValue(Integer.class);
                    String title = bookSnapshot.child("title").getValue(String.class);
                    NoteBook Notebook = new NoteBook(id, imgNoteBook, title);
                    NotebookList.add(Notebook);
                }


                NBRV.setAdapter(new NoteBookAdapter(getApplicationContext(), NotebookList, HomePageActivity.this));
                NBRV.setLayoutManager(new LinearLayoutManager(HomePageActivity.this, LinearLayoutManager.HORIZONTAL, false));


                NRV.setAdapter(new NoteAdapter(HomePageActivity.this, noteList, HomePageActivity.this));
                NRV.setLayoutManager(new LinearLayoutManager(HomePageActivity.this, LinearLayoutManager.VERTICAL, false));

            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


    public void showAll(View view) {
        Intent intent = new Intent(this, AllNoteBook.class);
        startActivity(intent);
    }

    public void showNotes(View view) {
        Intent intent = new Intent(this, ShowAllNotes.class);
        startActivity(intent);
    }

    @Override
    public void onNoteClick(Note note) {
        Intent intent = new Intent(this, ViewMyNote.class);
        intent.putExtra("id", note.getId());
        startActivity(intent);
    }

    public void SignOut(View view) {
        FirebaseAuth.getInstance().signOut();
        finish();
        startActivity(new Intent(this, NotebookTutorialActivity.class));
    }


    @Override
    public void onNoteBookClick(NoteBook noteBook) {
        if (noteBook.getId().equals("add")) {
            Intent intent = new Intent(this, AddNoteBookActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, AllNotes.class);
            intent.putExtra("id", noteBook.getId());
            startActivity(intent);
        }
    }
}
