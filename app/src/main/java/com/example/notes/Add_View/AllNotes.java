package com.example.notes.Add_View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.notes.Adapter_Listener.NoteAdapter;
import com.example.notes.Adapter_Listener.NoteItemClickListener;
import com.example.notes.Model.Note;
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

public class AllNotes extends AppCompatActivity implements NoteItemClickListener {

    List<Note> noteList;
    ImageView imageView;
    TextView textView;
    String id;
    RecyclerView noteRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);


        id = getIntent().getStringExtra("id");

        imageView = findViewById(R.id.image_view);
        textView = findViewById(R.id.textView3);

        noteList = new ArrayList<>();

        noteRV = findViewById(R.id.RVNote);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String path = "User/" + user.getUid() + "/Book/" + id + "/Note";
        DatabaseReference ref = database.getReference(path);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                noteList.clear();

                for (DataSnapshot noteSnapshot : dataSnapshot.getChildren()) {
                    String id = noteSnapshot.child("id").getValue().toString();
                    int img = noteSnapshot.child("img").getValue(Integer.class);
                    String date = noteSnapshot.child("date").getValue(String.class);
                    String title = noteSnapshot.child("title").getValue(String.class);
                    String dec = noteSnapshot.child("dec").getValue(String.class);

                    Note note = new Note(id, img, date, title, dec);
                    noteList.add(note);

                }

                noteRV.setAdapter(new NoteAdapter(AllNotes.this, noteList, AllNotes.this));
                noteRV.setLayoutManager(new LinearLayoutManager(AllNotes.this, LinearLayoutManager.VERTICAL, false));

                if (noteList.size() == 0) {

                    imageView.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                } else {
                    imageView.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addNote(View view) {
        Intent intent = new Intent(this, AddNoteActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    @Override
    public void onNoteClick(Note note) {
        Intent intent = new Intent(this, ViewMyNote.class);
        intent.putExtra("id", note.getId());
        startActivity(intent);
    }

    public void Done(View view) {
        this.finish();
    }
}
