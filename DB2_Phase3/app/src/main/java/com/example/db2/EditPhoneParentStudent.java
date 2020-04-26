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

public class EditPhoneParentStudent extends AppCompatActivity {

    EditText etNewPhone;
    Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_phone_student);

        Intent intent = getIntent();
        final String email = intent.getStringExtra("email");
        final String password = intent.getStringExtra("password");
        final String phone = intent.getStringExtra("phone");
        final String name = intent.getStringExtra("name");
        final String sEmail = intent.getStringExtra("sEmail");
        final String sName = intent.getStringExtra("sName");
        final String user = intent.getStringExtra("user");

        etNewPhone = (EditText) findViewById(R.id.newPhone);
        confirmButton = (Button) findViewById(R.id.confirmButton);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                final String newPhone = etNewPhone.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("pleaseHelp", response);
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                Intent intent = new Intent(EditPhoneParentStudent.this, PageParent.class);
                                intent.putExtra("email", email);
                                intent.putExtra("password", password);
                                intent.putExtra("phone", phone);
                                intent.putExtra("name", name);
                                EditPhoneParentStudent.this.startActivity(intent);
                            } else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(EditPhoneParentStudent.this);
                                builder.setMessage("Email Change Failed").setNegativeButton("Retry", null).create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                EditPhoneRequest editEmailRequest = new EditPhoneRequest(sEmail, newPhone, "parent", getString(R.string.url) + "EditPhone.php", responseListener);
                RequestQueue queue = Volley.newRequestQueue(EditPhoneParentStudent.this);
                queue.add(editEmailRequest);
            }
        });
    }
}