package com.example.calculator;


import android.app.DatePickerDialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.calculator.DateUtil;//计算天数程序包，写计算天数代码后导入即可。

import java.text.BreakIterator;
import java.util.Date;


public class DateActivity extends AppCompatActivity
{
    public Button btnChoose1Date;
    public Button btnChoose2Date;
    Date date1;
    Date date2;
    String theDate1;
    String theDate2;
    long between;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

//     将获取开始时间的按钮传进来
        btnChoose1Date= (Button) findViewById(R.id.btnChoose1Date);
//     在按钮中实现获取时间
        btnChoose1Date.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new DatePickerDialog(DateActivity.this,new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                    {
                        theDate1=(String.format("%d-%d-%d",year,monthOfYear+1,dayOfMonth));
                        System.out.println(theDate1);
                        //btnChoose1Date.setText(theDate1);
                        TextView text1=(TextView)findViewById(R.id.text1);
                        text1.setText("开始时间："+theDate1);
                        date1 = new Date(year - 1900, monthOfYear+1, dayOfMonth);//获取时间转换为Date对象
                    }
                },2016,4,11).show();
            }
        });

//     将获取结束时间的按钮传进来
        btnChoose2Date=(Button)findViewById(R.id.btnChoose2Date);
//    在按钮中实现获取时间
        btnChoose2Date.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new DatePickerDialog(DateActivity.this,new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                    {
                        theDate2=(String.format("%d-%d-%d",year,monthOfYear+1,dayOfMonth));
                        System.out.println(theDate2);
                        TextView text2=(TextView)findViewById(R.id.text2);
                        text2.setText("结束时间："+theDate2);
                        date2= new Date(year - 1900, monthOfYear+1, dayOfMonth); //获取时间转换为Date对象
                    }
                },2016,4,11).show();
            }
        });

//    将计算的天数传递给TextView
        findViewById(R.id.btnDaysAty).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                between= DateUtil.getDaysBetween(date1,date2);
                TextView days=(TextView) findViewById(R.id.days);
                days.setText("日期间隔为："+between+"天");
            }
        });
    }
}
