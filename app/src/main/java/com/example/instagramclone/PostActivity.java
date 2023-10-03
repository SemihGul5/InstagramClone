package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.instagramclone.databinding.ActivityPostBinding;

public class PostActivity extends AppCompatActivity {
    private ActivityPostBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPostBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
    }

    private void postButtonClicked(View view){

    }
    private void selectImage(View view){

    }
}