package com.example.notes.Adapter_Listener;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.Model.Note;
import com.example.notes.R;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {

    Context context;
    List<Note> mData;
    NoteItemClickListener Listener;

    public NoteAdapter(Context context, List<Note> mData, NoteItemClickListener Listener) {
        this.context = context;
        this.mData = mData;
        this.Listener=Listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.note,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.imgNote.setBackgroundColor(mData.get(position).getImg());
        holder.date.setText(mData.get(position).getDate());
        holder.txtTitle.setText(mData.get(position).getTitle());
        holder.txtDec.setText(mData.get(position).getDec());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private View imgNote;
        private TextView date ,txtTitle,txtDec;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imgNote=itemView.findViewById(R.id.imgRowNote);
            date=itemView.findViewById(R.id.txtDateRowNote);
            txtTitle=itemView.findViewById(R.id.txtNameRowNote);
            txtDec=itemView.findViewById(R.id.txtDescRowNote);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Listener.onNoteClick(mData.get(getAdapterPosition()));
                }
            });

        }
    }
}
