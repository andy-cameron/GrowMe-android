package com.andrewcameron.growme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HabitsAdapter extends RecyclerView.Adapter<HabitsAdapter.MyViewHolder> {

    private List<UserHabits> mUserHabitsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView habitName;

        public MyViewHolder(View view) {
            super(view);
            habitName = (TextView) view.findViewById(R.id.habit_name);
        }
    }

    public HabitsAdapter(List<UserHabits> userHabitsList) {
        mUserHabitsList = userHabitsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.habit_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        UserHabits userHabitsList = mUserHabitsList.get(position);
        holder.habitName.setText(userHabitsList.getHabitName());
    }

    @Override
    public int getItemCount() {
        return mUserHabitsList.size();
    }
}
