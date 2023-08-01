package com.example.oo_vko10;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DegreeListHolder extends RecyclerView.ViewHolder {
    /*
    Degree shown in RecyclerView
     */

    TextView degreeName;

    public DegreeListHolder(@NonNull View itemView) {
        super(itemView);
        degreeName = itemView.findViewById(R.id.txtDegreeName);
    }
}