package com.example.notes.NoteBook;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.Add_View.AllNotes;
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

public class AllNoteBook extends AppCompatActivity implements NoteBookItemClickListener {

   private List<NoteBook> bookList;
    private RecyclerView RVBN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_notebooks);


        bookList=new ArrayList<>();
        RVBN=findViewById(R.id.RVNoteB);

    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        String path = "User/"+user.getUid()+"/NoteBook";
        DatabaseReference ref = database.getReference(path);


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                bookList.clear();

                for (DataSnapshot bookSnapshot:dataSnapshot.getChildren()){

                    String id=bookSnapshot.child("id").getValue().toString();
                    int img=bookSnapshot.child("img").getValue(Integer.class);
                    String title=bookSnapshot.child("title").getValue(String.class);

                    NoteBook book=new NoteBook(id,img,title);
                    bookList.add(book);
                }

                RVBN.setAdapter(new NoteBookAdapter(AllNoteBook.this, bookList, AllNoteBook.this));
                RVBN.setLayoutManager(new GridLayoutManager(AllNoteBook.this, 3));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onNoteBookClick(NoteBook noteBook) {
        Intent intent = new Intent(this, AllNotes.class);
        intent.putExtra("id", noteBook.getId());
        startActivity(intent);
    }
}
