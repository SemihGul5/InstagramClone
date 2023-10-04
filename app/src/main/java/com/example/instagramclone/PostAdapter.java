package com.example.instagramclone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagramclone.databinding.RecyclerRowBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {
    ArrayList<Post> postArrayList;
    Context context;

    public PostAdapter(ArrayList<Post> postArrayList,Context context) {
        this.postArrayList = postArrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding binding= RecyclerRowBinding.inflate(LayoutInflater.from(context),parent,false);

        return new PostHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        holder.binding.recyclerViewEmailText.setText(postArrayList.get(position).getEmail());
        holder.binding.recyclerViewCommentText.setText(postArrayList.get(position).getComment());
        //picasso github kütüphanesinden yararlanıyoruz resmi indirmek için
        Picasso.get().load(postArrayList.get(position).getDownloadUrl()).resize(300,300)
                .into(holder.binding.recyclerViewImage);

    }

    @Override
    public int getItemCount() {
        return postArrayList.size();
    }

    public class PostHolder extends RecyclerView.ViewHolder{
        private RecyclerRowBinding binding;

        public PostHolder(RecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
