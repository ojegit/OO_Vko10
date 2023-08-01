package com.example.oo_vko10;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import java.util.ArrayList;

public class AddUsersActivity extends AppCompatActivity {

    private Context context;

    // One Button
    Button btnImgChooser;
    ImageView ivUserPreview;
    Uri uri;

    ActivityResultLauncher<PickVisualMediaRequest> pickMedia;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        //set context
        context = AddUsersActivity.this;

        //default image: @drawable/portrait_placeholder


        Resources resources = context.getResources();
        /*
        Not working 1:
        int defaultImg = R.drawable.portrait_placeholder;
        uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + resources.getResourcePackageName(defaultImg) +
                '/' + resources.getResourceTypeName(defaultImg) +
                '/' + resources.getResourceEntryName(defaultImg));

        Not working 2:
        uri = Uri.parse("android.resource://"+context.getPackageName()+"/drawable/portrait_placeholder");

        Not working 3:
        uri = Uri.parse("R.drawable.portrait_placeholder");

        */
        uri = Uri.parse("android.resource://"+context.getPackageName()+"/drawable/portrait_placeholder");
        System.out.println("Default img uri: "+uri);

        //Toast.makeText(context, String.valueOf(uri), Toast.LENGTH_LONG);


        pickMedia =
        registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri_ -> {
            // Callback is invoked after the user selects a media item or closes the
            // photo picker.
            if (uri_ != null) {
                uri = uri_;
                Log.d("PhotoPicker", "Selected URI: " + uri_);

                //load to preview
                ivUserPreview = (ImageView)findViewById(R.id.ivUserPreview);
                ivUserPreview.setImageURI(uri);


            } else {
                //default image from drawable;
                Log.d("PhotoPicker", "No media selected");
            }
        });

    }

    public void onClickReadImage(View view) {
        pickMedia.launch(new PickVisualMediaRequest.Builder()
                .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                .build());

    }


    public void onClickAddUser(View view) {

        RadioGroup rgDegreeProgram = findViewById(R.id.rgDegreeProgram);
        EditText etunimi = findViewById(R.id.editTextEtunimi);
        EditText sukunimi = findViewById(R.id.editTextSukunimi);
        EditText sahkoposti = findViewById(R.id.editTextSahkoposti);
        RadioGroup rgDegree = findViewById(R.id.rgDegree);
        String degreeProgram = "";

        //Check state of the radiobutton
        switch (rgDegreeProgram.getCheckedRadioButtonId()) {
            case R.id.rbTuta:
                degreeProgram = "Tuotantotalous";
                break;
            case R.id.rbTite:
                degreeProgram = "Tietotekniikka";
                break;
            case R.id.rbLT:
                degreeProgram = "Laskennallinen tekniikka";
                break;
            case R.id.rbSate:
                degreeProgram = "Sähkötekniikka";
                break;
        }

        //Check checkboxes
        ArrayList<String> degree = new ArrayList<>();
        if (rgDegree.getChildCount() > 0) {
            for (int i = 0; i < rgDegree.getChildCount(); i++) {
                CheckBox cb = (CheckBox) rgDegree.getChildAt(i);
                if (cb.isChecked()) {
                    System.out.println("User " + String.valueOf(etunimi.getText())
                            + " " + String.valueOf(sukunimi.getText()) +
                            " added a degree '" + String.valueOf(cb.getText()) + "'.");
                    degree.add(String.valueOf(cb.getText()));
                }
            }
        }

        //Add to ArrayList
        User user = new User(String.valueOf(etunimi.getText()),
                String.valueOf(sukunimi.getText()),
                String.valueOf(sahkoposti.getText()),
                degreeProgram,
                degree);

        //Permission here!?
        getContentResolver().takePersistableUriPermission(uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION);

        //convert from Uri to string before adding!
        user.setImageUri(uri.toString());

        UserStorage.getInstance().addUser(user);
        System.out.println("User added!");


        //Save list to file
        UserStorage.getInstance().saveUsers(context);
    }


}