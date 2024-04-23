package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.bean.Constants;
import com.example.myapplication.bean.User;
import com.example.myapplication.listener.ResultListener;
import com.example.myapplication.utils.HttpUtil;
import com.example.myapplication.utils.UserManager;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText editTextName, editTextPassword;
    TextView textViewSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = (Button) this.findViewById(R.id.buttonLogin);
        editTextName = (EditText) this.findViewById(R.id.editTextName);
        editTextPassword = (EditText) this.findViewById(R.id.editTextPassword);
        textViewSignup = (TextView) this.findViewById(R.id.textViewSignUp);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
        textViewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Signup();
            }
        });
    }

    private void Signup(){
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
//        intent.setClass(this,RegisterActivity.class);
        startActivity(intent);
    }
    private void Login(){
        String name = editTextName.getText().toString();
        String password = editTextPassword.getText().toString();
        
        login(name,password);
//        if(name.equals("admin") && password.equals("123")){
//            Toast.makeText(this,"Login successfully!",Toast.LENGTH_LONG).show();
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            startActivity(intent);
//        }
//        else{
//
//        }
    }

    private void login(String name, String password) {
        Map<String,String> params = new HashMap<>();
        params.put("username",name);
        params.put("password",password);
        HttpUtil.getInstance().post(Constants.login, params, User.class, new ResultListener<User>() {
            @Override
            public void onSuccess(User data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        saveUser(data);
                        Toast.makeText(LoginActivity.this,"Login successfully!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onFailure(String message) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this,message,Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }

    private void saveUser(User data) {

        UserManager.saveUser(data);
    }
}