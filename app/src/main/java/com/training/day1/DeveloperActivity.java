package com.training.day1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;

public class DeveloperActivity extends AppCompatActivity {

    de.hdodenhof.circleimageview.CircleImageView pindahdika ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        pindahdika = findViewById(R.id.dika);

        pindahdika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(DeveloperActivity.this,andikahasbigumdi.class);
                       startActivity(intent);
            }
        });

//        getSupportActionBar().hide();
    }
}
