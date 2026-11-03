package com.example.hellotoast;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hellotoast.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private int mCount = 0;

    private TextView mShowCount;
    private Button mButtonZero;
    private Button mButtonCount;

    private int mColorGray;
    private int mColorGreen;
    private int mColorBlue;
    private int mColorOrange;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top,
                        systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        mShowCount = findViewById(R.id.show_count);
        Button buttonToast = findViewById(R.id.button_toast);
        mButtonZero = findViewById(R.id.button_zero);
        mButtonCount = findViewById(R.id.button_count);

        mColorGray = ContextCompat.getColor(this, R.color.button_gray);
        mColorGreen = ContextCompat.getColor(this, R.color.button_green);
        mColorBlue = ContextCompat.getColor(this, R.color.button_blue);
        mColorOrange = ContextCompat.getColor(this, R.color.button_orange);

        if (savedInstanceState != null) {
            mCount = savedInstanceState.getInt("count", 0);
        }

        if (buttonToast != null) {
            buttonToast.setOnClickListener(this::showToast);
        }
        if (mButtonZero != null) {
            mButtonZero.setOnClickListener(this::showZero);
        }
        if (mButtonCount != null) {
            mButtonCount.setOnClickListener(this::showCount);
        }

        updateUI();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count", mCount);
    }

    private void updateUI() {
        if (mShowCount != null) {
            mShowCount.setText(String.valueOf(mCount));
        }

        if (mButtonZero != null) {
            int zeroColor = (mCount == 0) ? mColorGray : mColorOrange;
            mButtonZero.setBackgroundTintList(ColorStateList.valueOf(zeroColor));
            mButtonZero.setBackgroundColor(zeroColor);
        }

        if (mButtonCount != null) {
            int countColor;
            if (mCount == 0) {
                countColor = mColorBlue;
            } else if (mCount % 2 != 0) {
                countColor = mColorGreen;
            } else {
                countColor = mColorBlue;
            }
            mButtonCount.setBackgroundTintList(ColorStateList.valueOf(countColor));
            mButtonCount.setBackgroundColor(countColor);
        }
    }

    public void showToast(View view) {
        Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT).show();
    }

    public void show_toast(View view) {
        showToast(view);
    }

    public void showCount(View view) {
        mCount++;
        updateUI();
    }

    public void increase_count(View view) {
        showCount(view);
    }

    public void showZero(View view) {
        mCount = 0;
        updateUI();
    }

    public void resetCount(View view) {
        showZero(view);
    }
}
