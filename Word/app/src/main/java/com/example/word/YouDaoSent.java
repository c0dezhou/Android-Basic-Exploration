package com.example.word;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class YouDaoSent extends AppCompatActivity {

    Button btn_sent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_dao_sent);

        btn_sent=(Button)findViewById(R.id.btn_sent);
        btn_sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = ((EditText) findViewById(R.id.edit_query)).getText().toString();
                Intent intent = new Intent(YouDaoSent.this, YDSearchActivity.class);
                intent.putExtra("ydWord", word);
                startActivity(intent);
            }
        });


    }
}
