package com.andrewcameron.growme;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Tab1Fragment extends Fragment {
    private static final String TAG = "Tab1Fragment";

    private Button btnTest;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_habits_fragment, container,false);

        btnTest = (Button) view.findViewById(R.id.btnTab1);

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "TESTING BUTTON CLICK 1", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
