package com.example.db2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button adSignInButton = (Button) findViewById(R.id.adSignInButton);
        Button stSignInButton = (Button) findViewById(R.id.stSignInButton);
        Button paSignInButton = (Button) findViewById(R.id.paSignInButton);

        Button stRegButton = (Button) findViewById(R.id.stRegisterButton);
        Button paRegButton = (Button) findViewById(R.id.paRegisterButton);

        adSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent adSignInIntent = new Intent(MainActivity.this, SignInAdmin.class);
                MainActivity.this.startActivity(adSignInIntent);
            }
        });
        stSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent stSignInIntent = new Intent(MainActivity.this, SignInStudent.class);
                MainActivity.this.startActivity(stSignInIntent);
            }
        });
        paSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent paSignInIntent = new Intent(MainActivity.this, SignInParent.class);
                MainActivity.this.startActivity(paSignInIntent);
            }
        });

        stRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent stRegisterIntent = new Intent(MainActivity.this, RegisterStudentAct.class);
                MainActivity.this.startActivity(stRegisterIntent);
            }
        });
        paRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent paRegisterIntent = new Intent(MainActivity.this, RegisterParentAct.class);
                MainActivity.this.startActivity(paRegisterIntent);
            }
        });
    }
}
