package com.example.calculator;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.javia.arity.Symbols;
import org.javia.arity.SyntaxException;
import org.w3c.dom.Text;

import java.math.BigInteger;
import java.util.Stack;

public class ChangeActivity extends AppCompatActivity {
    private static final String TARGET_STR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final char[] chs = TARGET_STR.toCharArray();
    private static final BigInteger INTEGER0 = new BigInteger("0");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        setContentView(R.layout.activity_change);





        Button btn_zh =(Button)findViewById(R.id.btn_danjizh);
        btn_zh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TextView changeformula = (TextView) findViewById(R.id.changeFormula);
                String strformula = changeformula.getText().toString();
                TextView yuan = (TextView) findViewById(R.id.yuan);
                int stryuan = Integer.parseInt(yuan.getText().toString());
                TextView mudi = (TextView) findViewById(R.id.mudi);
                int strmudi =Integer.parseInt(mudi.getText().toString());

                String res=transRadix(strformula,stryuan,strmudi);
                //Toast.makeText(ChangeActivity.this,res,Toast.LENGTH_SHORT).show();
                TextView result = (TextView) findViewById(R.id.zhuanhuanshu);
                result.setText(res);
            }
        });

        RadioGroup rg1 = (RadioGroup) findViewById(R.id.zh1);
        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.cm:{
                        TextView dwyuan = (TextView) findViewById(R.id.dwshuru);
                        Double strdwyuan = Double.parseDouble(dwyuan.getText().toString());
                        String textcm=String.valueOf(strdwyuan);
                        String textdm=String.valueOf(strdwyuan*0.1);
                        String textm=String.valueOf(strdwyuan*0.01);
                        TextView rescm = (TextView) findViewById(R.id.xscm);
                        rescm.setText(textcm);
                        TextView resdm = (TextView) findViewById(R.id.xsdm);
                        resdm.setText(textdm);
                        TextView resm = (TextView) findViewById(R.id.xsm);
                        resm.setText(textm);

                    }
                    break;
                    case R.id.dm:{
                        TextView dwyuan = (TextView) findViewById(R.id.dwshuru);
                        Double strdwyuan = Double.parseDouble(dwyuan.getText().toString());
                        String textcm=String.valueOf(strdwyuan*10);
                        String textdm=String.valueOf(strdwyuan);
                        String textm=String.valueOf(strdwyuan*0.1);
                        TextView rescm = (TextView) findViewById(R.id.xscm);
                        rescm.setText(textcm);
                        TextView resdm = (TextView) findViewById(R.id.xsdm);
                        resdm.setText(textdm);
                        TextView resm = (TextView) findViewById(R.id.xsm);
                        resm.setText(textm);

                    }
                        break;
                    case R.id.m:
                    {
                        TextView dwyuan = (TextView) findViewById(R.id.dwshuru);
                        Double strdwyuan = Double.parseDouble(dwyuan.getText().toString());
                        String textcm=String.valueOf(strdwyuan*100);
                        String textdm=String.valueOf(strdwyuan*10);
                        String textm=String.valueOf(strdwyuan);
                        TextView rescm = (TextView) findViewById(R.id.xscm);
                        rescm.setText(textcm);
                        TextView resdm = (TextView) findViewById(R.id.xsdm);
                        resdm.setText(textdm);
                        TextView resm = (TextView) findViewById(R.id.xsm);
                        resm.setText(textm);

                    }
                        break;
                    default:
                        //Toast.makeText(this, "secret", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

        });

        RadioGroup rg2 = (RadioGroup) findViewById(R.id.zh2);
        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.cm2:{
                        TextView dwyuan = (TextView) findViewById(R.id.dwshuru);
                        Double strdwyuan = Double.parseDouble(dwyuan.getText().toString());
                        String textcm2=String.valueOf(strdwyuan);
                        String textdm2=String.valueOf(strdwyuan*0.01);
                        String textm2=String.valueOf(strdwyuan*0.0001);
                        TextView rescm2 = (TextView) findViewById(R.id.xscm2);
                        rescm2.setText(textcm2);
                        TextView resdm2 = (TextView) findViewById(R.id.xsdm2);
                        resdm2.setText(textdm2);
                        TextView resm2 = (TextView) findViewById(R.id.xsm2);
                        resm2.setText(textm2);

                    }
                    break;
                    case R.id.dm2:{
                        TextView dwyuan = (TextView) findViewById(R.id.dwshuru);
                        Double strdwyuan = Double.parseDouble(dwyuan.getText().toString());
                        String textcm2=String.valueOf(strdwyuan*100);
                        String textdm2=String.valueOf(strdwyuan);
                        String textm2=String.valueOf(strdwyuan*0.01);
                        TextView rescm2 = (TextView) findViewById(R.id.xscm2);
                        rescm2.setText(textcm2);
                        TextView resdm2 = (TextView) findViewById(R.id.xsdm2);
                        resdm2.setText(textdm2);
                        TextView resm2 = (TextView) findViewById(R.id.xsm2);
                        resm2.setText(textm2);

                    }
                    break;
                    case R.id.m2:
                    {
                        TextView dwyuan = (TextView) findViewById(R.id.dwshuru);
                        Double strdwyuan = Double.parseDouble(dwyuan.getText().toString());
                        String textcm2=String.valueOf(strdwyuan*10000);
                        String textdm2=String.valueOf(strdwyuan*100);
                        String textm2=String.valueOf(strdwyuan);
                        TextView rescm2 = (TextView) findViewById(R.id.xscm2);
                        rescm2.setText(textcm2);
                        TextView resdm2 = (TextView) findViewById(R.id.xsdm2);
                        resdm2.setText(textdm2);
                        TextView resm2 = (TextView) findViewById(R.id.xsm2);
                        resm2.setText(textm2);

                    }
                    break;
                    default:
                        //Toast.makeText(this, "secret", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

        });

        RadioGroup rg3 = (RadioGroup) findViewById(R.id.zh3);
        rg3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.cm3:{
                        TextView dwyuan = (TextView) findViewById(R.id.dwshuru);
                        Double strdwyuan = Double.parseDouble(dwyuan.getText().toString());
                        String textcm3=String.valueOf(strdwyuan);
                        String textdm3=String.valueOf(strdwyuan*0.001);
                        String textm3=String.valueOf(strdwyuan*0.000001);
                        TextView rescm3 = (TextView) findViewById(R.id.xscm3);
                        rescm3.setText(textcm3);
                        TextView resdm3 = (TextView) findViewById(R.id.xsdm3);
                        resdm3.setText(textdm3);
                        TextView resm3 = (TextView) findViewById(R.id.xsm3);
                        resm3.setText(textm3);

                    }
                    break;
                    case R.id.dm3:{
                        TextView dwyuan = (TextView) findViewById(R.id.dwshuru);
                        Double strdwyuan = Double.parseDouble(dwyuan.getText().toString());
                        String textcm3=String.valueOf(strdwyuan*1000);
                        String textdm3=String.valueOf(strdwyuan);
                        String textm3=String.valueOf(strdwyuan*0.001);
                        TextView rescm3 = (TextView) findViewById(R.id.xscm3);
                        rescm3.setText(textcm3);
                        TextView resdm3 = (TextView) findViewById(R.id.xsdm3);
                        resdm3.setText(textdm3);
                        TextView resm3 = (TextView) findViewById(R.id.xsm3);
                        resm3.setText(textm3);

                    }
                    break;
                    case R.id.m3:
                    {
                        TextView dwyuan = (TextView) findViewById(R.id.dwshuru);
                        Double strdwyuan = Double.parseDouble(dwyuan.getText().toString());
                        String textcm3=String.valueOf(strdwyuan*1000000);
                        String textdm3=String.valueOf(strdwyuan*1000);
                        String textm3=String.valueOf(strdwyuan);
                        TextView rescm3 = (TextView) findViewById(R.id.xscm3);
                        rescm3.setText(textcm3);
                        TextView resdm3 = (TextView) findViewById(R.id.xsdm3);
                        resdm3.setText(textdm3);
                        TextView resm3 = (TextView) findViewById(R.id.xsm3);
                        resm3.setText(textm3);

                    }
                    break;
                    default:
                        //Toast.makeText(this, "secret", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

        });

    }
    public static String numToRadix(String number, int radix) {
        if(radix < 0 || radix > TARGET_STR.length()){
            radix = TARGET_STR.length();
        }

        BigInteger bigNumber = new BigInteger(number);
        BigInteger bigRadix = new BigInteger(radix + "");

        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder(0);
        while (!bigNumber.equals(INTEGER0)) {
            stack.add(chs[bigNumber.remainder(bigRadix).intValue()]);
            bigNumber = bigNumber.divide(bigRadix);
        }
        for (; !stack.isEmpty(); ) {
            result.append(stack.pop());
        }
        return result.length() == 0 ? "0" : result.toString();
    }

    /**
     * 任意进制转10进制
     */
    public static String radixToNum(String number, int radix){
        if(radix < 0 || radix > TARGET_STR.length()){
            radix = TARGET_STR.length();
        }
        if (radix == 10) {
            return number;
        }

        char ch[] = number.toCharArray();
        int len = ch.length;

        BigInteger bigRadix = new BigInteger(radix + "");
        BigInteger result = new BigInteger("0");
        BigInteger base = new BigInteger("1");


        for (int i = len - 1; i >= 0; i--) {
            BigInteger index = new BigInteger(TARGET_STR.indexOf(ch[i]) + "");
            result = result.add(index.multiply(base)) ;
            base = base.multiply(bigRadix);
        }

        return result.toString();
    }


    /**
     * 任意进制之间的互相转换, 先将任意进制转为10进制, 然后在转换为任意进制
     */
    public static String transRadix(String num, int fromRadix, int toRadix) {
        return numToRadix(radixToNum(num, fromRadix), toRadix);
    }






}
