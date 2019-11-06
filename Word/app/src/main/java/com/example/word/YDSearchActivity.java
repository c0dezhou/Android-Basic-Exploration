package com.example.word;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

import cn.cocook.httpclient.HttpResponse;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.word.YDUtil.parseJsonWithGson;

public class YDSearchActivity extends AppCompatActivity {


//    private static final String TAG = "myTag";
//
//    private EditText shuru;
//    private TextView xianshi;
//    private TextView[] tvPhonetics = new TextView[3];
//    private ListView listView;
//    //private YDWord ydWord = null;
//    private Button btn_chaxun;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ydserch);
//
//        shuru = (EditText) findViewById(R.id.ed_sr);
//        xianshi=(TextView) findViewById(R.id.tv_xs);
//        btn_chaxun=(Button)findViewById(R.id.btn_search);
//
//
////        // 获取传进来的Word的值
////        Intent intent = getIntent();
////        String word = intent.getStringExtra("ydWord");
////        Log.i(TAG, "onCreate: " + word);
//
//        String word=shuru.getText().toString();
//
//        String url="http://fanyi.youdao.com/openapi.do?keyfrom=lewe518&key=70654389&type=data&doctype=json&version=1.1&q=";
//        OkHttpClient okHttpClient = new OkHttpClient();
//
//        final Request request = new Request.Builder()
//                .url(url+word)
//                .get()//默认就是GET请求，可以不写
//                .build();
//
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                try {
//                   final YDWord ydword=parseJsonWithGson(response.body().string());
//
//                    btn_chaxun.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            xianshi.setText(ydword.getQuery());
//                        }
//                    });
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//
//            }
//        });
//        //获得输入的值
//
//
//        //xianshi.setText(ydWord.getTranslation());
//
//
//                //msg.equals("success")
//
//
//
//    }

    private static final String TAG = "myTag";

    private TextView tvQuery,tvExplains;
    private TextView[] tvPhonetics = new TextView[3];
    private ListView listView;
    private YDWord ydWord = null;
    private Button btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ydserch);

        tvQuery = (TextView) findViewById(R.id.tv_query);
        tvPhonetics[0] = (TextView) findViewById(R.id.tv_USPhonetic);
        tvPhonetics[1] = (TextView) findViewById(R.id.tv_Phonetic);
        tvPhonetics[2] = (TextView) findViewById(R.id.tv_UKPhonetic);
        tvExplains = (TextView) findViewById(R.id.tv_explains);
        listView = (ListView) findViewById(R.id.listView_web);

        // 获取传进来的Word的值
        Intent intent = getIntent();
        String word = intent.getStringExtra("ydWord");
        Log.i(TAG, "onCreate: " + word);


        //String word= tvQuery.getText().toString();

        // 创建调用有道API的线程，启动线程
        YDJson yd = new YDJson(word);
        yd.start();
        try {
            // 等待子线程执行完毕
            yd.join();

            // 获取子线程中得到的JSON数据
            String jsonString = yd.getJsonResult();

            // 使用工厂方法解析返回的JSON数据并创建有道的单词对象
            ydWord = YDUtil.parseJsonWithGson(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 将创建好的有道单词对象的数据放到Activity中显示
        tvQuery.setText(ydWord.getQuery());
        for(int i=0; i<tvPhonetics.length; i++){
            tvPhonetics[i].setText(ydWord.getPhonetics()[i]);
        }
        String explains = "";
        int num = ydWord.getExplains().size();
        for(int i=0; i<num; i++){
            explains = explains + ydWord.getExplains().get(i);
            if(i != num-1){
                explains = explains + "\n";
            }
        }
        tvExplains.setText(explains);

        SimpleAdapter adapter = new SimpleAdapter(this, ydWord.getWebs(), R.layout.youdaoweb_item
                ,new String[]{"webKey", "webValue"}
                ,new int[]{R.id.tv_webKey, R.id.tv_webValue});
        listView.setAdapter(adapter);

//        try{
//            btn_update = (Button)findViewById(R.id.btn_update);
//            if( btn_update != null) {
//
//                btn_update.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        WordsDB wordsDB = WordsDB.getWordsDB();
//                        if (wordsDB != null) {
//                            ArrayList<Map<String, String>> items = wordsDB.SearchOne(ydWord.getQuery().toLowerCase());
//                            if (items.size() == 0) {
//                                wordsDB.Insert(ydWord.getQuery(), ydWord.getTranslation().substring(2, ydWord.getTranslation().length()-2), ydWord.getExplains().toString());
//                                Toast.makeText(YoudaoActivity.this, "添加单词成功", Toast.LENGTH_SHORT).show();
//                            } else {
//                                String id = items.get(0).get(Words.Word._ID);
//                                wordsDB.Update(id, ydWord.getQuery().toLowerCase(), ydWord.getTranslation().substring(2, ydWord.getTranslation().length()-2)
//                                        , ydWord.getExplains().toString());
//                                Toast.makeText(YoudaoActivity.this, "更新单词成功", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }
//                });
//            }
//        }catch (Exception e){
//
//        }
    }
}
