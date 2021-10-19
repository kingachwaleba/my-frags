package com.example.myfrags;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements Fragment1.OnButtonClickListener {

    private int[] frames;
    private boolean hidden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            frames = new int[]{R.id.frame1, R.id.frame2, R.id.frame3, R.id.frame4};
            hidden = false;

            Fragment[] fragments = new Fragment[]{new Fragment1(), new Fragment2(), new Fragment3(),
                    new Fragment4()};
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            for (int i = 0; i < 4; i ++) {
                transaction.add(frames[i], fragments[i]);
            }

            transaction.addToBackStack(null);
            transaction.commit();
        }
        else {
            frames = savedInstanceState.getIntArray("FRAMES");
            hidden = savedInstanceState.getBoolean("HIDDEN");
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putIntArray("FRAMES", frames);
        outState.putBoolean("HIDDEN", hidden);
    }

    @Override
    public void onButtonClickShuffle() {
        Toast.makeText(getApplicationContext(), "Shuffle", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onButtonClickClockwise() {
        Toast.makeText(getApplicationContext(), "Clockwise", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onButtonClickHide() {
        Toast.makeText(getApplicationContext(), "Hide", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onButtonClickRestore() {
        Toast.makeText(getApplicationContext(), "Restore", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);

        if (fragment instanceof  Fragment1) {
            ((Fragment1) fragment).setOnButtonClickListener(this);
        }
    }
}