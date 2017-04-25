package example.com.learningtopics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        Intent intent = getIntent();
        String fullname = intent.getStringExtra("fullname");
        String school = intent.getStringExtra("school");
        String major = intent.getStringExtra("major");
        String grade = intent.getStringExtra("grade");

        TextView fullnameView = (TextView) findViewById(R.id.profile_fullname);
        TextView schoolView = (TextView) findViewById(R.id.profile_school);
        TextView majorView = (TextView) findViewById(R.id.profile_major);
        TextView gradeView = (TextView) findViewById(R.id.profile_grade);

        fullnameView.setText(fullname);
        schoolView.setText(school);
        majorView.setText(major);
        gradeView.setText(grade);

    }
}
