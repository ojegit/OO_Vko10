package com.example.oo_vko10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set context
        context = MainActivity.this;

        //UserStorage singular
        UserStorage us = UserStorage.getInstance();

        //add some examples
        /*
        User user = new User("ABCD","abcd",
                "ABCD.abcd@qwerty.com", "Tuotantotalous");
         */

        //load users when the program starts
        us.loadUsers(context);


        //get default image uri from drawable
        /*
        Uri uri = (new Uri.Builder())
                .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                .authority(getResources().getResourcePackageName(R.drawable.portrait_placeholder))
                .appendPath(getResources().getResourceTypeName(R.drawable.portrait_placeholder))
                .appendPath(getResources().getResourceEntryName(R.drawable.portrait_placeholder))
                .build();
        user.setImageUri(uri);
        us.addUser(user);
        */
    }


    public void switchToAddUser(View view) {
        Intent intent = new Intent(this, AddUsersActivity.class);
        startActivity(intent);
    }

    public void switchToUserList(View view) {
        Intent intent = new Intent(this, ListUsersActivity.class);
        startActivity(intent);
    }


}