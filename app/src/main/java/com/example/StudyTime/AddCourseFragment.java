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

/*A fragment for the addCourse functionality to be accessed by the drawer.
*    Allows the user to add courses to their personal list*/
public class AddCourseFragment extends Fragment {

    private View rootView;
    public AddCourseFragment(){
        rootView = null;
    }

    private EditText coursePrompt;
    private Button buttonSaveCourse;

    CourseList courseList = CourseList.getInstance();

    // onCreateView initializes the fragment's view, and returns it to it's super
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        if (rootView == null){
            Log.d("FragmentDemo", "Creating Layout for Settings Fragment");
            /*Display(inflate) the layout xml for this fragment */
            rootView = inflater.inflate(R.layout.settings, container, false);
            coursePrompt = rootView.findViewById(R.id.coursePrompt);
            setSaveCourse();
        }
        return rootView;
    }

    // sets a button to allow for the user to add a course
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