package com.example.android.hindi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class HindiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_hindi);
        // Find the View that shows the numbers category
        TextView numbers = (TextView) findViewById(R.id.numbers);

// Set a click listener on that View
        numbers.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(HindiActivity.this, NumbersActivity.class);
                startActivity(numbersIntent);
            }
        });
        // Find the View that shows the numbers category
        TextView family = (TextView) findViewById(R.id.family);

// Set a click listener on that View
        family.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent familyIntent = new Intent(HindiActivity.this, FamilyActivity.class);
                startActivity(familyIntent);
            }
        });
        TextView colors = (TextView) findViewById(R.id.colors);

// Set a click listener on that View
        colors.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent colorsIntent = new Intent(HindiActivity.this, ColorsActivity.class);
                startActivity(colorsIntent);
            }
        });
        TextView phrases = (TextView) findViewById(R.id.phrases);

// Set a click listener on that View
        phrases.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent phrasesIntent = new Intent(HindiActivity.this, PhrasesActivity.class);
                startActivity(phrasesIntent);
            }
        });
    }
}

