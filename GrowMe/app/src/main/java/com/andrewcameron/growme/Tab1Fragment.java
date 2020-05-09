package com.andrewcameron.growme;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

        return view;
    }
}
