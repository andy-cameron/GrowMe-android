package com.andrewcameron.growme;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Tab1Fragment extends Fragment {
    private static final String TAG = "Tab1Fragment";

    private Button btnTest;
    private TextView textViewDayOfWeek, textViewDayofMonth, textViewMonth, textViewSuffix;
    private String[] suffixes =
            //    0     1     2     3     4     5     6     7     8     9
            { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
                    //    10    11    12    13    14    15    16    17    18    19
                    "th", "th", "th", "th", "th", "th", "th", "th", "th", "th",
                    //    20    21    22    23    24    25    26    27    28    29
                    "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
                    //    30    31
                    "th", "st" };

    // Recycler View
    private DatabaseReference mDatabase;
    private FirebaseUser mUser;
    private List<UserHabits> mUserHabitsList;
    private RecyclerView mRecyclerView;
    private String uID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_habits_fragment, container,false);

        textViewDayOfWeek = (TextView) view.findViewById(R.id.txtDayOfWeek);
        textViewDayofMonth = (TextView) view.findViewById(R.id.txtDayOfMonth);
        textViewMonth = (TextView) view.findViewById(R.id.txtMonth);
        textViewSuffix = (TextView) view.findViewById(R.id.txtSuffix);

        Calendar sCalendar = Calendar.getInstance();
        String dayLongName = sCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault());
        int dateOfMonth = sCalendar.get(Calendar.DAY_OF_MONTH);
        String month = sCalendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault());

        textViewDayOfWeek.setText(dayLongName);
        textViewDayofMonth.setText(String.valueOf(dateOfMonth));
        textViewSuffix.setText((suffixes[dateOfMonth]));
        textViewMonth.setText(month);

        // Recycler View
        mRecyclerView = view.findViewById(R.id.recycler_view_habits);
//        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(container.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        prepareUserHabitsData();


        mUser = FirebaseAuth.getInstance().getCurrentUser();
        if (mUser != null) {
            uID = mUser.getUid();
            Log.i("USER ID", uID);
        }

        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        return view;
    }

    private void prepareUserHabitsData() {
        new GetDataFromFirebase().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        // Read From Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUserHabitsList = new ArrayList<>();

                for (DataSnapshot habitsSnapshot: dataSnapshot.getChildren()) {
                    String habitName = (String) habitsSnapshot.child("GbovxRp2QrW4wV1KSI8nfTjJwpW2").child("Test").getValue();

                    UserHabits userHabits = new UserHabits(habitName);
                    mUserHabitsList.add(userHabits);
                }
                mRecyclerView.setAdapter(new HabitsAdapter(mUserHabitsList));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("ERROR", "FAILED TO READ VALUE");
            }
        });
    }

    private class GetDataFromFirebase extends AsyncTask<Void,Void,Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }
}
