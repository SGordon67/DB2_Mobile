package com.example.db2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PageStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_student);

        TextView tvName = (TextView) findViewById(R.id.tvName);
        TextView tvGrade = (TextView) findViewById(R.id.tvGrade);
        TextView tvPaEmail = (TextView) findViewById(R.id.tvPaEmail);
        TextView tvEmail = (TextView) findViewById(R.id.tvEmail);
        TextView tvPassword = (TextView) findViewById(R.id.tvPassword);
        TextView tvPhone = (TextView) findViewById(R.id.tvPhone);

        Button editEmailButton = (Button) findViewById(R.id.editEmailButton);
        Button editPhoneButton = (Button) findViewById(R.id.editPhoneButton);
        Button editPasswordButton = (Button) findViewById(R.id.editPasswordButton);

        Button joinLeaveButton = (Button) findViewById(R.id.joinLeaveButton);
        Button viewMeetingsButton = (Button) findViewById(R.id.viewMeetingsButton);
        Button studyMaterialsButton = (Button) findViewById(R.id.studyMaterialsButton);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        final int grade = intent.getIntExtra("grade", -1);
        final String paEmail = intent.getStringExtra("paEmail");
        final String email = intent.getStringExtra("email");
        final String password = intent.getStringExtra("password");
        final String phone = intent.getStringExtra("phone");
        final String user = "student";

        editEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent editEmailStudentIntent = new Intent(PageStudent.this, EditEmailStudent.class);
                editEmailStudentIntent.putExtra("email", email);
                editEmailStudentIntent.putExtra("password", password);
                editEmailStudentIntent.putExtra("phone", phone);
                editEmailStudentIntent.putExtra("name", name);
                editEmailStudentIntent.putExtra("grade", grade);
                editEmailStudentIntent.putExtra("paEmail", paEmail);
                editEmailStudentIntent.putExtra("user", user);
                PageStudent.this.startActivity(editEmailStudentIntent);
            }
        });
        editPhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent editPhoneStudentIntent = new Intent(PageStudent.this, EditPhoneStudent.class);
                editPhoneStudentIntent.putExtra("email", email);
                editPhoneStudentIntent.putExtra("password", password);
                editPhoneStudentIntent.putExtra("phone", phone);
                editPhoneStudentIntent.putExtra("name", name);
                editPhoneStudentIntent.putExtra("grade", grade);
                editPhoneStudentIntent.putExtra("paEmail", paEmail);
                editPhoneStudentIntent.putExtra("user", user);
                PageStudent.this.startActivity(editPhoneStudentIntent);
            }
        });
        editPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent editPasswordStudentIntent = new Intent(PageStudent.this, EditPasswordStudent.class);
                editPasswordStudentIntent.putExtra("email", email);
                editPasswordStudentIntent.putExtra("password", password);
                editPasswordStudentIntent.putExtra("phone", phone);
                editPasswordStudentIntent.putExtra("name", name);
                editPasswordStudentIntent.putExtra("grade", grade);
                editPasswordStudentIntent.putExtra("paEmail", paEmail);
                editPasswordStudentIntent.putExtra("user", user);
                PageStudent.this.startActivity(editPasswordStudentIntent);
            }
        });

        viewMeetingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent editPasswordStudentIntent = new Intent(PageStudent.this, MeetingView.class);
                editPasswordStudentIntent.putExtra("email", email);
                editPasswordStudentIntent.putExtra("password", password);
                editPasswordStudentIntent.putExtra("phone", phone);
                editPasswordStudentIntent.putExtra("name", name);
                editPasswordStudentIntent.putExtra("grade", grade);
                editPasswordStudentIntent.putExtra("paEmail", paEmail);
                editPasswordStudentIntent.putExtra("user", user);
                PageStudent.this.startActivity(editPasswordStudentIntent);
            }
        });
        joinLeaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent editPasswordStudentIntent = new Intent(PageStudent.this, MeetingJoinLeave.class);
                editPasswordStudentIntent.putExtra("email", email);
                editPasswordStudentIntent.putExtra("password", password);
                editPasswordStudentIntent.putExtra("phone", phone);
                editPasswordStudentIntent.putExtra("name", name);
                editPasswordStudentIntent.putExtra("grade", grade);
                editPasswordStudentIntent.putExtra("paEmail", paEmail);
                editPasswordStudentIntent.putExtra("user", user);
                PageStudent.this.startActivity(editPasswordStudentIntent);
            }
        });
        studyMaterialsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent editPasswordStudentIntent = new Intent(PageStudent.this, studyMaterials.class);
                editPasswordStudentIntent.putExtra("email", email);
                editPasswordStudentIntent.putExtra("password", password);
                editPasswordStudentIntent.putExtra("phone", phone);
                editPasswordStudentIntent.putExtra("name", name);
                editPasswordStudentIntent.putExtra("grade", grade);
                editPasswordStudentIntent.putExtra("paEmail", paEmail);
                editPasswordStudentIntent.putExtra("user", user);
                PageStudent.this.startActivity(editPasswordStudentIntent);
            }
        });

        String title = name + "'s Page";
        tvName.setText(title);
        tvGrade.setText(grade + "");
        tvPaEmail.setText(paEmail);
        tvEmail.setText(email);
        tvPhone.setText(phone);
    }
}