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
import com.example.notes.Add_View.ViewNotes;
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

    List<NoteBook> books;
    List<Note> notes;
    NoteBookAdapter noteBookAdapter;
    NoteAdapter noteAdapter;
    RecyclerView NBRV;
    RecyclerView NRV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getSupportActionBar().hide();

        books=new ArrayList<>();
        books.add(new NoteBook("add",R.drawable.addbook,"Create Notebook"));
        NBRV=findViewById(R.id.bookRV);

        notes=new ArrayList<>();
        NRV=findViewById(R.id.notesRV);


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        String path = "User/"+user.getUid()+"/Book";
        DatabaseReference ref = database.getReference(path);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                books.clear();
                notes.clear();
                books.add(new NoteBook("add",R.drawable.addbook,"Create Notebook"));

                for (DataSnapshot bookSnapshot:dataSnapshot.getChildren()){
                    String id=bookSnapshot.child("id").getValue().toString();
                    for (DataSnapshot noteSnapshot:bookSnapshot.child("Note").getChildren()){
                                String noteId=noteSnapshot.child("id").getValue().toString();
                                int mark=noteSnapshot.child("mark").getValue(Integer.class);
                                String date=noteSnapshot.child("date").getValue(String.class);
                                String title=noteSnapshot.child("title").getValue(String.class);
                                String txt=noteSnapshot.child("txt").getValue(String.class);

                                Note note=new Note(noteId,mark,date,title,txt);
                                notes.add(note);
                            }

                    int img=bookSnapshot.child("img").getValue(Integer.class);
                    String title=bookSnapshot.child("title").getValue(String.class);
                   NoteBook book=new NoteBook(id,img,title);
                    books.add(book);
                }
                noteBookAdapter = new NoteBookAdapter(getApplicationContext(), books, HomePageActivity.this);
                NBRV.setAdapter(noteBookAdapter);
                NBRV.setLayoutManager(new LinearLayoutManager(HomePageActivity.this, LinearLayoutManager.HORIZONTAL, false));

                noteAdapter = new NoteAdapter(HomePageActivity.this, notes, HomePageActivity.this);
                NRV.setAdapter(noteAdapter);
                NRV.setLayoutManager(new LinearLayoutManager(HomePageActivity.this, LinearLayoutManager.VERTICAL, false));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




    public void showAll(View view) {
        Intent intent=new Intent(this, ViewNoteBooks.class);
        startActivity(intent);
    }

    public void showNotes(View view) {
        Intent intent=new Intent(this, ShowAllNotes.class);
        startActivity(intent);
    }

    @Override
    public void onNoteClick(Note note) {
        Intent intent=new Intent(this, ViewMyNote.class);
        intent.putExtra("id",note.getId());
        startActivity(intent);
    }

    public void SignOut(View view) {
        FirebaseAuth.getInstance().signOut();
        finish();
        startActivity(new Intent(this, NotebookTutorialActivity.class));
    }



    @Override
    public void onNoteBookClick(NoteBook noteBook) {
        if (noteBook.getId().equals("add")){
            Intent intent = new Intent(this, AddNoteBookActivity.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(this, ViewNotes.class);
            intent.putExtra("id", noteBook.getId());
            startActivity(intent);
        }
    }
}
