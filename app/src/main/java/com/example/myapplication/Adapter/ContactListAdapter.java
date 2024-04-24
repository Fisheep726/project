package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.bean.User;

import java.util.ArrayList;
import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.MyHolder> {
    private List<User> mContact = new ArrayList<>();
    public ContactListAdapter(List<User> list){this.mContact = list;}

    public void addData(List<User> users){
        this.mContact= users;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ContactListAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contactlist_item,null);
        return new MyHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ContactListAdapter.MyHolder holder, int position){
        User contact = mContact.get(position);

        holder.contactName.setText(contact.getUsername());
        holder.img_contactPerson.setImageResource(R.drawable.login);
    }

    @Override
    public int getItemCount(){return mContact!=null? mContact.size():0;}
    class MyHolder extends RecyclerView.ViewHolder{
        TextView contactName;
        ImageView img_contactPerson;
        public MyHolder(@NonNull View itemView){
            super(itemView);
            contactName = itemView.findViewById(R.id.Text_contactName);
            img_contactPerson = itemView.findViewById(R.id.img_contactPerson);
        }
    }
}
