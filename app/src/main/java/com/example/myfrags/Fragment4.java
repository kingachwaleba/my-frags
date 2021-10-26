package com.example.myfrags;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class Fragment4 extends Fragment {

    private FragsData fragsData;
    private Observer<Integer> numberObserver;
    private EditText edit;
    private TextWatcher textWatcher;
    private boolean turnOffWatcher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_4, container, false);

        edit = view.findViewById(R.id.editTextNumber);
        edit.setSelection(edit.getText().length());
        fragsData = new ViewModelProvider(requireActivity()).get(FragsData.class);

        numberObserver = new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                turnOffWatcher = true;
                edit.setText(integer.toString());
                edit.setSelection(edit.getText().length());
            }
        };

        fragsData.counter.observe(getViewLifecycleOwner(), numberObserver);

        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!turnOffWatcher) {
                    Integer integer;

                    try {
                        integer = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        integer = fragsData.counter.getValue();
                    }
                    fragsData.counter.setValue(integer);
                }
                else
                    turnOffWatcher = !turnOffWatcher;

                if (s.length() == 0) 
                    fragsData.counter.setValue(Integer.valueOf("0"));
            }
        };

        edit.addTextChangedListener(textWatcher);

        return view;
    }
}