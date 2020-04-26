package com.example.db2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ParentEditStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_edit_student);

        Intent intent = getIntent();
        final String email = intent.getStringExtra("email");
        final String password = intent.getStringExtra("password");
        final String phone = intent.getStringExtra("phone");
        final String name = intent.getStringExtra("name");
        final String sName = intent.getStringExtra("sName");
        final String sEmail = intent.getStringExtra("sEmail");
        final String user = intent.getStringExtra("user");

        Button editEmailButton = (Button) findViewById(R.id.editEmailButton);
        Button editPhoneButton = (Button) findViewById(R.id.editPhoneButton);
        Button editPasswordButton = (Button) findViewById(R.id.editPasswordButton);

        editEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent editChild = new Intent(ParentEditStudent.this, EditEmailParentStudent.class);
                editChild.putExtra("email", email);
                editChild.putExtra("password", password);
                editChild.putExtra("phone", phone);
                editChild.putExtra("name", name);
                editChild.putExtra("sEmail", sEmail);
                editChild.putExtra("sName", sName);
                editChild.putExtra("user", user);
                ParentEditStudent.this.startActivity(editChild);
            }
        });
        editPhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent editChild = new Intent(ParentEditStudent.this, EditPhoneParentStudent.class);
                editChild.putExtra("email", email);
                editChild.putExtra("password", password);
                editChild.putExtra("phone", phone);
                editChild.putExtra("name", name);
                editChild.putExtra("sEmail", sEmail);
                editChild.putExtra("sName", sName);
                editChild.putExtra("user", user);
                ParentEditStudent.this.startActivity(editChild);
            }
        });
        editPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent editChild = new Intent(ParentEditStudent.this, EditPasswordParentStudent.class);
                editChild.putExtra("email", email);
                editChild.putExtra("password", password);
                editChild.putExtra("phone", phone);
                editChild.putExtra("name", name);
                editChild.putExtra("sEmail", sEmail);
                editChild.putExtra("sName", sName);
                editChild.putExtra("user", user);
                ParentEditStudent.this.startActivity(editChild);
            }
        });
    }
}
