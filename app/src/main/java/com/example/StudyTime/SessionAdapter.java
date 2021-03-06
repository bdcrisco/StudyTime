package com.example.StudyTime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/* SessionAdapter class
 * Based on the recycler view input returns the total number of items via the getItemCount() method. */
public class SessionAdapter extends RecyclerView.Adapter<SessionViewHolder> {

    private Context context;

    private List<Session> localList;

    public SessionAdapter(Context context, List<Session> sessionList){
        this.context = context;
        localList = sessionList;
    }

    public void update(List<Session> sessionList){
        localList = sessionList;
    }

    // inflates/returns row_sessions.xml layout to view saved session data
    @NonNull
    @Override
    public SessionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.row_session, parent, false);
        return new SessionViewHolder(view);
    }

    // updates list item position
    @Override
    public void onBindViewHolder(@NonNull SessionViewHolder holder, int position){
        holder.setDate(localList.get(position).getDate());
        holder.setCourseName(localList.get(position).getCourse().getCourseName());
        holder.setTime(localList.get(position).getTime());
        // if item list's position is even, will include background shading
        if(position% 2 == 0){
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.themeWhite));
        }else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.themeBlueGrey));
        }
    }

    @Override
    // returns the localList items
    public int getItemCount() {
        return localList == null ? 0 : localList.size();
    }
}