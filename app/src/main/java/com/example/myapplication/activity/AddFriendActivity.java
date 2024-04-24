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
import java.util.List;
import java.util.Map;

public class AddFriendActivity extends AppCompatActivity {
    EditText addFriendId,addFriendName;
    Button ADD;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfriend);
        addFriendId = this.findViewById(R.id.editTextAddFriendId);
        addFriendName = this.findViewById(R.id.editTextAddFriendName);
        ADD = this.findViewById(R.id.ButtonADDFriend);

        ADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String receiverId = addFriendId.getText().toString();
                String receiverName = addFriendName.getText().toString();
                addFriend(Integer.parseInt(receiverId),receiverName);
            }
        });
    }

    public void addFriend(Integer receiverId,String receiverName){
        Map<String,String> params =  new HashMap<>();
        params.put("receiveUserId",receiverId+"");
        params.put("receiveUserName",receiverName);
        params.put("sendUserId",UserManager.getUser().getId() + "");
        params.put("sendUserName",UserManager.getUser().getUsername() + "");
        Toast.makeText(AddFriendActivity.this,UserManager.getUser().getUsername() + "",Toast.LENGTH_LONG).show();
        HttpUtil.getInstance().post(Constants.addFriend, params, User.class, new ResultListener<User>(){

            @Override
            public void onSuccess(User data) {
                runOnUiThread(() -> {
                    UserManager.saveUser(data);
                    Toast.makeText(AddFriendActivity.this,"add friend successfully!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AddFriendActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                });
            }

            @Override
            public void onFailure(String message) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(AddFriendActivity.this,message,Toast.LENGTH_SHORT).show();
                    }

                });
            }

        });
    }
}
