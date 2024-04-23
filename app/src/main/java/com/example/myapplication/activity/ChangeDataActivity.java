package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
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

public class ChangeDataActivity extends AppCompatActivity {
    EditText editTextNickname,editTextPhone,editTextBirthday,editTextEmail;
    Button btnConfirm;
    RadioGroup radioGroup;
    int gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_changedata);
        btnConfirm = (Button) this.findViewById(R.id.personal_button_ChangeData);
        editTextNickname = (EditText) this.findViewById(R.id.personal_editText_nickname);
        editTextPhone = (EditText) this.findViewById(R.id.personal_editText_phone);
        editTextBirthday = (EditText) this.findViewById(R.id.personal_editText_birthday);
        editTextEmail = (EditText) this.findViewById(R.id.personal_editText_email);
        radioGroup = (RadioGroup) this.findViewById(R.id.personal_radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.personal_radiobutton_male){gender = 1;};
                if(checkedId == R.id.personal_radiobutton_female){gender = 2;};
            }
        });

        User user = UserManager.getUser();
        if (user!=null){
            editTextNickname.setText(user.getNickname());
            editTextPhone.setText(user.getMobile());
            editTextBirthday.setText(user.getBirthday());
            editTextEmail.setText(user.getEmail());
            if(user.getGender() == 1){
                radioGroup.check(R.id.personal_radiobutton_male);
            }else if(user.getGender() == 2){
                radioGroup.check(R.id.personal_radiobutton_female);
            }
        }

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nickname =  editTextNickname.getText().toString();
                String phone = editTextPhone.getText().toString();
                String birthday = editTextBirthday.getText().toString();
                String email = editTextEmail.getText().toString();
                Update(nickname,phone,birthday,email,gender);
            }
        });
    }

//    public void ChangeData(){
//        String gender;
//        user.setNickname(editTextNickname.getText().toString());
//        user.setMobile(editTextPhone.getText().toString());
//        //birthday and email need add
//
//        if(radioGroup.getId() == R.id.personal_radiobutton_male){
//            Toast.makeText(ChangeDataActivity.this,"male",Toast.LENGTH_LONG).show();
//            System.out.println("male");
//        }else if(radioGroup.getId() == R.id.personal_radiobutton_female){
//            Toast.makeText(ChangeDataActivity.this,"female",Toast.LENGTH_LONG).show();
//            System.out.println("female");
//        }
//    }

    public void Update(String nickname, String phone, String birthday, String email,int gender){
        Map<String,String> params = new HashMap<>();
        params.put("id", UserManager.getUser().getId()+"");
        params.put("nickname",nickname);
        params.put("mobile",phone);
        params.put("birthday",birthday);
        params.put("email",email);
        params.put("gender",gender+"");
        HttpUtil.getInstance().post(Constants.update, params,User.class, new ResultListener<User>() {
            @Override
            public void onSuccess(User data) {
                runOnUiThread(() -> {
                    UserManager.saveUser(data);
                    Toast.makeText(ChangeDataActivity.this,"Update successfully!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ChangeDataActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                });


            }

            @Override
            public void onFailure(String message) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ChangeDataActivity.this,message,Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }
}
