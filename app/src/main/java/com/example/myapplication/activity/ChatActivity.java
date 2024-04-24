package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.MessageListAdapter;
import com.example.myapplication.R;
import com.example.myapplication.bean.Constants;
import com.example.myapplication.bean.Message;
import com.example.myapplication.bean.User;
import com.example.myapplication.listener.ResultListener;
import com.example.myapplication.utils.HttpUtil;
import com.example.myapplication.utils.UserManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {
    TextView TextChatName;
    Button sendMessage;
    String Message,sessionId;
    Integer fromId,toId;
    EditText editTextMessage;
    private RecyclerView recyclerView;
    private List<Message> mMessage = new ArrayList<>();
    private MessageListAdapter messageListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        TextChatName = this.findViewById(R.id.chatName);
        recyclerView = this.findViewById(R.id.Recycler_message);
        messageListAdapter = new MessageListAdapter(mMessage);
        sendMessage = this.findViewById(R.id.ButtonSendMessage);
        editTextMessage = this.findViewById(R.id.editTextMessage);
        Intent intent = getIntent();
        fromId = intent.getIntExtra("fromId", 0);
        toId = intent.getIntExtra("toId", 0);
        sessionId = intent.getStringExtra("sessionId");
        TextChatName.setText("" + fromId);

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message = editTextMessage.getText().toString();
                sendMessage(Message);
            }
        });

        recyclerView.setAdapter(messageListAdapter);
        receiveMessage(sessionId);
    }

    public void sendMessage(String message) {
        Toast.makeText(ChatActivity.this, "" + message, Toast.LENGTH_SHORT).show();

        Map<String, String> params = new HashMap<>();
        params.put("content", message);
        params.put("toId", fromId.toString());
        params.put("fromId", UserManager.getUser().getId() + "");
        HttpUtil.getInstance().post(Constants.send, params, User.class, new ResultListener<User>() {
            @Override
            public void onSuccess(User data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ChatActivity.this, "Login successfully!", Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onFailure(String Message) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ChatActivity.this, Message, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }


    public void receiveMessage(String sessionId){
        Map<String,String> params = new HashMap<>();
        params.put("sessionId", sessionId + "");
        HttpUtil.getInstance().get(Constants.receiveMessage, params, Message.class, new ResultListener<Message>() {

            @Override
            public void onSuccess(List<Message> data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        messageListAdapter.addData(data);
                    }
                });
            }

            @Override
            public void onSuccess(Message data) {

            }

            @Override
            public void onFailure(String message) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ChatActivity.this,message,Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
