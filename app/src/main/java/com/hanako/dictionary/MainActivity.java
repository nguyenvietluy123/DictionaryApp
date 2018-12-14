package com.hanako.dictionary;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText edtSearch;
    private ListView lvWord;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtSearch = (EditText) findViewById(R.id.edt_search);
        lvWord = (ListView) findViewById(R.id.lv_words);


        DatabaseAccess dbAccess = DatabaseAccess.getInstance(this);
        dbAccess.open();
        ArrayList<String> words = dbAccess.getWords();
        dbAccess.close();


        //Load du lieu len list view
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, words);
        lvWord.setAdapter(adapter);
        lvWord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedWord = lvWord.getItemAtPosition(position).toString();
                Intent intent = new Intent(MainActivity.this, DefinitionActivity.class);
                intent.putExtra("word", selectedWord);
                startActivity(intent);
            }
        });

    }
    //Ham search
}
