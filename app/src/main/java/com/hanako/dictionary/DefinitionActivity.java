package com.hanako.dictionary;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class DefinitionActivity extends AppCompatActivity {

    private TextView tvWord;
    private TextView tvDefinition;
    private Button btnSpeak;
    private TextToSpeech hs1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition);

        tvWord = (TextView) findViewById(R.id.tv_word);
        tvDefinition = (TextView) findViewById(R.id.tv_definition);
        btnSpeak = findViewById(R.id.btn_speak);

        Intent intent = getIntent();
        String word = intent.getStringExtra("word");
        tvWord.setText(word);
        DatabaseAccess dbAccess = DatabaseAccess.getInstance(this);
        dbAccess.open();
        String definition = dbAccess.getDefinition(word);
        dbAccess.close();
        tvDefinition.setText(Html.fromHtml(definition));

        hs1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR){
                    hs1.setLanguage(Locale.UK);
                }
            }
        });
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hs1.speak(tvWord.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }
}
