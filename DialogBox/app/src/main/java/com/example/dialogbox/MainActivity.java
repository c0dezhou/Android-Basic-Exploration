package com.example.dialogbox;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin;
    private Button btnCancel;
    private Button Test;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.login_dialog);

         Test = (Button) findViewById(R.id.Test);



            Test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater =getLayoutInflater();
                View layout =inflater.inflate(R.layout.login_dialog,(ViewGroup) findViewById(R.id.container));
                builder.setTitle("Login");
                final EditText strUsername =(EditText)layout.findViewById(R.id.edtUsername);
                final EditText strPassword =(EditText)layout.findViewById(R.id.edtPassword);

                builder.setView(layout).setTitle("Login")
                        .setPositiveButton(R.string.login, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(MainActivity.this,strUsername.getText().toString(),
                                        Toast.LENGTH_LONG).show();
                                if ( strUsername.getText().toString().equals("abc") && strPassword.getText().toString().equals("123")) {
                                    Toast.makeText(MainActivity.this, "恭喜，用户名与密码正确！",
                                            Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "遗憾，用户名或密码错误！请重新输入！",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "您选择取消登录",
                                        Toast.LENGTH_LONG).show();
                                finish();
                            }
                        });

                builder.show();
            }
        });
    }


}
