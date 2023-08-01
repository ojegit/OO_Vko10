package com.example.oo_vko10;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class DegreeListAdapter extends RecyclerView
        .Adapter<DegreeListHolder> {
    /*
    RecyclerView that contains the degrees to be displayed at degree_view.xml
     */

    private Context context;
    private ArrayList<String> degrees = new ArrayList<String>();

    public DegreeListAdapter(Context context, ArrayList<String> degrees) {
        this.context = context;
        this.degrees = degrees;
    }

    @NonNull
    @Override
    public DegreeListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //see DegreeListHolder.java
        return new DegreeListHolder(LayoutInflater.from(context)
                .inflate(R.layout.degree_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DegreeListHolder holder, int position) {
        //format textView before adding content
        if(position==0) { //title
            //holder.degreeName.setText(String.valueOf(degrees.get(position)));
            holder.degreeName.setText("Suoritetut tutkinnot:");
            holder.degreeName.setTypeface(null, Typeface.ITALIC);
        } else { //actual content
            //holder.degreeName.setText("- "+String.valueOf(degrees.get(position)));
            holder.degreeName.setText("- "+String.valueOf(degrees.get(position-1)));
        }
    }

    @Override
    public int getItemCount() {
        if (degrees.size() > 0) {
            return (degrees.size() + 1); //add one extra row to be printed for the title
        } else {
            return 0;
        }
    }
}
