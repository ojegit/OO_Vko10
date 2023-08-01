package com.example.oo_vko10;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserListHolder extends RecyclerView.ViewHolder {
    /*
    User card shown in RecyclerView
     */

    ImageView ivUser;
    TextView nimi, sahkoposti, degreeProgram;

    RecyclerView degreeRecyclerView;

    public UserListHolder(@NonNull View itemView) {
        super(itemView);
        ivUser = itemView.findViewById(R.id.ivUser);
        nimi = itemView.findViewById(R.id.txtNimi);
        //etunimi = itemView.findViewById(R.id.txtEtunimi);
        //sukunimi = itemView.findViewById(R.id.txtSukunimi);
        sahkoposti = itemView.findViewById(R.id.txtSahkoposti);
        degreeProgram = itemView.findViewById(R.id.txtDegreeProgram);

        //add the child recycler view

        /*
        Problem is that this userView has to exist outside
         */
        degreeRecyclerView = itemView.findViewById(R.id.rvDegree);
    }
}
