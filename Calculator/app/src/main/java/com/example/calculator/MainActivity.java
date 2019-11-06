package com.example.calculator;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.javia.arity.Symbols;
import org.javia.arity.SyntaxException;
import java.math.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreatePanelMenu(int featureId,  Menu menu) {
        MenuInflater menuInflater=new MenuInflater(this);
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch(item.getItemId()){
            case R.id.help: {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                        dialog.setTitle("help");
                        dialog.setMessage("这是帮助");
                        dialog.setPositiveButton("确定", null);
                        dialog.show();
            }break;
            case R.id.exit: {
                Toast.makeText(this,"您选择了取消",Toast.LENGTH_SHORT).show();
            }break;
            case R.id.zhuanhuan: {
                Intent intent=new Intent(MainActivity.this, ChangeActivity.class);
                startActivity(intent);
            }break;
            case R.id.cdate:{
                Intent intent=new Intent(MainActivity.this, DateActivity.class);
                startActivity(intent);
            }break;
            case R.id.huilv:{
                Uri uri = Uri.parse("http://forex.hexun.com/rmbhl/#zkRate");//要跳转的网址
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }break;
        }
        return true;
    }

    public void  onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_add:
            case R.id.btn_sub:
            case R.id.btn_multiple:
            case R.id.btn_division:
            case R.id.btn_left:
            case R.id.btn_right:
            case R.id.btn_dot:
            {
                Button btn = (Button) view;
                String strAdded = btn.getText().toString();
                TextView formula = (TextView) findViewById(R.id.textFormula);
                String strContent = formula.getText().toString();
                String strNewContent = strContent + strAdded;
                formula.setText(strNewContent);
            }
            break;
            case R.id.btn_equal:
            {
                TextView formula = (TextView) findViewById(R.id.textFormula);
                String strContent = formula.getText().toString();

                try {
                    Symbols s = new Symbols();
                    double res = s.eval(strContent);

                    TextView result = (TextView) findViewById(R.id.textResult);
                    result.setText(String.valueOf(res));

//                    formula.setText("");

                } catch (SyntaxException e) {
                    Toast.makeText(MainActivity.this, "不能计算:)", Toast.LENGTH_SHORT).show();
                }
            }
            break;

            case R.id.btn_clean:
            {
                TextView formula = (TextView) findViewById(R.id.textFormula);
                formula.setText("");

                TextView result = (TextView) findViewById(R.id.textResult);
                result.setText("");
            }
            break;
            case R.id.btn_delete:
            {
                TextView formula = (TextView) findViewById(R.id.textFormula);
                String strContent = formula.getText().toString();
                if (strContent.length() > 0) {
                    strContent = strContent.substring(0, strContent.length() - 1);
                    formula.setText(strContent);
                }
            }
            break;
            case R.id.btn_sqrt:
            {
                TextView formula = (TextView) findViewById(R.id.textFormula);
                String strContent = formula.getText().toString();
                TextView result = (TextView) findViewById(R.id.textResult);
                double num1=Double.valueOf(strContent);
                double sum= Math.sqrt(num1);
                result.setText(String.valueOf(sum));
//                formula.setText("");
            }
            break;
            case R.id.btn_cbrt:
            {
                TextView formula = (TextView) findViewById(R.id.textFormula);
                String strContent = formula.getText().toString();
                TextView result = (TextView) findViewById(R.id.textResult);
                double num1=Double.valueOf(strContent);
                double sum= Math.cbrt(num1);
                result.setText(String.valueOf(sum));
//                formula.setText("");
            }
            break;
            case R.id.btn_pow2:
            {
                TextView formula = (TextView) findViewById(R.id.textFormula);
                String strContent = formula.getText().toString();
                TextView result = (TextView) findViewById(R.id.textResult);
                double num1=Double.valueOf(strContent);
                double sum= Math.pow(num1,2);
                result.setText(String.valueOf(sum));
//                formula.setText("");
            }
            break;
            case R.id.btn_pow3:
            {
                TextView formula = (TextView) findViewById(R.id.textFormula);
                String strContent = formula.getText().toString();
                TextView result = (TextView) findViewById(R.id.textResult);
                double num1=Double.valueOf(strContent);
                double sum= Math.pow(num1,3);
                result.setText(String.valueOf(sum));
//                formula.setText("");
            }
            break;
            case R.id.btn_sin:
            {
                TextView formula = (TextView) findViewById(R.id.textFormula);
                String strContent = formula.getText().toString();
                TextView result = (TextView) findViewById(R.id.textResult);
                double num1=Double.valueOf(strContent);
                double sum= Math.sin(num1);
                result.setText(String.valueOf(sum));
//                formula.setText("");
            }
            break;
            case R.id.btn_cos:
            {
                TextView formula = (TextView) findViewById(R.id.textFormula);
                String strContent = formula.getText().toString();
                TextView result = (TextView) findViewById(R.id.textResult);
                double num1=Double.valueOf(strContent);
                double sum= Math.cos(num1);
                System.out.println(Math.cos(55));
                result.setText(String.valueOf(sum));
//                formula.setText("");
            }
            break;
            case R.id.btn_tan:
            {
                TextView formula = (TextView) findViewById(R.id.textFormula);
                String strContent = formula.getText().toString();
                TextView result = (TextView) findViewById(R.id.textResult);
                double num1=Double.valueOf(strContent);
                double sum= Math.tan(num1);
                result.setText(String.valueOf(sum));
//                formula.setText("");
            }
            break;
            case R.id.btn_e:
            {
                TextView formula = (TextView) findViewById(R.id.textFormula);
                String strContent = formula.getText().toString();
                TextView result = (TextView) findViewById(R.id.textResult);
                double num1=Double.valueOf(strContent);
                double sum= Math.exp(num1);
                result.setText(String.valueOf(sum));
//                formula.setText("");
            }
            break;
            case R.id.btn_ln:
            {
                TextView formula = (TextView) findViewById(R.id.textFormula);
                String strContent = formula.getText().toString();
                TextView result = (TextView) findViewById(R.id.textResult);
                double num1=Double.valueOf(strContent);
                double sum= Math.log(num1);
                result.setText(String.valueOf(sum));
//                formula.setText("");
            }
            break;
            case R.id.btn_log:
            {
                TextView formula = (TextView) findViewById(R.id.textFormula);
                String strContent = formula.getText().toString();
                TextView result = (TextView) findViewById(R.id.textResult);
                double num1=Double.valueOf(strContent);
                double sum= Math.log10(num1);
                result.setText(String.valueOf(sum));
//                formula.setText("");
            }
            break;
            case R.id.btn_percent:
            {
                TextView formula = (TextView) findViewById(R.id.textFormula);
                String strContent = formula.getText().toString();
                TextView result = (TextView) findViewById(R.id.textResult);
                double num1=Double.valueOf(strContent);
                double sum= 0.01*(num1);
                result.setText(String.valueOf(sum));
//                formula.setText("");
            }
            break;
        }
    }
}
