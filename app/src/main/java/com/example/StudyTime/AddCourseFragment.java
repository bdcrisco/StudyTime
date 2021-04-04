package com.example.StudyTime;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
/*AddCourseFragment
extends Fragment
in order to be accessed
through our drawer fragment
returns layout for AddCourse page
allowing users to add a course
to their session study time
 */



public class AddCourseFragment extends Fragment {

    private View rootView;
    public AddCourseFragment(){
        rootView = null;
    }

    private EditText coursePrompt;
    private Button buttonSaveCourse;

    CourseList courseList = CourseList.getInstance();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        if (rootView ==null){
            Log.d("FragmentDemo", "Creating Layout for Settings Fragment");
            /*Display(inflate) the layout xml for this fragment */
            rootView = inflater.inflate(R.layout.settings, container, false);
            coursePrompt = rootView.findViewById(R.id.coursePrompt);
            setSaveCourse();
        }
        return rootView;
    }

    public void setSaveCourse() {
        buttonSaveCourse = rootView.findViewById(R.id.buttonSaveCourse);
        buttonSaveCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                courseList.addCourse(coursePrompt.getText().toString());
            }
        });
    }
}