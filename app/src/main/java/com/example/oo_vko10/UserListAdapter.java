package com.example.oo_vko10;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class UserListAdapter extends RecyclerView
        .Adapter<UserListHolder> {
    /*
    RecyclerView that contains the users to be displayed at activity_list_user.xml
    */

    private Context context;
    private ArrayList<User> users = new ArrayList<User>();

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();


    /*
    share the Views between the child and the parent RecyclerViews
    */
    /*
    private RecyclerView.RecycledViewPool viewPool
            = new RecyclerView.RecycledViewPool();
    */

    public UserListAdapter(Context context, ArrayList<User> users) {
        this.context = context;

        //users list here

        //sort list in terms of last name

        System.out.println("Pre-sorting: ");
        for(User u : users) {
            System.out.println(u.getFirstName() +" "+u.getLastName());
        }

        ArrayList<User> newUsers = new ArrayList<>(users);

        //sort alphabetically, in ascending order
        Collections.sort(newUsers, UserStorage.getLastNameComparator("ascending"));


        System.out.println("Post-sorting: ");
        for(User u : newUsers) {
            System.out.println(u.getFirstName() +" "+u.getLastName());
        }

        this.users = newUsers;
    }

    @NonNull
    @Override
    public UserListHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                             int viewType) {

        //see UserListHolder.java
        return new UserListHolder(LayoutInflater.from(context)
                  .inflate(R.layout.list_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserListHolder holder,
                                 int position) {


        holder.nimi.setText(String.valueOf(users.get(position).getFirstName())
                +" "+
                String.valueOf(users.get(position).getLastName()));
        holder.nimi.setTypeface(null, Typeface.BOLD);

        //holder.etunimi.setText(String.valueOf(users.get(position).getFirstName()));
        //holder.sukunimi.setText(String.valueOf(users.get(position).getLastName()));
        holder.sahkoposti.setText(String.valueOf(users.get(position).getEmail()));
        holder.degreeProgram.setText(String.valueOf(users.get(position).getDegreeProgram()));

        //grant permission
        //context.grantUriPermission(context.getPackageName(),
        //        users.get(position).getImageUri(), Intent.FLAG_GRANT_READ_URI_PERMISSION);

        //parse the Uri from string
        holder.ivUser.setImageURI(Uri.parse(users.get(position).getImageUri()));


        /*
        Send degree list from a user to DegreeListAdapter.java
         */
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(holder.degreeRecyclerView.getContext());
        layoutManager.setInitialPrefetchItemCount(users.get(position).getDegree().size());

        DegreeListAdapter degreeListAdapter = new DegreeListAdapter(context, users.get(position).getDegree());

        System.out.println("From user no " +position+ ": " +users.get(position).getDegree().toString());
        holder.degreeRecyclerView.setLayoutManager(layoutManager);
        holder.degreeRecyclerView.setAdapter(degreeListAdapter);
        holder.degreeRecyclerView.setRecycledViewPool(viewPool);

        /*
        Also set up the functionality of the list here:
        -title text is visible only if any data has been entered
         and size of the box parent needs to adjust to the the list size since
         it may or may not have any data in it


         */

        /*
        holder.degreeRecyclerView
                .setLayoutManager(new LinearLayoutManager(context));
        holder.degreeRecyclerView
                .setAdapter(new DegreeListAdapter(context, users.get(position).getDegree()));
        */

        //.setAdapter(new DegreeListAdapter(getApplicationContext(), users.get(position).getDegree());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
