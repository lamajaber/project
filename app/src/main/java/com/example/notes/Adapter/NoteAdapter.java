package com.example.notes.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.Model.Note;
import com.example.notes.R;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteVH> {

    Context context;
    List<Note> noteList;

    public NoteAdapter(Context context, List<Note> noteList) {
        this.context = context;
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public NoteVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_note, parent, false);
        return new NoteVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteVH holder, int position) {
         holder.setData(noteList.get(position));

    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }


    class NoteVH extends RecyclerView.ViewHolder {

        ImageView imgNote;
        TextView txtTitle, txtDate, txtDes;

        public NoteVH(@NonNull final View itemView) {
            super(itemView);

            imgNote = itemView.findViewById(R.id.imgRowNote);
            txtTitle = itemView.findViewById(R.id.txtNameRowNote);
            txtDate = itemView.findViewById(R.id.txtDateRowNote);
            txtDes = itemView.findViewById(R.id.txtDescRowNote);


        }


        public void setData(final Note note) {
            txtTitle.setText(note.getTitle());
            txtDate.setText(note.getDate());
            txtDes.setText(note.getDescription());

        }
    }
}

