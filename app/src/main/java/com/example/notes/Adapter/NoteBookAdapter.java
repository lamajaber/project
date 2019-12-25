package com.example.notes.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.Model.NoteBook;
import com.example.notes.R;

import java.util.List;

public class NoteBookAdapter extends RecyclerView.Adapter<NoteBookAdapter.NoteBookVH> {

    Context context;
    List<NoteBook> noteBooksList;

    public NoteBookAdapter(Context context, List<NoteBook> noteBooksList) {
        this.context = context;
        this.noteBooksList = noteBooksList;
    }

    @NonNull
    @Override
    public NoteBookAdapter.NoteBookVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_notbook, parent, false);
        return new NoteBookAdapter.NoteBookVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteBookAdapter.NoteBookVH holder, int position) {

        holder.txtTitle.setText(noteBooksList.get(position).getTitile());
        noteBooksList.get(position).getImge();
        holder.imgNote.setImageResource(noteBooksList.get(position).getImge());


    }

    @Override
    public int getItemCount() {
        return noteBooksList.size();
    }


    class NoteBookVH extends RecyclerView.ViewHolder {

        ImageView imgNote;
        TextView txtTitle;

        public NoteBookVH(@NonNull final View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtNameRowNotebook);
            imgNote = itemView.findViewById(R.id.imgRowNotebook);

        }


        public void setData(final NoteBook noteBook) {
            txtTitle.setText(noteBook.getTitile());
            txtTitle.setText(noteBook.getTitile());



        }
    }
}

