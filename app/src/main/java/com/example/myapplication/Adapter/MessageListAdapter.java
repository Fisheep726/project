package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.bean.Message;
import com.example.myapplication.bean.User;

import java.util.ArrayList;
import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MyHolder> {
    private List<Message> mMessage = new ArrayList<>();
    public MessageListAdapter(List<Message> list){
        this.mMessage = list;
    }
    public void addData(List<Message> messages){
        this.mMessage= messages;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MessageListAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.messagelist_item,null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageListAdapter.MyHolder holder, int position) {
        Message message = mMessage.get(position);
        holder.messageName.setText(message.getFromId() + "");
        holder.messageContent.setText(message.getContent());
        holder.img_messagePerson.setImageResource(R.drawable.login);
    }

    @Override
    public int getItemCount() {
        return mMessage!=null? mMessage.size():0;
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView messageName,messageContent;
        ImageView img_messagePerson;
        public MyHolder(@NonNull View itemView){
            super(itemView);
            messageName = itemView.findViewById(R.id.Text_messageName);
            messageContent = itemView.findViewById(R.id.Text_messageContent);
            img_messagePerson = itemView.findViewById(R.id.img_messagePerson);
        }
    }
}
