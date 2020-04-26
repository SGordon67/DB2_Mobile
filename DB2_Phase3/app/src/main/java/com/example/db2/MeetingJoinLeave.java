package com.example.db2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MeetingJoinLeave extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_view);

        TextView tvName = (TextView) findViewById(R.id.tvName);
        final LinearLayout meetingLayout = (LinearLayout) findViewById(R.id.meetingLayout);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        final int grade = intent.getIntExtra("grade", -1);
        final String paEmail = intent.getStringExtra("paEmail");
        final String email = intent.getStringExtra("email");
        final String password = intent.getStringExtra("password");
        final String phone = intent.getStringExtra("phone");
        final String user = "student";

        String title = name + "'s Meetings You Can Be In";
        tvName.setText(title);

        JoinAllButton JAButton = new JoinAllButton(MeetingJoinLeave.this, "Join All Meetings", email, name, grade, paEmail, password, phone, getString(R.string.url));
        LeaveAllButton LAButton = new LeaveAllButton(MeetingJoinLeave.this, "Leave All Meetings", email, name, grade, paEmail, password, phone, getString(R.string.url));
        meetingLayout.addView(JAButton.getButton());
        meetingLayout.addView(LAButton.getButton());

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                TextView title1 = new TextView(MeetingJoinLeave.this);
                title1.setText("Meetings you can Mentee in:");
                title1.setTextSize(18);
                title1.setTextColor(Color.parseColor("#000000"));
                meetingLayout.addView(title1);
                try {
                    Log.d("pleaseHelp", response);
                    JSONObject jsonResponse = new JSONObject(response);
                    int i = 0;
                    while(jsonResponse.has(Integer.toString(i) + "mid")){
                        String mName = jsonResponse.getString(Integer.toString(i) + "mName");
                        String mDate = jsonResponse.getString(Integer.toString(i) + "mDate");
                        String mStartTime = jsonResponse.getString(Integer.toString(i) + "mStartTime");
                        String mEndTime = jsonResponse.getString(Integer.toString(i) + "mEndTime");
                        int mid = Integer.parseInt(jsonResponse.getString(Integer.toString(i) + "mid"));

                        LeaveMeetingButton but = new LeaveMeetingButton(MeetingJoinLeave.this, "Leave Meeting", email, name, grade, paEmail, password, phone, getString(R.string.url), mid, "Mentee");
                        JoinMeetingButton but2 = new JoinMeetingButton(MeetingJoinLeave.this, "Join Meeting", email, name, grade, paEmail, password, phone, getString(R.string.url), mid, "Mentee");
                        String temp = mName + ", " + mDate + ", at " + mStartTime + "-" + mEndTime + "";
                        TextView meetInfo = new TextView(MeetingJoinLeave.this);
                        meetInfo.setText(temp);
                        meetingLayout.addView(meetInfo);
                        meetingLayout.addView(but2.getButton());
                        meetingLayout.addView(but.getButton());
                        meetingLayout.addView(new TextView(MeetingJoinLeave.this));
                        i++;
                    }
                } catch (JSONException e) {
                    // should not reach here
                    Log.d("???", response);
                    e.printStackTrace();
                }
            }
        };

        Response.Listener<String> responseListener2 = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                TextView title1 = new TextView(MeetingJoinLeave.this);
                title1.setText("Meetings you can Mentor in:");
                title1.setTextSize(18);
                title1.setTextColor(Color.parseColor("#000000"));
                meetingLayout.addView(title1);
                try {
                    Log.d("pleaseHelp", response);
                    JSONObject jsonResponse = new JSONObject(response);
                    int i = 0;
                    while(jsonResponse.has(Integer.toString(i) + "mid")){
                        String mName = jsonResponse.getString(Integer.toString(i) + "mName");
                        String mDate = jsonResponse.getString(Integer.toString(i) + "mDate");
                        String mStartTime = jsonResponse.getString(Integer.toString(i) + "mStartTime");
                        String mEndTime = jsonResponse.getString(Integer.toString(i) + "mEndTime");
                        int mid = Integer.parseInt(jsonResponse.getString(Integer.toString(i) + "mid"));

                        JoinMeetingButton but = new JoinMeetingButton(MeetingJoinLeave.this, "Join Meeting", email, name, grade, paEmail, password, phone, getString(R.string.url), mid, "Mentor");
                        LeaveMeetingButton but2 = new LeaveMeetingButton(MeetingJoinLeave.this, "Leave Meeting", email, name, grade, paEmail, password, phone, getString(R.string.url), mid, "Mentor");
                        String temp = mName + ", " + mDate + ", at " + mStartTime + "-" + mEndTime + "";
                        TextView meetInfo = new TextView(MeetingJoinLeave.this);
                        meetInfo.setText(temp);
                        meetingLayout.addView(meetInfo);
                        meetingLayout.addView(but.getButton());
                        meetingLayout.addView(but2.getButton());
                        meetingLayout.addView(new TextView(MeetingJoinLeave.this));
                        i++;
                    }
                } catch (JSONException e) {
                    // should not reach here
                    Log.d("???", response);
                    e.printStackTrace();
                }
            }
        };

        getMenteeMeetingsRequest getMenteeMeet = new getMenteeMeetingsRequest(email,getString(R.string.url) + "getPosMenteeMeetings.php", responseListener);
        RequestQueue queue = Volley.newRequestQueue(MeetingJoinLeave.this);


        getMentorMeetingsRequest getMentorMeet = new getMentorMeetingsRequest(email,getString(R.string.url) + "getPosMentorMeetings.php", responseListener2);
        queue.add(getMentorMeet);
        queue.add(getMenteeMeet);
    }
}
