package com.example.db2;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class ParentEditStudentButton {
    Button editButton;
    String sName;
    String sEmail;
    String email;
    String password;
    String phone;
    String name;

    public ParentEditStudentButton(final Context context, String header, String s_email, String s_name, int sid, String p_name, String p_email, String p_password, String p_phone){
        sName =  s_name;
        sEmail = s_email;
        email = p_email;
        password = p_password;
        phone = p_phone;
        name = p_name;

        editButton = new Button(context);
        editButton.setText(header);
        editButton.setId(sid);
        editButton.setTag(sid);
        editButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent editChildIntent = new Intent(context, ParentEditStudent.class);
                editChildIntent.putExtra("email", email);
                editChildIntent.putExtra("password", password);
                editChildIntent.putExtra("phone", phone);
                editChildIntent.putExtra("name", name);
                editChildIntent.putExtra("sName", sName);
                editChildIntent.putExtra("sEmail", sEmail);
                editChildIntent.putExtra("user", "parent");
                context.startActivity(editChildIntent);
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
