package com.example.db2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class PageAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_admin);

        TextView tvName = (TextView) findViewById(R.id.tvName);
        TextView tvEmail = (TextView) findViewById(R.id.tvEmail);
        TextView tvPassword = (TextView) findViewById(R.id.tvPassword);
        TextView tvPhone = (TextView) findViewById(R.id.tvPhone);
        Button editEmailButton = (Button) findViewById(R.id.editEmailButton);
        Button editPhoneButton = (Button) findViewById(R.id.editPhoneButton);
        Button editPasswordButton = (Button) findViewById(R.id.editPasswordButton);
        final LinearLayout userLayout = (LinearLayout) findViewById(R.id.userLayout);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        final String email = intent.getStringExtra("email");
        final String password = intent.getStringExtra("password");
        final String phone = intent.getStringExtra("phone");
        final String user = "admin";

        editEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent editAdminEmailIntent = new Intent(PageAdmin.this, EditEmailAdmin.class);
                editAdminEmailIntent.putExtra("email", email);
                editAdminEmailIntent.putExtra("password", password);
                editAdminEmailIntent.putExtra("phone", phone);
                editAdminEmailIntent.putExtra("name", name);
                editAdminEmailIntent.putExtra("user", user);
                PageAdmin.this.startActivity(editAdminEmailIntent);
            }
        });
        editPhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent editAdminPhoneIntent = new Intent(PageAdmin.this, EditPhoneAdmin.class);
                editAdminPhoneIntent.putExtra("email", email);
                editAdminPhoneIntent.putExtra("password", password);
                editAdminPhoneIntent.putExtra("phone", phone);
                editAdminPhoneIntent.putExtra("name", name);
                editAdminPhoneIntent.putExtra("user", user);
                PageAdmin.this.startActivity(editAdminPhoneIntent);
            }
        });
        editPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent editAdminPasswordIntent = new Intent(PageAdmin.this, EditPasswordAdmin.class);
                editAdminPasswordIntent.putExtra("email", email);
                editAdminPasswordIntent.putExtra("password", password);
                editAdminPasswordIntent.putExtra("phone", phone);
                editAdminPasswordIntent.putExtra("name", name);
                editAdminPasswordIntent.putExtra("user", user);
                PageAdmin.this.startActivity(editAdminPasswordIntent);
            }
        });

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("userInfo", response);
                    JSONObject jsonResponse = new JSONObject(response);
                    int i = 0;
                    while(jsonResponse.has(Integer.toString(i) + "uid")){
                        String uName = jsonResponse.getString(Integer.toString(i) + "uName");
                        String uEmail = jsonResponse.getString(Integer.toString(i) + "uEmail");
                        String uPhone = jsonResponse.getString(Integer.toString(i) + "uPhone");

                        String temp = "Name: " + uName;
                        String temp2 = "Email: " + uEmail + ", Phone: " + uPhone;
                        TextView userInfo = new TextView(PageAdmin.this);
                        TextView userInfo2 = new TextView(PageAdmin.this);
                        userInfo.setText(temp);
                        userInfo2.setText(temp2);
                        userLayout.addView(userInfo);
                        userLayout.addView(userInfo2);
                        userLayout.addView(new TextView(PageAdmin.this));
                        i++;
                    }
                } catch (JSONException e) {
                    // should not reach here
                    Log.d("whatHappened", response);
                    e.printStackTrace();
                }
            }
        };

        getUsersRequest getUsers = new getUsersRequest(getString(R.string.url) + "getUserInfo.php", responseListener);
        RequestQueue queue = Volley.newRequestQueue(PageAdmin.this);
        queue.add(getUsers);

        String title = name + "'s Page";
        tvName.setText(title);
        tvEmail.setText(email);
        tvPhone.setText(phone);
    }
}
