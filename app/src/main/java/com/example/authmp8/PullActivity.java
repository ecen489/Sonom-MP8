package com.example.authmp8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.LinearlayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PullActivity extends AppCompatActivity {

    EditText editText1;

    Intent i;
    Intent iLO;

    Button buttonQ1;
    Button buttonQ2;

    List<Grade> list_of_Grades;

    LinearLayoutManager layoutManager;

    RecyclerView.Adapter recyclerViewAdapter1;

    RecyclerView recyclerView1;

    FirebaseDatabase fbdb;
    DatabaseReference dbrf;

    public final Map<Integer, String> studentIDs = new HashMap<Integer, String>() {{ put(123, "Bart"); put(404, "Ralph"); put(456, "Milhouse"); put(888, "Lisa"); }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull2);


        fbdb = FirebaseDatabase.getInstance();
        dbrf = fbdb.getReference();

        recyclerView1 = (RecyclerView) findViewById(R.id.recyclerView1);
        buttonQ1 = (Button) findViewById(R.id.buttonQ1);
        buttonQ2= (Button) findViewById(R.id.buttonQ2);
        editText1 = (EditText) findViewById(R.id.studentID);
    }

    public void pushButtonClick(View view) {
        i = new Intent(getApplicationContext(), PushActivity.class);
        startActivity(i);
    }

    public void logOutButtonClick(View view) {
        iLO = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(iLO);
    }

    public void buttonQ1Click(View view) {
        int studID = Integer.parseInt(editText1.getText().toString());

        DatabaseReference gradeKey = dbrf.child("simpsons/grades/");

        Query query = gradeKey.orderByChild("student_id").equalTo(studID);
        query.addListenerForSingleValueEvent(valueEventListener1);

    }

    public void buttonQ2Click(View view) {
        int studID = Integer.parseInt(editText1.getText().toString());

        DatabaseReference gradeKey = dbrf.child("simpsons/grades/");

        Query query = gradeKey.orderByChild("student_id").startAt(studID);
        query.addListenerForSingleValueEvent(valueEventListener2);

    }

    ValueEventListener valueEventListener1 = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                list_of_Grades = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Grade grade = snapshot.getValue(Grade.class);
                    list_of_Grades.add(grade);
                }

                List<String> temp_list_of_C = new ArrayList<>();
                List<String> temp_list_of_G = new ArrayList<>();
                List<String> temp_list_of_N = new ArrayList<>();

                for (Grade obj : list_of_Grades) {
                    temp_list_of_N.add(studentIDs.get(obj.getstudent_id()));
                    temp_list_of_C.add(obj.getcourse_name());
                    temp_list_of_G.add(obj.getgrade());
                }

                recyclerViewAdapter1 = new MyAdapter(temp_list_of_N, temp_list_of_C, temp_list_of_G);
                recyclerView1.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView1.setLayoutManager(layoutManager);
                recyclerView1.setAdapter(recyclerViewAdapter1);
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    }; // end valueEventListener1

    ValueEventListener valueEventListener2 = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                list_of_Grades = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Grade grade = snapshot.getValue(Grade.class);
                    list_of_Grades.add(grade);
                }

                List<String> temp_list_of_C = new ArrayList<>();
                List<String> temp_list_of_G = new ArrayList<>();
                List<String> temp_list_of_N = new ArrayList<>();

                for (Grade obj : list_of_Grades) {
                    temp_list_of_N.add(studentIDs.get(obj.getstudent_id()));
                    temp_list_of_C.add(obj.getcourse_name());
                    temp_list_of_G.add(obj.getgrade());
                }

                recyclerViewAdapter1 = new MyAdapter(temp_list_of_N, temp_list_of_C, temp_list_of_G);
                recyclerView1.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView1.setLayoutManager(layoutManager);
                recyclerView1.setAdapter(recyclerViewAdapter1);
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    }; // end valueEventListener2


} // End Pull Activity
