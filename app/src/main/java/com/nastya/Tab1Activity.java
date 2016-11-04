package com.nastya;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Tab1Activity extends FragmentActivity implements View.OnClickListener{

   private DatabaseReference Database;

  /*private EditText mName;
    private EditText mProfession;
    private TextView mTextViewPersons;

    private Button mSubmitBtn;

*/
    EditText NameText;
    EditText ProfessionText;
    Button SubmitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab1);

        NameText = (EditText) findViewById(R.id.editTextName);
        ProfessionText = (EditText) findViewById(R.id.editTextProfession);
        SubmitBtn = (Button) findViewById(R.id.submitBtn);
        SubmitBtn.setOnClickListener(this);

        //Creating firebase object
        Database = FirebaseDatabase.getInstance().getReference().child("Users");

        Firebase.setAndroidContext(this);

  /*      mName = (EditText) findViewById(R.id.editTextName);
        mProfession = (EditText) findViewById(R.id.editTextProfession);

        mTextViewPersons = (TextView) findViewById(R.id.textViewPersons);

        mSubmitBtn = (Button) findViewById(R.id.submitBtn);

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Getting values to store
                String name = mName.getText().toString().trim();
                String profession = mProfession.getText().toString().trim();

                //Creating person object
                User user = new User();

                //Adding values
                user.setName(name);
                user.setProfession(profession);

                //Storing values to firebase
                mDatabase.child("User").setValue(user);
            }
        }); */

        //Value event listener for realtime data update
       /* mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //Getting the data from snapshot
                    User user = postSnapshot.getValue(User.class);

                    //Adding it to a string
                    String userData = "Name: "+user.getName()+"\nProfession: "+user.getProfession()+"\n\n";

                    //Displaying it on textView
                    mTextViewPersons.setText(userData);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });*/
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        //Getting values to store
        String nameInDb = NameText.getText().toString().trim();
        String professionInDb = ProfessionText.getText().toString().trim();

        //Creating person object
        User user = new User();

        //Adding values
        user.setName(nameInDb);
        user.setProfession(professionInDb);

        //Storing values to firebase
        Database.child("User").setValue(user);

        intent.putExtra("name", NameText.getText().toString());
        intent.putExtra("profession", ProfessionText.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
