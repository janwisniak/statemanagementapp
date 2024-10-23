package com.example.myapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    private TextView counterTextView, additionalInfoTextView;
    private Button increaseButton;
    private EditText inputEditText;
    private CheckBox optionCheckBox;
    private Switch darkModeSwitch;

    private int counter = 0;
    private boolean isDarkMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Restore dark mode before calling super.onCreate()
        if (savedInstanceState != null) {
            isDarkMode = savedInstanceState.getBoolean("switchState", false);
        }

        // Apply the dark or light theme
        updateDarkMode(isDarkMode);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        counterTextView = findViewById(R.id.counterTextView);
        increaseButton = findViewById(R.id.increaseButton);
        inputEditText = findViewById(R.id.inputEditText);
        optionCheckBox = findViewById(R.id.optionCheckBox);
        additionalInfoTextView = findViewById(R.id.additionalInfoTextView);
        darkModeSwitch = findViewById(R.id.darkModeSwitch);

        // Restore saved instance state
        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt("counter", 0);
            counterTextView.setText(String.valueOf(counter));
            inputEditText.setText(savedInstanceState.getString("inputText", ""));
            optionCheckBox.setChecked(savedInstanceState.getBoolean("checkBoxState", false));
            darkModeSwitch.setChecked(isDarkMode);

            // Update UI based on restored state
            updateAdditionalInfoVisibility();
        }

        // Button click listener to increase counter
        increaseButton.setOnClickListener(v -> {
            counter++;
            counterTextView.setText(String.valueOf(counter));
        });

        // CheckBox listener to show/hide additional info
        optionCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            updateAdditionalInfoVisibility();
        });

        // Switch listener to toggle dark mode
        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isDarkMode = isChecked;
            updateDarkMode(isDarkMode);
            // Recreate the activity to apply the new theme
            recreate();
        });
    }

    // Save instance state
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("counter", counter);
        outState.putString("inputText", inputEditText.getText().toString());
        outState.putBoolean("checkBoxState", optionCheckBox.isChecked());
        outState.putBoolean("switchState", isDarkMode);
    }

    // Method to toggle dark mode
    private void updateDarkMode(boolean isDarkMode) {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    // Method to show/hide additional info based on CheckBox state
    private void updateAdditionalInfoVisibility() {
        if (optionCheckBox.isChecked()) {
            additionalInfoTextView.setVisibility(TextView.VISIBLE);
        } else {
            additionalInfoTextView.setVisibility(TextView.GONE);
        }
    }
}
