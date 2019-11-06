package com.example.word;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

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

        final TextView wordWord=(TextView) findViewById(R.id.word_Word);
        final String word=intent.getStringExtra("wordWord");
        final String sample=intent.getStringExtra("wordSample");
        wordWord.setText(intent.getStringExtra("wordWord"));

        TextView wordExplain=(TextView) findViewById(R.id.word_Explain);
        wordExplain.setText("解释："+intent.getStringExtra("wordExplain"));

        final TextView wordSample=(TextView) findViewById(R.id.word_Sample);
        wordSample.setText("示例："+intent.getStringExtra("wordSample"));

        final Button btn_voice=(Button) findViewById(R.id.btn_voice);
        btn_voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://dict.youdao.com/dictvoice?audio=";
//                Intent intent = new Intent();
//                //显示数据给用户
//                intent.setAction(Intent.ACTION_VIEW);
//                //设置为浏览器类型
//                intent.addCategory(Intent.CATEGORY_BROWSABLE);
//
//                //设置要访问的网址
//                Uri uri = Uri.parse(url+word);
//                intent.setData(uri);

                final MediaPlayer mediaPlayer = new MediaPlayer();
                // 设置指定的流媒体地址
                try {
                    mediaPlayer.setDataSource(url + word);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // 设置音频流的类型
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                // 通过异步的方式装载媒体资源
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        // 装载完毕 开始播放流媒体
                        mediaPlayer.start();
                        Toast.makeText(DetailActivity.this, "开始播放", Toast.LENGTH_SHORT).show();
                        // 避免重复播放，把播放按钮设置为不可用
                        //btn_voice.setEnabled(false);
                    }


                    //startActivity(intent);

                });


            }

        });

        final Button btn_Samplevoice=(Button) findViewById(R.id.btn_Samplevoice);
        btn_Samplevoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://dict.youdao.com/dictvoice?audio=";
//                Intent intent = new Intent();
//                //显示数据给用户
//                intent.setAction(Intent.ACTION_VIEW);
//                //设置为浏览器类型
//                intent.addCategory(Intent.CATEGORY_BROWSABLE);
//
//                //设置要访问的网址
//                Uri uri = Uri.parse(url+word);
//                intent.setData(uri);

                final MediaPlayer mediaPlayer = new MediaPlayer();
                // 设置指定的流媒体地址
                try {
                    mediaPlayer.setDataSource(url + sample);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // 设置音频流的类型
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                // 通过异步的方式装载媒体资源
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        // 装载完毕 开始播放流媒体
                        mediaPlayer.start();
                        Toast.makeText(DetailActivity.this, "开始播放", Toast.LENGTH_SHORT).show();
                        // 避免重复播放，把播放按钮设置为不可用
                        //btn_voice.setEnabled(false);
                    }


                    //startActivity(intent);

                });


            }

        });
    }
}






