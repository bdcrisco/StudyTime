package com.example.StudyTime;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        if (rootView ==null){
            Log.d("FragmentDemo", "Creating Layout for Settings Fragment");
            /*Display(inflate) the layout xml for this fragment */
            rootView = inflater.inflate(R.layout.settings, container, false);
        }
        return rootView;

    }




}