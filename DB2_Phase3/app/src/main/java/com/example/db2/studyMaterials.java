package com.example.db2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class studyMaterials extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_info);

        TextView tvName = (TextView) findViewById(R.id.tvName);
        final LinearLayout meetingLayout = (LinearLayout) findViewById(R.id.meetingLayout);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        final int grade = intent.getIntExtra("grade", -1);
        final String paEmail = intent.getStringExtra("paEmail");
        final String email = intent.getStringExtra("email");
        final String password = intent.getStringExtra("password");
        final String phone = intent.getStringExtra("phone");
        final int mid = intent.getIntExtra("mid", -1);
        final String user = "student";

        String title = name + "'s Meetings";
        tvName.setText(title);
        Button returnButton = (Button) findViewById(R.id.returnButton);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(studyMaterials.this, PageStudent.class);

                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                intent.putExtra("paEmail", paEmail);
                intent.putExtra("grade", grade);
                intent.putExtra("phone", phone);

                studyMaterials.this.startActivity(intent);
            };
        });

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                TextView title1 = new TextView(studyMaterials.this);
                title1.setText("Study Materials Available:");
                title1.setTextSize(18);
                title1.setTextColor(Color.parseColor("#000000"));
                meetingLayout.addView(title1);
                try {
                    Log.d("pleaseHelp", response);
                    JSONObject jsonResponse = new JSONObject(response);
                    int i = 0;
                    while(jsonResponse.has(Integer.toString(i) + "mid")){
                        String mid = jsonResponse.getString(Integer.toString(i) + "mid");
                        String mUrl = jsonResponse.getString(Integer.toString(i) + "mUrl");

                        String temp = "Meeting ID: " + mid + ", URL: " + mUrl;
                        TextView matInfo = new TextView(studyMaterials.this);
                        matInfo.setText(temp);
                        meetingLayout.addView(matInfo);
                        meetingLayout.addView(new TextView(studyMaterials.this));
                        i++;
                    }
                } catch (JSONException e) {
                    // should not reach here
                    Log.d("???", response);
                    e.printStackTrace();
                }
            }
        };
        getStudyMaterialsRequest getStudy = new getStudyMaterialsRequest(email, getString(R.string.url) + "getMaterials.php", responseListener);
        RequestQueue queue = Volley.newRequestQueue(studyMaterials.this);
        queue.add(getStudy);
    }
}