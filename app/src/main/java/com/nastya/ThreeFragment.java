package com.nastya;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class ThreeFragment extends Fragment implements View.OnClickListener{

   /* private EditText mName;
    private Button mSubmitBtn; */
    TextView NameView;
    TextView ProfessionView;
    Button AddProfileBtn;

    public ThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        NameView = (TextView) view.findViewById(R.id.nameTextView);
        ProfessionView = (TextView) view.findViewById(R.id.professionTextView);
        AddProfileBtn = (Button) view.findViewById(R.id.addProfileBtn);
        AddProfileBtn.setOnClickListener(this);

        /*mName = (EditText) view.findViewById(R.id.editTextName);
        mSubmitBtn = (Button) view.findViewById(R.id.submitBtn);

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Getting values to store
                String name = mName.getText().toString().trim();


                //Creating person object
                User user = new User();

                //Adding values
                user.setName(name);

                //startActivity that works well inside Fragments
                //startActivity(new Intent(getActivity(), Tab1Activity.class));

                startActivityForResult();
            }
        });*/


        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), Tab1Activity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {return;}
        String name = data.getStringExtra("name");
        String profession = data.getStringExtra("profession");

        NameView.setText("Name: " + name);
        ProfessionView.setText("Profession: " + profession);
    }
}
