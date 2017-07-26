package com.esgi.texttospeechspeakloop;

import android.content.Context;
import android.media.AudioManager;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Asifadam93 on 26/07/2017.
 */

public class TextToSpeechManager implements TextToSpeech.OnInitListener, TextToSpeech.OnUtteranceCompletedListener {

    private final static String UTTERANCE_ID = "FINISHED PLAYING";
    private TextToSpeech textToSpeech;
    private HashMap<String, String> paramMap;
    private String text = "Default string";
    private boolean isSpeechStopped = false;
    private Context context;

    public TextToSpeechManager(Context context) {
        this.context = context;
        textToSpeech = new TextToSpeech(context, this);
    }

    @Override
    public void onInit(int status) {

        Log.i("TextToSpeechManager", "onInit");

        if (status == TextToSpeech.SUCCESS) {

            int result = textToSpeech.setLanguage(Locale.getDefault());
            textToSpeech.addSpeech("bip", context.getPackageName(), R.raw.new_sms);
            textToSpeech.setOnUtteranceCompletedListener(this);

        } else {
            Log.i("TextToSpeechManager", "onInit error");
        }
    }

    public void speak() {
        Log.i("TextToSpeechManager", "speak");
        textToSpeech.speak(text, TextToSpeech.QUEUE_ADD, getParamMap());
        textToSpeech.speak("bip", TextToSpeech.QUEUE_ADD, getParamMap());
        isSpeechStopped = false;
    }

    public void stop() {
        if (textToSpeech != null) {
            isSpeechStopped = true;
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        Log.i("TextToSpeechManager", "stop");
    }

    @Override
    public void onUtteranceCompleted(String id) {

        if (id.equals(UTTERANCE_ID)) {
            if (!isSpeechStopped) {
                Log.i("TextToSpeechManager", "onUtteranceCompleted");
                speak();
            }
        }
    }

    private HashMap<String, String> getParamMap() {

        if (paramMap == null) {
            paramMap = new HashMap<>();
            paramMap.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_ALARM));
            paramMap.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, UTTERANCE_ID);
        }

        return paramMap;
    }

    public void setText(String text) {
        this.text = text;
    }
}
