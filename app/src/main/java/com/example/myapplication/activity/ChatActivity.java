package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class ChatActivity extends AppCompatActivity {
    TextView TextChatName,TextMessage;
    Button sendMessage;
    String Name,Message;
    EditText editTextMessage;
    User user = UserManager.getUser();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        TextChatName = this.findViewById(R.id.chatName);
        TextMessage = this.findViewById(R.id.Text_message);
        sendMessage = this.findViewById(R.id.ButtonSendMessage);
        editTextMessage = this.findViewById(R.id.editTextMessage);
        Message = editTextMessage.getText().toString();
        Intent intent = getIntent();
        Name = intent.getStringExtra("name");
        TextChatName.setText("" + Name);

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(Message);
            }
        });
    }

    public void sendMessage(String message){
        Toast.makeText(ChatActivity.this,"" + message,Toast.LENGTH_SHORT).show();

        Map<String,String> params = new HashMap<>();
        params.put("message",message);
        params.put("toId","200");
        params.put("fromId",UserManager.getUser().getId()+"");
        HttpUtil.getInstance().post(Constants.send, params, User.class, new ResultListener<User>() {
            @Override
            public void onSuccess(User data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ChatActivity.this,"Login successfully!",Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onFailure(String Message) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ChatActivity.this,Message,Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

}
