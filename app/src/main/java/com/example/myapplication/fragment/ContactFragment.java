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
import com.example.myapplication.R;
import com.example.myapplication.activity.AddFriendActivity;
import com.example.myapplication.bean.Constants;
import com.example.myapplication.bean.User;
import com.example.myapplication.listener.ResultListener;
import com.example.myapplication.utils.HttpUtil;

import java.util.ArrayList;
import java.util.List;

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
                    }
                });
            }

            @Override
            public void onSuccess(List<User> data) {
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        contactListAdapter.addData(data);
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
