package com.example.word;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    long stopTime1=0;
    static boolean isIn=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        isIn=true;
        //Intent接收数据并显示
        Intent intent =getIntent();

        TextView wordWord=(TextView) findViewById(R.id.word_Word);
        wordWord.setText(intent.getStringExtra("wordWord"));

        TextView wordExplain=(TextView) findViewById(R.id.word_Explain);
        wordExplain.setText("解释："+intent.getStringExtra("wordExplain"));

        TextView wordSample=(TextView) findViewById(R.id.word_Sample);
        wordSample.setText("示例："+intent.getStringExtra("wordSample"));


    }








}
