package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.bean.Constants;
import com.example.myapplication.bean.User;
import com.example.myapplication.listener.ResultListener;
import com.example.myapplication.utils.HttpUtil;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText editTextName;
    EditText editTextPassword;
    EditText editTextPassword2;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextName = (EditText) this.findViewById(R.id.editTextSetName);
        editTextPassword = (EditText) this.findViewById(R.id.editTextSetPassword);
        editTextPassword2 = (EditText) this.findViewById(R.id.editTextSetPassword2);
        button2= findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextName.getText().toString();
                String password = editTextPassword.getText().toString().trim();
                String password2 = editTextPassword2.getText().toString().trim();
                if(username.length() < 5){
                    Toast.makeText(RegisterActivity.this,"The user name must not be less than 5 characters",Toast.LENGTH_LONG).show();
                    return;
                }
                if (!password2.equals(password)){
                    Toast.makeText(RegisterActivity.this,"The two passwords are inconsistent",Toast.LENGTH_LONG).show();
                    return;
                }
                register(username,password);
            }
        });
    }

    public void register(String name, String password){
        Map<String,String> params = new HashMap<>();
        params.put("username",name);
        params.put("password",password);
        HttpUtil.getInstance().post(Constants.register, params,User.class, new ResultListener<User>() {
            @Override
            public void onSuccess(User data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(RegisterActivity.this,"Register successfully!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });


            }

            @Override
            public void onFailure(String message) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(RegisterActivity.this,message,Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }
}