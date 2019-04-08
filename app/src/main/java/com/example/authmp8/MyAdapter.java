package com.example.authmp8;

import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
{
    private List<String> list_of_courses;
    private List<String> list_of_grades;
    private List<String> list_of_students;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtHeader;
        public TextView txtFooter;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.courseNameView);
            txtFooter = (TextView) v.findViewById(R.id.gradeView);
        }
    }

    public void add(String student, String courseName, String grade) {
        list_of_students.add(student);
        list_of_courses.add(courseName);
        list_of_grades.add(grade);
    }

    public void remove(int position) {
        list_of_students.remove(position);
        list_of_courses.remove(position);
        list_of_grades.remove(position);
    }

    public MyAdapter(List<String> studentNames, List<String> courseNames, List<String> grades) {
        list_of_students = studentNames;
        list_of_courses = courseNames;
        list_of_grades = grades;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.recyclerview_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final String name = list_of_students.get(position);
        final String course = list_of_courses.get(position);
        final String grade = list_of_grades.get(position);

        holder.txtHeader.setText(name + ", " + course);

        holder.txtHeader.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(position);
            }
        });
        holder.txtFooter.setText(grade);
    }

    @Override
    public int getItemCount() {
        return list_of_courses.size();
    }
}
