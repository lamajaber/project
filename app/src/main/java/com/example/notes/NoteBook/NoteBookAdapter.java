package com.example.notes.NoteBook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.notes.R;



import java.util.List;

public class NoteBookAdapter extends RecyclerView.Adapter<NoteBookAdapter.MyViewHolder> {

    Context context;
    List<NoteBook> mData;
    NoteBookItemClickListener Listener;

    public NoteBookAdapter(Context context, List<NoteBook> mData, NoteBookItemClickListener Listener) {
        this.context = context;
        this.mData = mData;
        this.Listener=Listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.book,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.TvTitle.setText(mData.get(position).getTitle());
        holder.ImgMovie.setImageResource(mData.get(position).getImg());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView TvTitle;
        private ImageView ImgMovie;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            TvTitle=itemView.findViewById(R.id.book_name);
            ImgMovie=itemView.findViewById(R.id.book_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Listener.onNoteBookClick(mData.get(getAdapterPosition()));
                }
            });

        }
    }
}
