package com.example.notes.Adapter;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.notes.R;
import com.smarteist.autoimageslider.SliderViewAdapter;


public class SliderAdapterNote extends SliderViewAdapter<SliderAdapterNote.SliderAdapterVH> {

    private Context context;

    public SliderAdapterNote(Context context) {
        this.context = context;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.tutorial, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {

        switch (position) {
            case 0:
                final SpannableStringBuilder sb = new SpannableStringBuilder("Notebooks are the best place to manage your Notes");

                final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);
                final StyleSpan iss = new StyleSpan(android.graphics.Typeface.ITALIC);
                sb.setSpan(iss, 0, 44, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                sb.setSpan(bss, 44, 49, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                viewHolder.img.setImageResource(R.drawable.tutorial);
                viewHolder.title.setText("Notebooks");
                viewHolder.des.setText(sb);
                break;

            case 1:
                viewHolder.img.setImageResource(R.drawable.tutorial2);
                viewHolder.title.setText("Add Notes to Notebook");
                viewHolder.des.setText("Simply create your note and add it to your favorite notebook");
                break;

            default:
                break;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }


    class SliderAdapterVH extends SliderAdapterNote.ViewHolder {

        private View itemView;
        private ImageView img;
        private TextView title,des;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_tutorial);
            title = itemView.findViewById(R.id.name_tutorial);
            des = itemView.findViewById(R.id.dec_tutorial);

            this.itemView = itemView;
        }
    }
}
