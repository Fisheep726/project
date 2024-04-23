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

import com.example.myapplication.Adapter.ContactListAdapter;
import com.example.myapplication.Adapter.PostListAdapter;
import com.example.myapplication.Chat;
import com.example.myapplication.R;
import com.example.myapplication.activity.AddFriendActivity;
import com.example.myapplication.activity.LoginActivity;
import com.example.myapplication.activity.MainActivity;
import com.example.myapplication.activity.WritePostActivity;
import com.example.myapplication.bean.Constants;
import com.example.myapplication.bean.User;
import com.example.myapplication.listener.ResultListener;
import com.example.myapplication.utils.HttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactFragment extends Fragment{
    Button add;
    View contactView;
    private RecyclerView recyclerView;
    private List<User> mContact = new ArrayList<>();
    private ContactListAdapter contactListAdapter;

    public ContactFragment(){
        // Required empty public constructor
    }

    public static ContactFragment newInstance(){
        ContactFragment contactFragment = new ContactFragment();
        Bundle args = new Bundle();
        contactFragment.setArguments(args);
        return contactFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        if(contactView == null){
            contactView = inflater.inflate(R.layout.fragment_contact,container,false);
            add = contactView.findViewById(R.id.ButtonAddFriend);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), AddFriendActivity.class);
                    startActivity(intent);
                }
            });
        }
        return contactView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.Recycler_contact);
//        mContact.add(new Chat("user 01","21:21","helloWorld !",R.drawable.login));
//        mContact.add(new Chat("user 02","09:09","good morning !",R.drawable.login));
//        mContact.add(new Chat("user 03","11:27","helloWorld ccc!",R.drawable.login));
//        mContact.add(new Chat("user 04","08:09","你好 !",R.drawable.login));
//        mContact.add(new Chat("user 05","21:21","这是一个 !",R.drawable.login));
//        mContact.add(new Chat("user 06","21:21","测试 !",R.drawable.login));
//        mContact.add(new Chat("user 07","21:21","看到 !",R.drawable.login));
//        mContact.add(new Chat("user 08","21:21","说明 !",R.drawable.login));
//        mContact.add(new Chat("user 09","21:21","可以 !",R.drawable.login));
//        mContact.add(new Chat("user 10","21:21","正常 !",R.drawable.login));
//        mContact.add(new Chat("user 11","21:21","运行 !",R.drawable.login));

//        String id = initContactList();
//        mContact.add(new Chat(""+id,null,null,R.drawable.login));


        contactListAdapter = new ContactListAdapter(mContact);
        recyclerView.setAdapter(contactListAdapter);
        initContactList();
    }

    public void initContactList(){


        HttpUtil.getInstance().get(Constants.contact, null, User.class, new ResultListener<User>() {
            @Override
            public void onSuccess(User data) {
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(getActivity(),"id is :" + id,Toast.LENGTH_SHORT).show();
//                        System.out.println(id);
//                        Intent intent = new Intent(getActivity(), MainActivity.class);
//                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onSuccess(List<User> data) {
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),"id is :",Toast.LENGTH_SHORT).show();
//                        Toast.makeText(getActivity(),"获取数据成功" ,Toast.LENGTH_SHORT).show();
                        contactListAdapter.addData(data);
                        //
                    }
                });

            }

            @Override
            public void onFailure(String message) {
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }
}
