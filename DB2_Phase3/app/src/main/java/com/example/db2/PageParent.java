package com.example.db2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
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

public class PageParent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_parent);

        TextView tvName = (TextView) findViewById(R.id.tvName);
        TextView tvEmail = (TextView) findViewById(R.id.tvEmail);
        TextView tvPassword = (TextView) findViewById(R.id.tvPassword);
        TextView tvPhone = (TextView) findViewById(R.id.tvPhone);

        Button editEmailButton = (Button) findViewById(R.id.editEmailButton);
        Button editPhoneButton = (Button) findViewById(R.id.editPhoneButton);
        Button editPasswordButton = (Button) findViewById(R.id.editPasswordButton);

        final LinearLayout childLayout = (LinearLayout) findViewById(R.id.childLayout);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        final String email = intent.getStringExtra("email");
        final String password = intent.getStringExtra("password");
        final String phone = intent.getStringExtra("phone");
        final String user = "parent";

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            try {
                Log.d("pleaseHelp", response);
                JSONObject jsonResponse = new JSONObject(response);
                int i = 0;
                while(jsonResponse.has(Integer.toString(i) + "sid")){
                    String sName = jsonResponse.getString(Integer.toString(i) + "sName");
                    String sEmail = jsonResponse.getString(Integer.toString(i) + "sEmail");
                    int sid = Integer.parseInt(jsonResponse.getString(Integer.toString(i) + "sid"));
                    TextView studentName = new TextView(PageParent.this);
                    studentName.setText(sName);
                    TextView studentEmail = new TextView(PageParent.this);
                    studentEmail.setText(sEmail);
                    ParentEditStudentButton but = new ParentEditStudentButton(PageParent.this, "Edit Student's Info", sEmail, sName, sid, name, email, password, phone);
                    childLayout.addView(studentName);
                    childLayout.addView(studentEmail);
                    childLayout.addView(but.getButton());
                    childLayout.addView(new TextView(PageParent.this));
                    i++;
                }
            } catch (JSONException e) {
                // should not reach here
                Log.d("???", response);
                e.printStackTrace();
            }
            }
        };

        getParentChildRequest getChild = new getParentChildRequest(email,getString(R.string.url) + "getParentChild.php", responseListener);
        RequestQueue queue = Volley.newRequestQueue(PageParent.this);
        queue.add(getChild);


        editEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent editEmailParentIntent = new Intent(PageParent.this, EditEmailParent.class);
                editEmailParentIntent.putExtra("email", email);
                editEmailParentIntent.putExtra("password", password);
                editEmailParentIntent.putExtra("phone", phone);
                editEmailParentIntent.putExtra("name", name);
                editEmailParentIntent.putExtra("user", user);
                PageParent.this.startActivity(editEmailParentIntent);
            }
        });
        editPhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent editPhoneParentIntent = new Intent(PageParent.this, EditPhoneParent.class);
                editPhoneParentIntent.putExtra("email", email);
                editPhoneParentIntent.putExtra("password", password);
                editPhoneParentIntent.putExtra("phone", phone);
                editPhoneParentIntent.putExtra("name", name);
                editPhoneParentIntent.putExtra("user", user);
                PageParent.this.startActivity(editPhoneParentIntent);
            }
        });
        editPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent editPasswordParentIntent = new Intent(PageParent.this, EditPasswordParent.class);
                editPasswordParentIntent.putExtra("email", email);
                editPasswordParentIntent.putExtra("password", password);
                editPasswordParentIntent.putExtra("phone", phone);
                editPasswordParentIntent.putExtra("name", name);
                editPasswordParentIntent.putExtra("user", user);
                PageParent.this.startActivity(editPasswordParentIntent);
            }
        });

        String title = name + "'s Page";
        tvName.setText(title);
        tvEmail.setText(email);
        tvPhone.setText(phone);
    }
}