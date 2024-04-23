package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.Chat;

import java.util.ArrayList;
import java.util.List;
public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.MyHolder>{
    private List<Chat> mPost = new ArrayList<>();
    public PostListAdapter(List<Chat> list){
        this.mPost = list;
    }
    @NonNull
    @Override
    public PostListAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.postlist_item,null);
        return new MyHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull PostListAdapter.MyHolder holder, int position) {
        Chat post = mPost.get(position);

        holder.postName.setText(post.getName());
        holder.postTime.setText(post.getTime());
        holder.postContent.setText(post.getContent());
        holder.img_postPerson.setImageResource(post.getImg());
    }

    @Override
    public int getItemCount() {
        return mPost.size();
    }
    class MyHolder extends RecyclerView.ViewHolder{
        TextView postName,postTime,postContent;
        ImageView img_postPerson;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            postName = itemView.findViewById(R.id.Text_postName);
            postTime = itemView.findViewById(R.id.Text_postTime);
            postContent = itemView.findViewById(R.id.Text_postContent);
            img_postPerson = itemView.findViewById(R.id.img_postPerson);
        }
    }
}
