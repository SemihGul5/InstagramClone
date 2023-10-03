package com.example.instagramclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.instagramclone.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // kullanıcı eğer daha önceden giriş yapmışsa direkt giriş yapıyor, email ve şifre sormuyor
        FirebaseUser user= mAuth.getCurrentUser();
        if (user!=null){
            reload();
        }


    }

    public void reload() {
        Intent intent= new Intent(MainActivity.this,FeedActivity.class);
        startActivity(intent);
        finish();
    }

    public void loginClicked(View view){
        String eMail= binding.eMailText.getText().toString();
        String password=binding.passwordText.getText().toString();
        mAuth.signInWithEmailAndPassword(eMail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    //giriş başarılı
                   reload();
                }
                else{
                    //giriş başarısız
                    Toast.makeText(MainActivity.this,"Giriş başarısız !",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void signUpClicked(View view){
        String eMail= binding.eMailText.getText().toString();
        String password=binding.passwordText.getText().toString();

        if (eMail.equals("")||password.equals("")){
            Toast.makeText(MainActivity.this,"Tüm alanları doldurun",Toast.LENGTH_SHORT).show();
        }
        else{
            mAuth.createUserWithEmailAndPassword(eMail,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    //kayıt başarılıysa
                    reload();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    //kayıt başarısızsa
                    Toast.makeText(MainActivity.this,e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }

    }







}

