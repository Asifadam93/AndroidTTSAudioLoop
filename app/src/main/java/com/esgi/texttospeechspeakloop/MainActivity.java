package com.esgi.texttospeechspeakloop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonStart, buttonStop;
    private TextToSpeechManager mtextToSpeechManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = (Button) findViewById(R.id.start_button);
        buttonStop = (Button) findViewById(R.id.stop_button);

        final TextToSpeechManager textToSpeechManager = new TextToSpeechManager(getBaseContext());

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeechManager.speak();
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeechManager.stop();
            }
        });

    }
}
