package com.example.notes.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.Adapter.NoteBookAdapter;
import com.example.notes.Model.NoteBook;
import com.example.notes.R;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NotebookActivity extends AppCompatActivity {


    FirebaseDatabase FDB;

    NoteBookAdapter NBAapter;
    RecyclerView myrv;
    List<NoteBook> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notebook_activity);
        FDB = FirebaseDatabase.getInstance();
        listData = new ArrayList<>();
        myrv = findViewById(R.id.RVNB);
        myrv.setHasFixedSize(true);
        NBAapter = new NoteBookAdapter(this, listData);
        myrv.setLayoutManager(new LinearLayoutManager(this));
        myrv.setItemAnimator(new DefaultItemAnimator());
        myrv.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));

        listData = new ArrayList<>();

        final DatabaseReference nm = FirebaseDatabase.getInstance().getReference("data");
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                        NoteBook l = npsnapshot.getValue(NoteBook.class);
                        listData.add(l);
                    }
                    NBAapter = new NoteBookAdapter(getApplicationContext(), listData);
                    myrv.setAdapter(NBAapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}