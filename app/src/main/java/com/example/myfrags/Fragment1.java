package com.example.myfrags;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment1 extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        // Connect an interfaces' methods with fragments' buttons
        Button buttonShuffle = view.findViewById(R.id.button_shuffle);
        Button buttonClockwise = view.findViewById(R.id.button_clockwise);
        Button buttonHide = view.findViewById(R.id.button_hide);
        Button buttonRestore = view.findViewById(R.id.button_restore);

        buttonShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null)
                    callback.onButtonClickShuffle();
            }
        });

        buttonClockwise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null)
                    callback.onButtonClickClockwise();
            }
        });

        buttonHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null)
                    callback.onButtonClickHide();
            }
        });

        buttonRestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null)
                    callback.onButtonClickRestore();
            }
        });

        return view;
    }

    // Create an inner interface with four methods
    // Main activity will implement this inteface
    public interface OnButtonClickListener {
        void onButtonClickShuffle();
        void onButtonClickClockwise();
        void onButtonClickHide();
        void onButtonClickRestore();
    }

    private OnButtonClickListener callback = null;

    // Connect main activity with fragment
    public void setOnButtonClickListener(OnButtonClickListener callback) {
        this.callback = callback;
    }
}