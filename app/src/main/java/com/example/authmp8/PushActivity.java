package com.example.authmp8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.content.Intent;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class PushActivity extends AppCompatActivity {

    Intent i;

    FirebaseDatabase fbdb;
    DatabaseReference dbrf;
    int radioID = R.id.rad_ralph;
    int studentID = 404;

    EditText courseID;
    EditText courseName;
    EditText grade;

    int idNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push);

        fbdb = FirebaseDatabase.getInstance();
        dbrf = fbdb.getReference();
    }

    public void radioClick(View view) {
        radioID = view.getId();
        if(radioID==R.id.rad_bart){studentID = 123;}
        if(radioID==R.id.rad_ralph){studentID = 404;}
        if(radioID==R.id.rad_milhouse){studentID = 456;}
        if(radioID==R.id.rad_lisa){studentID = 888;}
    }

    public void addGrade(View view) {
        courseID = findViewById(R.id.courseID);
        courseName = findViewById(R.id.courseName);
        grade = findViewById(R.id.grade);

        String idString = courseID.getText().toString();
        courseID.getText().clear();
        idNum = Integer.parseInt(idString);
        String nameString = courseName.getText().toString();
        courseName.getText().clear();

        String gradeString = grade.getText().toString();
        grade.getText().clear();

        DatabaseReference dbrf1 = fbdb.getReference("simpsons/grades/");

        String dbid = dbrf1.push().getKey();

        Grade grade = new Grade(idNum,nameString,gradeString,studentID);
        dbrf1.child(dbid).setValue(grade);

        i = new Intent(getApplicationContext(), PullActivity.class);
        startActivity(i);
    }
}
