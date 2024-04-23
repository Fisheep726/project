package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.PostListAdapter;
import com.example.myapplication.Chat;
import com.example.myapplication.R;
import com.example.myapplication.activity.WritePostActivity;

import java.util.ArrayList;
import java.util.List;

public class PondFragment extends Fragment{
    Button post;
    View pondView;
    private RecyclerView recyclerView;
    private List<Chat> mPost = new ArrayList<>();
    private PostListAdapter postListAdapter;

    public PondFragment(){
        // Required empty public constructor
    }

    public static PondFragment newInstance(){
        PondFragment pondFragment = new PondFragment();
        Bundle args = new Bundle();
        pondFragment.setArguments(args);
        return pondFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        if(pondView == null){
            pondView = inflater.inflate(R.layout.fragment_pond, container, false);

            post = pondView.findViewById(R.id.ButtonPost);
            post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), WritePostActivity.class);
                    startActivity(intent);
                }
            });
        }
        return pondView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.Recycler_pond);
        mPost.add(new Chat("user 01","21:21","helloWorld !",R.drawable.login));
        mPost.add(new Chat("user 02","09:09","good morning !",R.drawable.login));
        mPost.add(new Chat("user 03","11:27","helloWorld ccc!",R.drawable.login));
        mPost.add(new Chat("user 04","08:09","你好 !",R.drawable.login));
        mPost.add(new Chat("user 05","21:21","这是一个 !",R.drawable.login));
        mPost.add(new Chat("user 06","21:21","测试 !",R.drawable.login));
        mPost.add(new Chat("user 07","21:21","看到 !",R.drawable.login));
        mPost.add(new Chat("user 08","21:21","说明 !",R.drawable.login));
        mPost.add(new Chat("user 09","21:21","可以 !",R.drawable.login));
        mPost.add(new Chat("user 10","21:21","正常 !",R.drawable.login));
        mPost.add(new Chat("user 11","21:21","运行 !",R.drawable.login));

        postListAdapter = new PostListAdapter(mPost);
        recyclerView.setAdapter(postListAdapter);
    }
}
