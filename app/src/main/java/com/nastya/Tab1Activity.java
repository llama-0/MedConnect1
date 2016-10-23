package com.nastya;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Tab1Activity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;


    private boolean mSubmit;

    private EditText mName;
    private EditText mProfession;

    private Button mSubmitBtn;

    private CheckBox mChk1, mChk2, mChk3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab1);
        mSubmit = false;

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mName = (EditText) findViewById(R.id.name);
        mProfession = (EditText) findViewById(R.id.profession);
        mSubmitBtn = (Button) findViewById(R.id.submitBtn);
        mName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_NEXT) {
                    mName.requestFocus();
                    return true;
                }
                return false;
            }
        });
        mProfession.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_NULL) {
                    submit();
                    return true;
                }
                return false;
            }
        });
        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
                startActivity(new Intent(Tab1Activity.this, TabHolderActivity.class));
            }
        });
    }

    public void submit() {
        if (mSubmit) {
            return;
        }

        mName.setError(null);
        mProfession.setError(null);

        String name = mName.getText().toString();
        String profession = mProfession.getText().toString();

        boolean cancelSubmit = false;
        View focusView = null;

        if (TextUtils.isEmpty(name)) {
            mName.setError(getString(R.string.empty_data));
            focusView = mName;
            cancelSubmit = true;
        } else if (TextUtils.isEmpty(profession)) {
            mProfession.setError(getString(R.string.empty_data));
            focusView = mProfession;
            cancelSubmit = true;
        }

        if (cancelSubmit) {
            // error in login
            focusView.requestFocus();
        } else {
            mSubmit = true;
            User user = new User(name, profession);
            mDatabase.child("users").setValue(user);
        }
    }


}
