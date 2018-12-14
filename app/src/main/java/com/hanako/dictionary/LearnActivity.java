package com.hanako.dictionary;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class LearnActivity extends AppCompatActivity {

    public static int REQ_CODE_SPEECH_INPUT = 123;
    private Random randomGenerator;
    private TextView tv_random;
    Integer index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tv_random = findViewById(R.id.tv_word_learn);

        setContentView(R.layout.activity_learn);
        DatabaseAccess dbAccess = DatabaseAccess.getInstance(this);
        dbAccess.open();
        ArrayList<String> words = dbAccess.getWords();
        dbAccess.close();

        // index = randomGenerator.nextInt(words.size());
        //Random r = new Random();
        //System.out.println(words.size() + "");

    }
    private void promptSpeechInput(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Recognize");
        startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 123: {
                if (requestCode==RESULT_OK&&null!=data){
                    ArrayList<String>result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //result[0];
                }
                break;
            }
        }
    }


}
