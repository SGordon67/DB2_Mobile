package com.example.db2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterParentAct extends AppCompatActivity {

    EditText etEmail;
    EditText etName;
    EditText etPhone;
    EditText etPassword;
    Button regButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_parent);

        etEmail = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.password);
        etName = (EditText) findViewById(R.id.name);
        etPhone = (EditText) findViewById(R.id.phone);
        regButton = (Button) findViewById(R.id.RegButton);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                final String name = etName.getText().toString();
                final String email = etEmail.getText().toString();
                final String password = etPassword.getText().toString();
                final String phone = etPhone.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("pleaseHelp", response);
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                Intent intent = new Intent(RegisterParentAct.this, MainActivity.class);
                                RegisterParentAct.this.startActivity(intent);
                            } else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterParentAct.this);
                                builder.setMessage("Register Failed").setNegativeButton("Retry", null).create().show();
                            }
                        } catch (JSONException e) {
                            Log.d("pleaseHelp",response);
                            e.printStackTrace();
                        }
                    }
                };

                RegisterParentRequest registerRequest = new RegisterParentRequest(email, password, name, phone, getString(R.string.url) + "RegisterParent.php", responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterParentAct.this);
                queue.add(registerRequest);
            }

        });
    }
}