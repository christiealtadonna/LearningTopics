package example.com.learningtopics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //If user selects cancel, bring them back to login page
        Button cancelButton = (Button) findViewById(R.id.register_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        final EditText confirmPwd = (EditText) findViewById(R.id.register_confirmpassword);
        Button registerButton = (Button) findViewById(R.id.register_submit_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = (EditText) findViewById(R.id.register_username);
                EditText password = (EditText) findViewById(R.id.register_password);
                EditText fullname = (EditText) findViewById(R.id.register_fullname);
                EditText college = (EditText) findViewById(R.id.register_school);
                EditText grade = (EditText) findViewById(R.id.register_grade);
                EditText major = (EditText) findViewById(R.id.register_major);
                String usrnm = username.getText().toString();
                String pswd = password.getText().toString();
                String flnm = fullname.getText().toString();
                String school = college.getText().toString();
                String grd = grade.getText().toString();
                String mjr = major.getText().toString();
                String confirm_pswd = confirmPwd.getText().toString();

                if(pswd.equals(confirm_pswd)) {
                    Connect conn = new Connect(RegisterActivity.this, usrnm, pswd);
                    conn.execute("register", usrnm, pswd, flnm, school, grd, mjr);
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Passwords did not match", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
