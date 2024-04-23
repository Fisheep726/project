package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.bean.Constants;
import com.example.myapplication.bean.User;
import com.example.myapplication.listener.ResultListener;
import com.example.myapplication.utils.HttpUtil;
import com.example.myapplication.utils.UserManager;

import java.util.HashMap;
import java.util.Map;

public class ChangePwdActivity extends AppCompatActivity {
    EditText editTextOldPassword,editTextNewPassword,editTextNewPassword2;
    Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_changepassword);
        btnConfirm = this.findViewById(R.id.personal_button_ChangePwd);
        editTextOldPassword = this.findViewById(R.id.personal_editText_oldPwd);
        editTextNewPassword = this.findViewById(R.id.personal_editText_newPwd);
        editTextNewPassword2 = this.findViewById(R.id.personal_editText_newPwd2);

        User user = UserManager.getUser();

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPassword = editTextOldPassword.getText().toString();
                String newPassword = editTextNewPassword.getText().toString().trim();
                String newPassword2 = editTextNewPassword2.getText().toString().trim();
                if(!user.getPassword().equals(oldPassword)){
                    Toast.makeText(ChangePwdActivity.this,"old password wrong !",Toast.LENGTH_LONG).show();
                    return;
                }
                if (!newPassword2.equals(newPassword)){
                    Toast.makeText(ChangePwdActivity.this,"The two passwords are inconsistent",Toast.LENGTH_LONG).show();
                    return;
                }
                Update(newPassword2);
            }
        });
    }

    public void Update(String password){
        Map<String,String> params = new HashMap<>();
        params.put("id", UserManager.getUser().getId()+"");
        params.put("password",password);
        HttpUtil.getInstance().post(Constants.update, params,User.class, new ResultListener<User>() {
            @Override
            public void onSuccess(User data) {
                runOnUiThread(() -> {
                    UserManager.saveUser(data);
                    Toast.makeText(ChangePwdActivity.this,"Update successfully!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ChangePwdActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                });


            }

            @Override
            public void onFailure(String message) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ChangePwdActivity.this,message,Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }
}
