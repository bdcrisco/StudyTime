package com.example.StudyTime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
//import com.example.recycler.model.Session;
//import com.example.recycler.ui.SessionViewHolder;

import java.util.ArrayList;

public class SessionAdapter extends RecyclerView.Adapter<SessionViewHolder> {

    private Context context;
    private ArrayList<Sessions> sessionArrayList;
    private ViewGroup parent;

    public SessionAdapter(Context context, ArrayList<Sessions> sessionArrayList){
        this.context = context;
        this.sessionArrayList = sessionArrayList;
    }

    @NonNull
    @Override
    public SessionViewHolder onCreateViewHolder(@NonNull ViewGroup paren, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.row_session, parent, false);
        return new SessionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SessionViewHolder holder, int position){
        Sessions session = sessionArrayList.get(position);
        holder.setDate(session.getDate());
        holder.setCourseName(session.getName());
        holder.setTime(session.getTime());
    }

    @Override
    public int getItemCount() {
        return sessionArrayList == null ? 0 : sessionArrayList.size();
    }


}