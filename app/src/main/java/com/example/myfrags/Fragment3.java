package com.example.myfrags;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Fragment3 extends Fragment {

    private FragsData fragsData;
    private Observer<Integer> numberObserver;
    private TextView text;
    private Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_3, container, false);

        text = view.findViewById(R.id.current);
        button = view.findViewById(R.id.button_minus);
        fragsData = new ViewModelProvider(requireActivity()).get(FragsData.class);

        numberObserver = new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                text.setText(integer.toString());
            }
        };

        fragsData.counter.observe(getViewLifecycleOwner(), numberObserver);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer integer = fragsData.counter.getValue();
                fragsData.counter.setValue(--integer);
            }
        });

        return view;
    }
}