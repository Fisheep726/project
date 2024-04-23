package com.example.myapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class AddFriendActivity extends AppCompatActivity {
    EditText addFriend;
    Button ADD;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfriend);
        addFriend = this.findViewById(R.id.editTextAddFriend);
        ADD = this.findViewById(R.id.ButtonADDFriend);

        ADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFriend();
            }
        });
    }

    public void addFriend(){
        Toast.makeText(AddFriendActivity.this,"add friend successfully !",Toast.LENGTH_LONG).show();
    }
}
