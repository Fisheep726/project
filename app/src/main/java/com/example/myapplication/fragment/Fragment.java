package com.example.myapplication.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;

public class Fragment extends androidx.fragment.app.Fragment {

    private static final String ARG_TEXT = "param1";
    private String mTextString;
    View rootView;

    public Fragment() {
        // Required empty public constructor
    }
    public static Fragment newInstance(String param1) {
        Fragment fragment = new Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTextString = getArguments().getString(ARG_TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(rootView == null){
        rootView = inflater.inflate(R.layout.fragment, container, false);
        }
        initView();
//        initRecyclerView();
//        initData();
        return rootView;
    }

    public void initView(){
        TextView textView = rootView.findViewById(R.id.id_text_fragment1);
        textView.setText(mTextString);
    }

//    public void initRecyclerView(){
//        recyclerView = (RecyclerView)rootView.findViewById(R.id.Recycler);
//        initData();
//        recyclerViewAdapter = new RecyclerViewAdapter(getActivity(),chatList);
//        recyclerView.setAdapter(recyclerViewAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
//        recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
//            @Override
//            public void OnItemClick(View view, chat chats) {
//                Toast.makeText(getActivity(),"none",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

//    public void initData(){
//        for(int i = 0; i < 20; i++){
//            chat chats = new chat();
//            chats.setName("name " + i);
//            chats.setTime("time " + i);
//            chats.setContent("content " + i);
//            chatList.add(chats);
//        }
//    }
}