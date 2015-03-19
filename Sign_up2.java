package com.example.manes.test1;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.content.Intent;

import com.parse.*;


public class Sign_up2 extends ActionBarActivity {

    private Button register;
    private EditText reg_password, reg_email, reg_password2;
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        register = (Button)findViewById(R.id.register);
        reg_password = (EditText)findViewById(R.id.reg_password);
        reg_password2 = (EditText)findViewById(R.id.reg_password2);
        reg_email = (EditText)findViewById(R.id.reg_email);



        register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser user = new ParseUser();


                if( reg_password.getText().toString().isEmpty()|| reg_email.getText().toString().isEmpty()
                        || reg_password2.getText().toString().isEmpty()   )
                {

                    Toast.makeText(Sign_up2.this,"Saving user FAILED. Enter again.", Toast.LENGTH_LONG).show();
                }

                else if((reg_password.getText().toString().equals(reg_password2.getText().toString())) == false)
                {
                    Toast.makeText(Sign_up2.this,"Passwords are different, enter again.", Toast.LENGTH_LONG).show();
                }

                else if(reg_password.getText().toString().length() < 7 || reg_password2.getText().toString().length() < 7 )
                {
                    Toast.makeText(Sign_up2.this,"Password is too short. Enter again.", Toast.LENGTH_LONG).show();
                }

                else
                {
                    // Save new user data into Parse.com Data Storage


                    user.setUsername(reg_email.getText().toString());
                    user.setPassword(reg_password.getText().toString());
                    user.setEmail(reg_email.getText().toString());



                    user.signUpInBackground(new SignUpCallback() {
                        public void done(ParseException e) {
                            if (e == null) {
                                // Show a simple Toast message upon successful registration
                                Toast.makeText(getApplicationContext(),
                                        "Successfully Signed up, please log in.",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Sign up Error, enter again.", Toast.LENGTH_LONG)
                                        .show();
                            }
                        }
                    });




                }





            }
        });


    }





}
