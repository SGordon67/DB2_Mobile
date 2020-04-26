package com.example.db2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterStudentAct extends AppCompatActivity {

    EditText etName;
    EditText etEmail;
    EditText etPassword;
    EditText etPaEmail;
    EditText etPhone;
    EditText etGrade;
    Button regButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);

        etName = (EditText) findViewById(R.id.name);
        etEmail = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.password);
        etPaEmail = (EditText) findViewById(R.id.paEmail);
        etPhone = (EditText) findViewById(R.id.phone);
        etGrade = (EditText) findViewById(R.id.grade);
        regButton = (Button) findViewById(R.id.regButton);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String paEmail = etPaEmail.getText().toString();
                String phone = etPhone.getText().toString();
                String grade = etGrade.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("pleaseHelp", response);
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                Intent intent = new Intent(RegisterStudentAct.this, MainActivity.class);
                                RegisterStudentAct.this.startActivity(intent);
                            } else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterStudentAct.this);
                                builder.setMessage("Register Failed").setNegativeButton("Retry", null).create().show();
                            }
                        } catch (JSONException e) {
                            Log.d("pleaseHelp",response);
                            e.printStackTrace();
                        }
                    }
                };

                RegisterStudentRequest registerRequest = new RegisterStudentRequest(email, password, name, phone, paEmail, grade, getString(R.string.url) + "RegisterStudent.php", responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterStudentAct.this);
                queue.add(registerRequest);
            }

        });
    }
}