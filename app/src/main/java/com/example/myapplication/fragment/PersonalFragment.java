package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.myapplication.R;
import com.example.myapplication.activity.ChangeDataActivity;
import com.example.myapplication.activity.ChangePwdActivity;
import com.example.myapplication.bean.User;

import java.util.ArrayList;
import java.util.List;

public class PersonalFragment extends Fragment{
    private List<User> users = new ArrayList<>();
    private LinearLayout llPwd,llData;

    View personalView;

    public PersonalFragment(){
        // Required empty public constructor
    }

    public static PersonalFragment newInstance(){
        PersonalFragment personalFragment = new PersonalFragment();
        Bundle args = new Bundle();
        personalFragment.setArguments(args);
        return personalFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        if(personalView == null){
            personalView = inflater.inflate(R.layout.fragment_personal, container, false);

            llPwd = personalView.findViewById(R.id.personal_tab_changePassword);
            llData = personalView.findViewById(R.id.personal_tab_changeData);
            llPwd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), ChangePwdActivity.class);
                    startActivity(intent);
                }
            });

            llData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), ChangeDataActivity.class);
                    startActivity(intent);
                }
            });
        }
        return personalView;
    }

//    @Override
//    public void onClick(View v) {
//
//    }

    public void initTabView(){

    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//    }

}
