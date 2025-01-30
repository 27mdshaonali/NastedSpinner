package com.example.nastedspinner;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainAttraction extends AppCompatActivity {

    public static String SUBJECT_NAME = "SUBJECT_NAME";
    public static String SUBJECT_REVIEW = "SUBJECT_REVIEW";
    public static String SUBJECT_IMAGE = "SUBJECT_IMAGE";

    ImageView imageView;
    TextView subjectName, subjectReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_attraction);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imageView = findViewById(R.id.imageView);
        subjectName = findViewById(R.id.subjectName);
        subjectReview = findViewById(R.id.subjectReview);

        subjectName.setText(getIntent().getStringExtra(SUBJECT_NAME));
        subjectReview.setText(getIntent().getStringExtra(SUBJECT_REVIEW));
        imageView.setImageResource(getIntent().getIntExtra(SUBJECT_IMAGE, 0));


    }
}