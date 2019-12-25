package com.example.notes.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.Model.NoteBook;
import com.example.notes.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {

    ImageView imgCAccent, imgAddNotebook, imgCreateNoteBook;
    TextView txtEditAccent, ShowAll_Notebooks, ShowAll_Notes, cancelNotebook;

    RecyclerView RVNoteBook, RV_Notes;
    EditText editTitleCreate;

    FirebaseDatabase database;
    DatabaseReference myRef;
    Button imgChangColor;
    int imgNB, imgCjange;
     Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_activity);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("NoteBook");

        imgCAccent = findViewById(R.id.imgColorHome);
        imgAddNotebook = findViewById(R.id.imgAddNotebook);

        txtEditAccent = findViewById(R.id.txtEditAccent);
        ShowAll_Notebooks = findViewById(R.id.ShowAll_Notebooks);
        ShowAll_Notes = findViewById(R.id.ShowAll_Notes);

        RVNoteBook = findViewById(R.id.RV_Notebooks);
        RV_Notes = findViewById(R.id.RV_Notes);



    }

    public void imgAddNotebook(View view) {
        showCustomDialog(this);
    }


    public void showCustomDialog(final Context context) {
         dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.create_notebook_page_activity, null, false);
        ((Activity) context).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        dialog.setContentView(view);
        final Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawableResource(R.color.accent);
        window.setGravity(Gravity.CENTER);
        dialog.show();
        editTitleCreate = view.findViewById(R.id.editTitleCreate);
        imgCreateNoteBook = view.findViewById(R.id.imgCreateNoteBook);
        imgChangColor = view.findViewById(R.id.imgChangColor);
        imgChangColor.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                imgNB = R.drawable.s7;
                imgCjange = R.color.s7;
                view.findViewById(R.id.include_ChangeCOlor).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_m1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imgCreateNoteBook.setBackgroundResource(R.drawable.s1);
                        imgChangColor.setBackgroundColor(R.color.s1);
                        imgNB = R.drawable.s1;
                        imgCjange = R.color.s1;
                    }
                });

                view.findViewById(R.id.img_m2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imgCreateNoteBook.setBackgroundResource(R.drawable.s2);
                        imgChangColor.setBackgroundColor(R.color.s2);
                        imgNB = R.drawable.s2;
                        imgCjange = R.color.s2;
                    }
                });


                view.findViewById(R.id.img_m3).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imgCreateNoteBook.setBackgroundResource(R.drawable.s3);
                        imgChangColor.setBackgroundColor(R.color.s3);
                        imgNB = R.drawable.s3;
                        imgCjange = R.color.s3;
                    }
                });


                view.findViewById(R.id.img_m4).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imgCreateNoteBook.setBackgroundResource(R.drawable.s4);
                        imgChangColor.setBackgroundColor(R.color.s4);
                        imgNB = R.drawable.s4;
                        imgCjange = R.color.s4;
                    }
                });



                view.findViewById(R.id.img_m5).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imgCreateNoteBook.setBackgroundResource(R.drawable.s5);
                        imgChangColor.setBackgroundColor(R.color.s5);
                        imgNB = R.drawable.s5;
                        imgCjange = R.color.s5;
                    }
                });



                view.findViewById(R.id.img_m6).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imgCreateNoteBook.setBackgroundResource(R.drawable.s6);
                        imgChangColor.setBackgroundColor(R.color.s6);
                        imgNB = R.drawable.s6;
                        imgCjange = R.color.s6;
                    }
                });


                view.findViewById(R.id.img_m7).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imgCreateNoteBook.setBackgroundResource(R.drawable.s7);
                        imgChangColor.setBackgroundColor(R.color.s7);
                        imgNB = R.drawable.s7;
                        imgCjange = R.color.s7;
                    }
                });


                view.findViewById(R.id.img_m8).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imgCreateNoteBook.setBackgroundResource(R.drawable.s8);
                        imgChangColor.setBackgroundColor(R.color.s8);
                        imgNB = R.drawable.s8;
                        imgCjange = R.color.s8;
                    }
                });

            }
        });

        view.findViewById(R.id.btn_SaveNotebook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleNB = editTitleCreate.getText().toString();
                myRef.push().setValue(new NoteBook(titleNB, imgNB, imgCjange));
                dialog.dismiss();
            }
        });

        view.findViewById(R.id.cancelNotebook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
}
