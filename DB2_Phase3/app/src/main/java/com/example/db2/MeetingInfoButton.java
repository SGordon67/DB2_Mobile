package com.example.db2;

import android.content.Context;
import android.content.Intent;
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

public class MeetingInfoButton {
    Button editButton;
    String sEmail;
    String sName;
    String sPEmail;
    String sPassword;
    String sPhone;
    int sGrade;
    int sMid;
    String url;
    public MeetingInfoButton(final Context context, String text, String email, String name, int grade, String pEmail, String password, String phone, String myUrl, int mid){
        sEmail = email;
        sName = name;
        sPEmail = pEmail;
        sPassword = password;
        sPhone = phone;
        sGrade = grade;
        sMid = mid;
        url = myUrl;
        editButton = new Button(context);
        editButton.setText(text);
        editButton.setId(sMid);
        editButton.setTag(sMid);

        editButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent meetInfo = new Intent(context, MeetingInfo.class);
                meetInfo.putExtra("name", sName);
                meetInfo.putExtra("email", sEmail);
                meetInfo.putExtra("password", sPassword);
                meetInfo.putExtra("paEmail", sPEmail);
                meetInfo.putExtra("grade", sGrade);
                meetInfo.putExtra("phone", sPhone);
                meetInfo.putExtra("mid", sMid);
                context.startActivity(meetInfo);
            }
        });

    }
    public Button getButton(){
        return editButton;
    }
    public int getID(){
        return editButton.getId();
    }
    public int getTag(){
        return (int) editButton.getTag();
    }
}
