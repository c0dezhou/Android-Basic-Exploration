package com.example.word;

import android.content.Intent;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        List<Map<String, String>> items= (List<Map<String, String>>) bundle.getSerializable("result");

        SimpleAdapter adapter = new SimpleAdapter(this, items, R.layout.activity_detail,
                new String[]{Word.Words._ID,Word.Words.COLUMN_NAME_WORD, Word.Words.COLUMN_NAME_MEANING, Word.Words.COLUMN_NAME_SAMPLE},
                new int[]{R.id.textId,R.id.word_Word, R.id.word_Explain, R.id.word_Sample});



        ListView list = (ListView) findViewById(R.id.lstSearchResultWords);

        list.setAdapter(adapter);
    }
}