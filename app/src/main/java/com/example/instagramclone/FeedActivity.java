package com.example.instagramclone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.instagramclone.databinding.ActivityFeedBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class FeedActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    private ActivityFeedBinding binding;
    ArrayList<Post> postArrayList;
    PostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityFeedBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        postArrayList=new ArrayList<>();

        auth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        getData();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter= new PostAdapter(postArrayList,FeedActivity.this);
        binding.recyclerView.setAdapter(adapter);
    }

    private void getData() {
        firestore.collection("Post").orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error!=null){
                    Toast.makeText(FeedActivity.this,error.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                }
                if (value!=null){
                    for (DocumentSnapshot documentSnapshot :value.getDocuments()){
                        Map<String,Object> data=documentSnapshot.getData();

                        String email= (String) data.get("userEmail");
                        String comment= (String) data.get("comment");
                        String downloadUrl= (String) data.get("downloadUrl");

                        Post post= new Post(email,comment,downloadUrl);
                        postArrayList.add(post);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });

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