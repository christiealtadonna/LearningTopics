package example.com.learningtopics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import example.com.learningtopics.Utils.SharedPrefUtils;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //If user selects register button - bring them to register page
        Button registerButton = (Button) findViewById(R.id.login_register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = (EditText) findViewById(R.id.username);
                String user_name = username.getText().toString();
                Log.d("Username \n", user_name);
                EditText pswd = (EditText) findViewById(R.id.password);
                String password = pswd.getText().toString();
                Log.d("Password \n", password);

                Connect con = new Connect(LoginActivity.this, user_name, password);
                //must use instance of class that connects to database
                Log.d("entered button click", "button click");
                con.execute("login", user_name, password);

                SharedPrefUtils.setPasswordPreference(LoginActivity.this, password);
                username.setText("");
                pswd.setText("");

            }
        });


    }
}
