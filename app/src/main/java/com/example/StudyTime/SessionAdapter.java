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
import java.util.List;

public class SessionAdapter extends RecyclerView.Adapter<SessionViewHolder> {

    private Context context;
    private ViewGroup parent;
    SessionList sessionList = SessionList.getInstance();
    private List<Session> localList = sessionList.getList();

    public SessionAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public SessionViewHolder onCreateViewHolder(@NonNull ViewGroup paren, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.row_session, parent, false);
        return new SessionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SessionViewHolder holder, int position){
        holder.setDate(localList.get(position).getDate());
        holder.setCourseName(localList.get(position).getCourse().getCourseName());
        holder.setTime(localList.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return localList == null ? 0 : localList.size();
    }
}