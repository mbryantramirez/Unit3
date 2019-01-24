package org.pursuit.unit_03_assessment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.pursuit.unit_03_assessment.R;

public class DisplayActivity extends AppCompatActivity {
    private TextView planetTextView;
    private TextView planetTextView2;

    private ImageView planetImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        /*
        * TODO: Add logic that will:
        * TODO 1. Receive values from sending intent
        * TODO 2. Create a TextView instance for the Planet Name
        * TODO 3. Create a TextView instance for the Planet Number count
        * TODO 4. Create an ImageView for the image url
        * TODO 5. Display each value in views - Strings for TextViews, and Picasso for the ImageView
        */

        planetTextView = findViewById(R.id.planet_number);
        planetTextView2 = findViewById(R.id.planet_title);
        planetImageView = findViewById(R.id.imageView);
        Intent intent = getIntent();
        planetTextView.setText(intent.getStringExtra("planet"));
        planetTextView2.setText(intent.getStringExtra("planet count"));
        Picasso.get().load(intent.getStringExtra("image")).into(planetImageView);
    }
}
