package com.example.newstest;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.newstest.gson.NewsList;
import com.example.newstest.util.*;
import com.example.newstest.gson.News;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final int  ITEM_SOCIETY= 1;
    private static final int  ITEM_INTERNATION= 2;
    private static final int  ITEM_FUN= 3;
    private static final int  ITEM_SPORT= 4;
    private static final int  ITEM_TECHNOLOGY= 5;
    private static final int  ITEM_WAR= 6;
    private static final int  ITEM_INTERNET= 7;
    private static final int  ITEM_IT= 8;

    private List<Title> titleList = new ArrayList<Title>();
    private ListView listView;
    private TitleAdapter adapter;
    private News news;

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private SwipeRefreshLayout refreshLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_layout);
        refreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        listView = (ListView)findViewById(R.id.list_view);
        adapter = new TitleAdapter(MainActivity.this,R.layout.list_view_item, titleList);
        listView.setAdapter(adapter);
        Toolbar toolbar= (Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icons_ch);
        }
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("国际新闻");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Intent intent = new Intent(MainActivity.this, ContentActivity.class);
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Title title = titleList.get(position);
                intent.putExtra("title",actionBar.getTitle());
                intent.putExtra("uri",title.getUri());
                Toast.makeText(MainActivity.this,title.getUri(),Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_internation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.nav_internation:
                        handleCurrentPage("国际新闻",ITEM_INTERNATION);
                        break;
                    case R.id.nav_society:
                        handleCurrentPage("社会新闻",ITEM_SOCIETY);
                        break;
                    case R.id.nav_fun:
                        handleCurrentPage("娱乐新闻",ITEM_FUN);
                        break;
                    case R.id.nav_sport:
                        handleCurrentPage("体育新闻",ITEM_SPORT);
                        break;
                    case R.id.nav_technology:
                        handleCurrentPage("科技新闻",ITEM_TECHNOLOGY);
                        break;
                    case R.id.nav_war:
                        handleCurrentPage("军事新闻",ITEM_WAR);
                        break;
                    case R.id.nav_internet:
                        handleCurrentPage("移动互联",ITEM_INTERNET);
                        break;
                    case R.id.nav_it:
                        handleCurrentPage("IT资讯",ITEM_IT);
                        break;
                    default:
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(true);
                int itemName = parseString((String)actionBar.getTitle());
                requestNew(itemName);
            }
        });
        requestNew(ITEM_INTERNATION);
    }

    private void handleCurrentPage(String text, int item){
        ActionBar actionBar = getSupportActionBar();
        if (!text.equals(actionBar.getTitle().toString())){
            actionBar.setTitle(text);
            requestNew(item);
            refreshLayout.setRefreshing(true);
        }
    }


    public void requestNew(int itemName){

        Log.v("myTag","aaaa");
        //String url = "http://api.tianapi.com/world/?key=7091e6eaf023dab07a9ff13c5d33b0af&num=50";

        String url = response(itemName);
        OkHttpClient okHttpClient = new OkHttpClient();

        final Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("myTag", "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //Log.d("myTag", "onResponse: " + response.body().string());


                final String responseText = response.body().string();
                Log.v("myTag",responseText);
                final NewsList newslist = Utility.parseJsonWithGson(responseText);
                final int code = newslist.code;
                final String msg = newslist.msg;

                Log.v("myTag",msg);
                Log.v("myTag",code+"");
                //msg.equals("success")
                if (code==200){
                    titleList.clear();
                    for (News news:newslist.newsList){
                        Title title = new Title(news.title,news.description,news.picUrl, news.url);
                        titleList.add(title);

                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                            listView.setSelection(0);

                            refreshLayout.setRefreshing(false);

                        };
                    });
                }else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "数据错误返回",Toast.LENGTH_SHORT).show();
                            refreshLayout.setRefreshing(false);
                        }
                    });
                }
            }
        });


         //根据返回到的 URL 链接进行申请和返回数据
//        String address = "http://api.tianapi.com/social/?key=7091e6eaf023dab07a9ff13c5d33b0af&num=50";   // key
//        HttpUtil.sendOkHttpRequest(address, new Callback() {
//
//
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.v("myTag","abc");
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(MainActivity.this, "新闻加载失败", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }

            //@Override
//            public void onResponse(Call call, Response response) throws IOException {
////                Log.v("myTag","234");
////
////                final String responseText = response.body().string();
////                Log.v("myTag",responseText);
////                final NewsList newslist = Utility.parseJsonWithGson(responseText);
////                final int code = newslist.code;
////                final String msg = newslist.msg;
////
////                if (msg=="success"){
////                    titleList.clear();
////                    for (News news:newslist.newsList){
////                        Title title = new Title(news.title,news.description,news.picUrl, news.url);
////                        titleList.add(title);
////
////                    }
////
////                    runOnUiThread(new Runnable() {
////                        @Override
////                        public void run() {
////                            adapter.notifyDataSetChanged();
////                            listView.setSelection(0);
////
////                            //refreshLayout.setRefreshing(false);
////
////                        };
////                    });
////                }else{
////                    runOnUiThread(new Runnable() {
////                        @Override
////                        public void run() {
////                            Toast.makeText(MainActivity.this, "数据错误返回",Toast.LENGTH_SHORT).show();
////                            //refreshLayout.setRefreshing(false);
////                        }
////                    });
////                }
//
//
//
//            }
       // });


    }

    /**
     * 输入不同的类型选项，返回对应的 URL 链接
     */
    private String response(int itemName){
        String address = "http://api.tianapi.com/world/?key=7091e6eaf023dab07a9ff13c5d33b0af&num=50";
        switch(itemName){
            case ITEM_INTERNATION:
                break;

            case ITEM_SOCIETY:
                address = address.replaceAll("world","social");
//                address="http://api.tianapi.com/social/?key=7091e6eaf023dab07a9ff13c5d33b0af&num=50&rand=1";
                break;
            case ITEM_FUN:
                address = address.replaceAll("world","huabian");
//                address="http://api.tianapi.com/huabian/?key=7091e6eaf023dab07a9ff13c5d33b0af&num=50&rand=1";
                break;
            case ITEM_SPORT:
                address = address.replaceAll("world","tiyu");
//                address="http://api.tianapi.com/tiyu/?key=7091e6eaf023dab07a9ff13c5d33b0af&num=50&rand=1";
                break;

            case ITEM_TECHNOLOGY:
                address = address.replaceAll("world","keji");
                break;

            case ITEM_WAR:
                address = address.replaceAll("world","military");
                break;
            case ITEM_INTERNET:
                address = address.replaceAll("world","mobile");
                break;

            case ITEM_IT:
                address = address.replaceAll("world","it");
                break;
            default:
        }
        return address;
    }

    /**
     * 通过 actionbar.getTitle() 的参数，返回对应的 ItemName
     */
    private int parseString(String text){
        if (text.equals("社会新闻")){
            return ITEM_SOCIETY;
        }

        if (text.equals("国际新闻")){
            return ITEM_INTERNATION;
        }
        if (text.equals("娱乐新闻")){
            return ITEM_FUN;
        }
        if (text.equals("体育新闻")){
            return ITEM_SPORT;
        }

        if (text.equals("科技新闻")){
            return ITEM_TECHNOLOGY;
        }
        if (text.equals("军事新闻")){
            return ITEM_WAR;
        }
        if (text.equals("移动互联")){
            return ITEM_INTERNET;
        }

        if (text.equals("IT资讯")){
            return ITEM_IT;
        }
        return ITEM_INTERNATION;
    }

    /**
     * 对侧边栏按钮进行处理，打开侧边栏
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }

    /**
     * 对返回键进行处理，如果侧边栏打开则关闭侧边栏，否则关闭 activity
     */
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawers();
        }else{
            finish();
        }
    }



}
