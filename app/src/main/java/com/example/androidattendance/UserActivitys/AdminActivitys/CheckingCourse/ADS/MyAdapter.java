package com.example.androidattendance.UserActivitys.AdminActivitys.CheckingCourse.ADS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidattendance.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<Model> list;
    Context context;

    public MyAdapter(Context context,ArrayList<Model> list)
    {
        this.list=list;
        this.context=context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.course_student_card,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model model=list.get(position);
        holder.studentId.setText(model.getStudentId());
        holder.fullName.setText(model.getFullName());

    }

    @Override
    public int getItemCount() {
        return list.size() ;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView studentId,fullName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            studentId=itemView.findViewById(R.id.idText);
            fullName=itemView.findViewById(R.id.nameText);
        }
    }
}
