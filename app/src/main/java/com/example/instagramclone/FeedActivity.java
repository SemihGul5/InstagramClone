package com.example.instagramclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.instagramclone.databinding.ActivityFeedBinding;
import com.google.firebase.auth.FirebaseAuth;

public class FeedActivity extends AppCompatActivity {
    FirebaseAuth auth;
    private ActivityFeedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityFeedBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        auth=FirebaseAuth.getInstance();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {// option menü bağlama işlemi
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.options_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {//option menü hangi elemanı seçildiği işlemleri
        if (item.getItemId()==R.id.addPost){
            //post ekranına gidilecek
            Intent intent=  new Intent(FeedActivity.this, PostActivity.class);
            startActivity(intent);

        } else if (item.getItemId()==R.id.singOut) {
            //çıkış yapılacak
            auth.signOut();
            Intent intent= new Intent(FeedActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

            Toast.makeText(FeedActivity.this,"Çıkış Başarılı",Toast.LENGTH_SHORT).show();

        }

        return super.onOptionsItemSelected(item);
    }
}