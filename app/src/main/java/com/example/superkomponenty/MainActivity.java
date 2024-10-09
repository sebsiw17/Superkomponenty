package com.example.superkomponenty;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tworzenie CustomCircleView programowo
        CustomCircleView customCircleView = new CustomCircleView(this);
        setContentView(customCircleView);
    }
}