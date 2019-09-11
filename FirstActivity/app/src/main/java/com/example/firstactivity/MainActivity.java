package com.example.firstactivity;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        Button button1 = (Button) findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {//按钮监听器
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "You clicked Button 1",
//                        Toast.LENGTH_SHORT).show();
//                finish();
//             1.   Intent intent =new Intent(MainActivity.this,SecondActivity.class);
//                Intent intent =new Intent("com.example.firstactivity.ACTION_START");
//                intent.addCategory("com.example.firstactivity.My_CATEGORY");
//            2.    Intent intent =new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("http://www.baidu.com"));
                String data="hello secondactivity.";
                Intent intent =new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("extra_data",data);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, "you clicked add", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "you clicked remove", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Test_item:
                Toast.makeText(this, "you clicked Test", Toast.LENGTH_SHORT).show();
                break;
            //default:

            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        return true;
    }
}