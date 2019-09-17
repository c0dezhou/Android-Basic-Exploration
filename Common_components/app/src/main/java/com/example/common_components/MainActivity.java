package com.example.common_components;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstactivity);

        Button button1=(Button)findViewById(R.id.button1);

        button1.setOnClickListener(new OnClickListener() {//匿名内部类
            @Override
            public void onClick(View view) {
                TextView textView1=(TextView)findViewById(R.id.textView1);
                textView1.setText("成功修改文字");

            }
        });
        //通过findViewById获得CheckBox对象
        CheckBox checkBox1=(CheckBox)findViewById(R.id.checkBox1);
        CheckBox checkBox2=(CheckBox)findViewById(R.id.checkBox2);

        //注册事件监听器,控件绑定OnCheckBoxClickListener方法
        OnCheckBoxClickListener listener = new OnCheckBoxClickListener();
        checkBox1.setOnClickListener(listener);
        checkBox2.setOnClickListener(listener);




        RadioGroup radioGroup1=(RadioGroup)findViewById(R.id.radioGroup1);
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
                TextView textView2=(TextView)findViewById(R.id.textView2);

                    //boolean checked =((RadioButton)view).isChecked();
                    switch(checkedID){
                        case R.id.radioButton1:
                            textView2.setText("男");break;

                        case R.id.radioButton2:
                            textView2.setText("女");break;
                    }
            }

        });




    }
    class OnCheckBoxClickListener implements OnClickListener {

        @Override
        public void onClick(View view) {
            CheckBox checkBox = (CheckBox) view;
            TextView textView2=(TextView)findViewById(R.id.textView2);
            if (checkBox.getId() == R.id.checkBox1) {
                textView2.setText("C++");
            } else if (checkBox.getId() == R.id.checkBox2) {
                textView2.setText("Java");
            }

            }

        }

}










