package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.Chat;

import java.util.ArrayList;
import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.MyHolder> {
    private List<Chat> mChat = new ArrayList<>();
    private ChatListAdapter.OnItemClickListener mListener;
    public ChatListAdapter(List<Chat> list){
        this.mChat = list;
    }

    @NonNull
    @Override
    public ChatListAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatlist_item,null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatListAdapter.MyHolder holder, int position) {
        Chat chat = mChat.get(position);

        holder.chatName.setText(chat.getName());
        holder.chatTime.setText(chat.getTime());
        holder.chatContent.setText(chat.getContent());
        holder.img_chatPerson.setImageResource(chat.getImg());
    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView chatName,chatTime,chatContent;
        ImageView img_chatPerson;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            chatName = itemView.findViewById(R.id.Text_chatName);
            chatTime = itemView.findViewById(R.id.Text_chatTime);
            chatContent = itemView.findViewById(R.id.Text_chatContent);
            img_chatPerson = itemView.findViewById(R.id.img_chatPerson);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            int position = getAdapterPosition();
            if(mListener != null && position != RecyclerView.NO_POSITION){
                Chat chat = mChat.get(position);
                mListener.onItemClick(chat);
//                mListener.onItemClick(position);
            }
        }
    }

    public interface OnItemClickListener{
//        void onItemClick(int position);
        void onItemClick(Chat chat);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
}
