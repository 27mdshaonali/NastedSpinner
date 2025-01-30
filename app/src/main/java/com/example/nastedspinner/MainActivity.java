package com.example.nastedspinner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Spinner spinnerMain, spinnerSub;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        spinnerMain = findViewById(R.id.spinnerMain);
        spinnerSub = findViewById(R.id.spinnerSub);
        textView = findViewById(R.id.textView);

        // Define main categories with a default option
        String[] mainCategories = {"Choose an option", "University", "College", "School", "Other"};

        // Define subcategories for each main category with a default option
        String[][] subCategories = {{}, // Empty for "Choose an option"
                {"Choose a subject", "English", "Economics", "Statistics"}, {"Choose a subject", "Physics", "Chemistry", "Biology"}, {"Choose a subject", "Math", "History", "Geography"}, {"Choose a subject", "Art", "Music", "Sports"}};

        // Define messages to be displayed based on subject selection
        String[][] messages = {{}, // Empty for "Choose an option"
                {"", "English is an important subject.", "Wow! Economics is a great subject.", "Statistics is tough, study well!"}, {"", "Physics is amazing!", "Chemistry is interesting!", "Biology is about life!"}, {"", "Math needs practice!", "History tells us about the past!", "Geography is about Earth!"}, {"", "Art is creative!", "Music is soulful!", "Sports keep you healthy!"}};

        // Set adapter for main spinner
        ArrayAdapter<String> mainAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mainCategories);
        mainAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMain.setAdapter(mainAdapter);

        // Hide sub spinner and text view initially
        spinnerSub.setVisibility(View.GONE);
        textView.setVisibility(View.GONE);

        // Handle selection in main spinner
        spinnerMain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    // If "Choose an option" is selected, hide sub spinner and text view
                    spinnerSub.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                } else {
                    // Show sub spinner and set adapter with default option
                    spinnerSub.setVisibility(View.VISIBLE);
                    ArrayAdapter<String> subAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, subCategories[position]);
                    subAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSub.setAdapter(subAdapter);

                    // Handle selection in sub spinner
                    spinnerSub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int subPosition, long id) {
                            if (subPosition == 0) {
                                // If "Choose a subject" is selected, hide text view
                                textView.setVisibility(View.GONE);
                            } else {
                                // Show text view and set message
                                textView.setVisibility(View.VISIBLE);
                                textView.setText(messages[position][subPosition]);

                                // Navigate to respective activity when certain subjects are selected
                                String selectedSubject = subCategories[position][subPosition];
                                if (selectedSubject.equals("Economics")) {
                                    String subjectName = "Economics";
                                    String subjectReview = "Wow! Economics is a great subject.";
                                    Intent intent = new Intent(MainActivity.this, MainAttraction.class);
                                    intent.putExtra(MainAttraction.SUBJECT_NAME, subjectName);
                                    intent.putExtra(MainAttraction.SUBJECT_REVIEW, subjectReview);
                                    intent.putExtra(MainAttraction.SUBJECT_IMAGE, R.drawable.economics);
                                    startActivity(intent);

                                } else if (selectedSubject.equals("Sports")) {

                                    String subjectName = "Sports";
                                    String subjectReview = "Sports keep you healthy!";
                                    Intent intent = new Intent(MainActivity.this, MainAttraction.class);
                                    intent.putExtra(MainAttraction.SUBJECT_NAME, subjectName);
                                    intent.putExtra(MainAttraction.SUBJECT_REVIEW, subjectReview);
                                    intent.putExtra(MainAttraction.SUBJECT_IMAGE, R.drawable.sports);
                                    startActivity(intent);

                                }
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            textView.setVisibility(View.GONE); // Hide text if no selection
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerSub.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
            }
        });
    }
}
