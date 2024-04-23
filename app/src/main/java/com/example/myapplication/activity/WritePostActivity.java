package com.example.myapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class WritePostActivity extends AppCompatActivity {
    EditText postContent;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writepost);
        postContent = this.findViewById(R.id.editTextPost);
        send = this.findViewById(R.id.ButtonPostSend);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPost();
            }
        });
    }

    public void sendPost(){
        Toast.makeText(WritePostActivity.this,"send post successfully !",Toast.LENGTH_LONG).show();
    }
}
