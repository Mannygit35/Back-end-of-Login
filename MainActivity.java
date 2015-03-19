package com.example.manes.test1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.View;
import java.lang.String;
import java.util.List;
import android.view.*;
import com.parse.RequestPasswordResetCallback;

import android.content.Intent;
import android.widget.Toast;

import com.parse.*;


public class MainActivity extends ActionBarActivity {

    private Button btn_enter, sign_up;
    private TextView change_text,forgot_psw;
    private EditText username, password;
    private String strusername;
    private Integer count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        btn_enter = (Button)findViewById(R.id.btn_enter);
        change_text = (TextView)findViewById(R.id.change_text);
        forgot_psw = (TextView)findViewById(R.id.forgot_psw);
        sign_up = (Button)findViewById(R.id.sign_up);



        forgot_psw.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser.requestPasswordResetInBackground(username.getText().toString(),new RequestPasswordResetCallback() {

            public void done(ParseException e)
            {
                if (e == null)
                {
                    Toast.makeText(getApplicationContext(),"Confirmation E-mail sent to E-mail provided.", Toast.LENGTH_LONG).show();
                }
                // An email was successfully sent with reset instructions.
                else
                {
                    Toast.makeText(getApplicationContext(),"Reset password E-mail not sent.", Toast.LENGTH_LONG).show();
                }
                    }});
         }});

       btn_enter.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {




                ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null)
                        {

                            // Retrieve current user from Parse.com
                            ParseUser currentUser = ParseUser.getCurrentUser();
                            // Convert currentUser into String
                            strusername = currentUser.getUsername().toString();
                            // If user exist and authenticated, send user to Welcome.class
                            //Intent intent = new Intent(LoginSignupActivity.this, Welcome.class);
                            //startActivity(intent);
                            Toast.makeText(getApplicationContext(), strusername +" is logged in.", Toast.LENGTH_LONG).show();
                            //finish();
                        }
                        else
                        {
                            count++;
                            Toast.makeText(getApplicationContext(), "Incorrect info. Please signup or enter again", Toast.LENGTH_LONG).show();
                        }

                        if(count == 2)
                        {
                            forgot_psw.setVisibility(View.VISIBLE);
                        }
                    }
                });



           }

       });

        sign_up.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent new_class = new Intent(MainActivity.this, Sign_up2.class);
                    startActivity(new_class);
                    finish();

                }




        });




















    }
}
