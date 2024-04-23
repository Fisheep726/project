package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.ChatListAdapter;
import com.example.myapplication.Adapter.PostListAdapter;
import com.example.myapplication.Chat;
import com.example.myapplication.R;
import com.example.myapplication.activity.ChatActivity;
import com.example.myapplication.activity.WritePostActivity;

import java.util.ArrayList;
import java.util.List;

public class BubbleFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Chat> mChat = new ArrayList<>();
    private ChatListAdapter chatListAdapter;


    public BubbleFragment(){
        // Required empty public constructor
    }

    public static BubbleFragment newInstance(){
        BubbleFragment bubbleFragment = new BubbleFragment();
        Bundle args = new Bundle();
        bubbleFragment.setArguments(args);
        return bubbleFragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_bubble, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.Recycler_bubble);
        mChat.add(new Chat("user 01","21:21","helloWorld !",R.drawable.login));
        mChat.add(new Chat("user 02","09:09","good morning !",R.drawable.login));
        mChat.add(new Chat("user 03","11:27","helloWorld ccc!",R.drawable.login));
        mChat.add(new Chat("user 04","08:09","你好 !",R.drawable.login));
        mChat.add(new Chat("user 05","21:21","这是一个 !",R.drawable.login));
        mChat.add(new Chat("user 06","21:21","测试 !",R.drawable.login));
        mChat.add(new Chat("user 07","21:21","看到 !",R.drawable.login));
        mChat.add(new Chat("user 08","21:21","说明 !",R.drawable.login));
        mChat.add(new Chat("user 09","21:21","可以 !",R.drawable.login));
        mChat.add(new Chat("user 10","21:21","正常 !",R.drawable.login));
        mChat.add(new Chat("user 11","21:21","运行 !",R.drawable.login));

        chatListAdapter = new ChatListAdapter(mChat);
        chatListAdapter.setOnItemClickListener(new ChatListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Chat chat) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra("name",chat.getName());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(chatListAdapter);
    }
}
