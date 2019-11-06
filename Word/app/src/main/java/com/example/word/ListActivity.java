package com.example.word;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.SQLException;
import android.graphics.Color;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//public class ListActivity extends AppCompatActivity {
//
//    private CreateWordDB createWordDB;
//    //接收进入和退出DetailActivity的时间
//    static long ttime1 = 0;
//    static long ttime2 = 0;
//
//    //用于记录单词条数
//    int count = 0;
//    //用于记录被点击的行号
//    String number;
//
//    ListView listView;
//
//    //用于记录被点击的单词的标题、来源、时间、内容
////    static String myTitle;
////    static String mySource;
////    static String myTime;
////    static String myContent;
//
//    static String Id;
//    static String wordWord;
//    static String wordExplain;
//    static String wordSample;
//
//    //用于承装浏览过的单词ID
//    static List<String> lstID = new ArrayList<String>();
//    static List<Word> lstWord = new ArrayList<Word>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
//        setContentView(R.layout.activity_list);
//
//
//        //创建一个“AlRead”文件，现在里面放一个“，”
//        SharedPreferences.Editor editor = getSharedPreferences("AlRead", MODE_PRIVATE).edit();
//        editor.putString("data", ",");
//        editor.apply();
//
////        ListView list = (ListView) findViewById(R.id.listview_wordWord);
////        registerForContextMenu(list);
//
//        //List<Map<String, String>> items=getAll();
//        //setWordsListView(items);
//
//        //创建数据库
//        createWordDB = new CreateWordDB(this, "WORDS.db", null, 2);
//        SQLiteDatabase db = createWordDB.getWritableDatabase();
//        //插入数据
////        ContentValues values = new ContentValues();
////        values.put("wordWord", "add");
////        values.put("wordExplain", "增加");
////        values.put("wordSample", "add it to milk.");
////
////        db.insert("WordTable", null, values);
////        values.clear();
////
////        values.put("wordWord", "update");
////        values.put("wordExplain", "更新");
////        values.put("wordSample", "update it");
////
////        db.insert("WordTable", null, values);
////        values.clear();
//
//
//        //lstWord用于承载每条单词
//        final List<Word> lstWord = new ArrayList<Word>();
//        //游标查询数据库内容
//        Cursor cursor = db.query("WordTable", null, null, null, null, null, null);
//        //获取总共多少条单词
//        count = cursor.getCount();
//        int i = 0;
//        //单词数组
//        final Word[] word = new Word[count];
//        if (cursor.moveToFirst()) {
//            do {
//                //获取数据值
//                int id = cursor.getInt(cursor.getColumnIndex("nlID"));
//                String wordWord = cursor.getString(cursor.getColumnIndex("wordWord"));
//                String wordExplain = cursor.getString(cursor.getColumnIndex("wordExplain"));
//                String wordSample = cursor.getString(cursor.getColumnIndex("wordSample"));
//
//
//                //将每组值重组为一则单词word[i]
//                word[i] = new Word();
//                word[i].setId(String.valueOf(id));
//                word[i].setWordWord(wordWord);
//                word[i].setWordExplain(wordExplain);
//                word[i].setWordSample(wordSample);
//
//                //将每条单词存入lstWord
//                lstWord.add(word[i]);
//                i++;
//            } while (cursor.moveToNext());
//        }
//
//
//
//
//        //借用适配器ArrayAdapter来将数据传入ListView
//
//
//
//        MyAdapter adapter = new MyAdapter(ListActivity.this, R.layout.word_item, lstWord);
//
//        listView = (ListView) findViewById(R.id.listview_wordWord);
//
//
//        listView.setAdapter(adapter);
//        listView.setOnCreateContextMenuListener(listviewLongPress);
//
//
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Configuration mConfiguration = ListActivity.this.getResources().getConfiguration();
//                int ori = mConfiguration.orientation;
//                if (ori == mConfiguration.ORIENTATION_LANDSCAPE) {
//                    Toast.makeText(ListActivity.this, "横屏", Toast.LENGTH_SHORT).show();
//
//                    wordWord = word[position].getWordWord();
//                    wordExplain = word[position].getWordExplain();
//                    wordSample = word[position].getWordSample();
//
//                    replaceFragment(new DetailFragment());
//                } else if (ori == mConfiguration.ORIENTATION_PORTRAIT) {
//                    //用Intent传输数据到DetailActivity
//                    Toast.makeText(ListActivity.this, "竖屏", Toast.LENGTH_SHORT).show();
//
//                    Intent intent = new Intent();
//                    intent.setClassName(ListActivity.this, "com.example.word.DetailActivity");
//                    number = word[position].getId();
//                    intent.putExtra("wordWord", word[position].getWordWord());
//                    intent.putExtra("wordExplain", word[position].getWordExplain());
//                    intent.putExtra("wordSample", word[position].getWordSample());
//
//                    startActivityForResult(intent, 1);
//                }
//            }
//
//            public void replaceFragment(Fragment fragment) {
//                //得到一个碎片管理器的实例
//                FragmentManager manager = getSupportFragmentManager();
//                //得到一个碎片事务的实例并开启这个事务
//                FragmentTransaction transaction = manager.beginTransaction();
//                //将容器中的Fragment替换为指定的Fragment
//                transaction.replace(R.id.detail_fragment, fragment);
//                //添加回退栈
//                transaction.addToBackStack(null);
//                //提交事务
//                transaction.commitAllowingStateLoss();
//            }
//        });
//
////        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
////            @Override
////            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
////                final CharSequence[] items={"修改","删除"};
////                final TextView textId = null;
////                final TextView textWord = null;
////                final TextView textMeaning = null;
////                final TextView textSample = null;
////                //final ListView v1=(ListView)parent;
////                //final Cursor c1=(Cursor)v1.getItemAtPosition(position);
////
////
////                android.app.AlertDialog.Builder bulder=new android.app.AlertDialog.Builder(ListActivity.this);
////                bulder.setTitle("操作")
////                        .setItems(items, new DialogInterface.OnClickListener() {
////                            @Override
////                            public void onClick(DialogInterface dialog, int which) {
////                                SQLiteDatabase db=createWordDB.getWritableDatabase();
////
////                                switch (which){
////                                    case 0:{
////
////                                        android.app.AlertDialog.Builder builder2 =new android.app.AlertDialog.Builder(ListActivity.this);
////                                        LayoutInflater inflater=getLayoutInflater();
////                                        builder2.setView(inflater.inflate(R.layout.insert,null))
////                                                .setTitle("修改")
////                                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
////                                                    @Override
////                                                    public void onClick(DialogInterface dialog, int which) {
////
////                                                        //修改操作
////                                                    }
////                                                })
////                                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
////                                                    @Override
////                                                    public void onClick(DialogInterface dialog, int which) {
////                                                        //取消操作
////                                                    }
////                                                });
////                                        builder2.show();
////                                    }
////                                    break;
////                                    case 1:{
////
////                                        db.execSQL("delete from WordTable where nlID=1");
////                                        db.close();
////                                        Toast.makeText(ListActivity.this,"删除",Toast.LENGTH_SHORT).show();
////                                    }break;
////
////                                }
////                            }
////                        });
////                bulder.show();
////                Toast.makeText(ListActivity.this,"yes",Toast.LENGTH_SHORT).show();
////                return true;
////            }
////        });
//
//
//    }
//
//    View.OnCreateContextMenuListener listviewLongPress = new View.OnCreateContextMenuListener() {
//        @Override
//        public void onCreateContextMenu(ContextMenu menu, View v,
//                                        ContextMenu.ContextMenuInfo menuInfo) {
//            // TODO Auto-generated method stub
//            final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
//            new AlertDialog.Builder(ListActivity.this)
//                    /* 弹出窗口的最上头文字 */
//                    .setTitle("删除当前数据")
//                    /* 设置弹出窗口的图式 */
//                    .setIcon(android.R.drawable.ic_dialog_info)
//                    /* 设置弹出窗口的信息 */
//                    .setMessage("确定删除当前记录")
//                    .setPositiveButton("是",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(
//                                        DialogInterface dialoginterface, int i) {
//                                    // 获取位置索引
//                                    int mListPos = info.position;
//                                    // 获取对应HashMap数据内容
//                                    HashMap<String, Object> map = lstWord
//                                            .get(mListPos);
//                                    // 获取id
//                                    int id = Integer.valueOf((map.get("id")
//                                            .toString()));
//                                    // 获取数组具体值后,可以对数据进行相关的操作,例如更新数据
//                                    if (dao.delete(mDb, "student", id)) {
//                                        // 移除listData的数据
//                                        lst.remove(mListPos);
//                                        listItemAdapter.notifyDataSetChanged();
//                                    }
//                                }
//                            })
//                    .setNegativeButton("否",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(
//                                        DialogInterface dialoginterface, int i) {
//                                    // 什么也没做
//
//                                }
//                            }).show();
//        }
//    };
//
//
//    private void ShowListViewDate(){
//        List<Word> lstWordview = new ArrayList<Word>();
//        MyAdapter adapter = new MyAdapter(ListActivity.this, R.layout.word_item, lstWordview);
//
//        listView = (ListView) findViewById(R.id.listview_wordWord);
//        listView.setAdapter(adapter);
//    }
//
//    // 删除一条数据
//    public boolean delete(SQLiteDatabase mDb, String table, String wordWord) {
//        String whereClause = "wordWord=?";
//        String[] whereArgs = new String[] { String.valueOf(wordWord) };
//        try {
//            mDb.delete(table, whereClause, whereArgs);
//        } catch (SQLException e) {
//            Toast.makeText(getApplicationContext(), "删除数据库失败",
//                    Toast.LENGTH_LONG).show();
//            return false;
//        }
//        return true;
//    }
//}
//



public class ListActivity extends AppCompatActivity {
    CreateWordDB mDbHelper;

    static String wordWord;
    static String wordExplain;
    static String wordSample;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        setContentView(R.layout.activity_list);

        //为ListView注册上下文菜单
        ListView list = (ListView) findViewById(R.id.listview_wordWord);
        registerForContextMenu(list);


        //创建SQLiteOpenHelper对象，注意第一次运行时，此时数据库并没有被创建
        mDbHelper = new CreateWordDB(ListActivity.this);

        //在列表显示全部单词
        final List<Map<String, String>> items=getAll();
        setWordsListView(items);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView wordWord=(TextView) view.findViewById(R.id.word_Word);
                //wordWord.getText();
                TextView wordExplain=(TextView) view.findViewById(R.id.word_Explain);
                //wordExplain.getText();
                TextView wordSample=(TextView) view.findViewById(R.id.word_Sample);
                //wordSample.getText();


                Configuration mConfiguration = ListActivity.this.getResources().getConfiguration();
                int ori = mConfiguration.orientation;
                if (ori == mConfiguration.ORIENTATION_LANDSCAPE) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    Fragment fragment = new DetailFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("Word", wordWord.getText().toString());
                    bundle.putString("Explain", wordExplain.getText().toString());
                    bundle.putString("Sample", wordSample.getText().toString());
                    fragment.setArguments(bundle);
                    transaction.add(R.id.detail_fragment, fragment);
                    transaction.show(fragment);
                    transaction.commit();

                    String wordtext;
                    String explaintext;
                    String sampletext;
                    Toast.makeText(ListActivity.this, "横屏", Toast.LENGTH_SHORT).show();

                    replaceFragment(fragment);
                } else if (ori == mConfiguration.ORIENTATION_PORTRAIT) {
                    //用Intent传输数据到DetailActivity

                    Toast.makeText(ListActivity.this, "竖屏", Toast.LENGTH_SHORT).show();

                    Intent intent ;

                    intent = new Intent(ListActivity.this, DetailActivity.class);


                    String number = Word.Words._ID;
                    intent.putExtra("wordWord",wordWord.getText() );
                    intent.putExtra("wordExplain", wordExplain.getText());
                    intent.putExtra("wordSample", wordSample.getText());

                    startActivityForResult(intent, 1);
                }
            }

            public void replaceFragment(Fragment fragment) {
                //得到一个碎片管理器的实例
                FragmentManager manager = getSupportFragmentManager();
                //得到一个碎片事务的实例并开启这个事务
                FragmentTransaction transaction = manager.beginTransaction();
                //将容器中的Fragment替换为指定的Fragment
                transaction.replace(R.id.detail_fragment, fragment);
                //添加回退栈
                transaction.addToBackStack(null);
                //提交事务
                transaction.commitAllowingStateLoss();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDbHelper.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_search:
                //查找
                SearchDialog();
                return true;
            case R.id.action_insert:
                //新增单词
                InsertDialog();
                return true;
            case R.id.action_youdaosearch:{
                Intent intent = new Intent(this, YouDaoSent.class);

                startActivity(intent);

            }break;
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
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.wordlistview, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        TextView textId=null;
        TextView textWord=null;
        TextView textMeaning=null;
        TextView textSample=null;

        AdapterView.AdapterContextMenuInfo info=null;
        View itemView=null;

        switch (item.getItemId()){
            case R.id.action_delete:
                //删除单词
                info=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
                itemView=info.targetView;
                textId =(TextView)itemView.findViewById(R.id.textId);
                if(textId!=null){
                    String strId=textId.getText().toString();
                    DeleteDialog(strId);
                }
                break;
            case R.id.action_update:
                //修改单词
                info=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
                itemView=info.targetView;
                textId =(TextView)itemView.findViewById(R.id.textId);
                textWord =(TextView)itemView.findViewById(R.id.word_Word);
                textMeaning =(TextView)itemView.findViewById(R.id.word_Explain);
                textSample =(TextView)itemView.findViewById(R.id.word_Sample);
                if(textId!=null && textWord!=null && textMeaning!=null && textSample!=null){
                    String strId=textId.getText().toString();
                    String strWord=textWord.getText().toString();
                    String strMeaning=textMeaning.getText().toString();
                    String strSample=textSample.getText().toString();
                    UpdateDialog(strId, strWord, strMeaning, strSample);
                }
                break;
        }
        return true;
    }

    /**
     * 设置适配器，在列表中显示单词
     * */
    private void setWordsListView(List<Map<String, String>> items){
        SimpleAdapter adapter = new SimpleAdapter(this, items, R.layout.word_item,
                new String[]{Word.Words._ID,Word.Words.COLUMN_NAME_WORD,Word.Words.COLUMN_NAME_MEANING,Word.Words.COLUMN_NAME_SAMPLE},
                new int[]{R.id.textId,R.id.word_Word,R.id.word_Explain,R.id.word_Sample});

        ListView list = (ListView) findViewById(R.id.listview_wordWord);
        list.setAdapter(adapter);
    }





    /**
     * 获得所有的单词
     * */
    private List<Map<String, String>> getAll() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                Word.Words._ID,
                Word.Words.COLUMN_NAME_WORD,
                Word.Words.COLUMN_NAME_MEANING,
                Word.Words.COLUMN_NAME_SAMPLE
        };

        //排序
        String sortOrder =
                Word.Words.COLUMN_NAME_WORD + " DESC";

        //查询
        Cursor c = db.query(
                Word.Words.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        return ConvertCursor2List(c);
    }


    /**
     * 将Cursor对象转换为List对象，从而能够在ListView中显示
     * */
    private ArrayList<Map<String, String>> ConvertCursor2List(Cursor cursor) {
        ArrayList<Map<String, String>> result = new ArrayList<>();
        while (cursor.moveToNext()) {
            Map<String, String> map = new HashMap<>();
            map.put(Word.Words._ID, String.valueOf(cursor.getInt(0)));
            map.put(Word.Words.COLUMN_NAME_WORD, cursor.getString(1));
            map.put(Word.Words.COLUMN_NAME_MEANING, cursor.getString(2));
            map.put(Word.Words.COLUMN_NAME_SAMPLE, cursor.getString(3));
            result.add(map);
        }
        return result;
    }

    /**
     * 使用Sql语句向数据库中插入单词
     * @param strWord 单词
     * @param strMeaning  单词含义
     * @param strSample 单词例句
     * */
    private void InsertUserSql(String strWord, String strMeaning, String strSample){
        String sql="insert into  words(word,meaning,sample) values(?,?,?)";

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.execSQL(sql,new String[]{strWord,strMeaning,strSample});
    }

    /**
     * 使用Insert方法向数据库中插入单词
     * @param strWord 单词
     * @param strMeaning  单词含义
     * @param strSample 单词例句
     * */
    private void Insert(String strWord, String strMeaning, String strSample) {

        //Gets the data repository in write mode*/
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Word.Words.COLUMN_NAME_WORD, strWord);
        values.put(Word.Words.COLUMN_NAME_MEANING, strMeaning);
        values.put(Word.Words.COLUMN_NAME_SAMPLE, strSample);

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                Word.Words.TABLE_NAME,
                null,
                values);
    }


    /**
     * 新增对话框
     * */
    private void InsertDialog() {
        final TableLayout tableLayout = (TableLayout) getLayoutInflater().inflate(R.layout.insert, null);
        new AlertDialog.Builder(this)
                .setTitle("新增单词")//标题
                .setView(tableLayout)//设置视图
                //确定按钮及其动作
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String strWord=((EditText)tableLayout.findViewById(R.id.txtWord)).getText().toString();
                        String strMeaning=((EditText)tableLayout.findViewById(R.id.txtMeaning)).getText().toString();
                        String strSample=((EditText)tableLayout.findViewById(R.id.txtSample)).getText().toString();

                        //既可以使用Sql语句插入，也可以使用使用insert方法插入
                         InsertUserSql(strWord, strMeaning, strSample);
                        //Insert(strWord, strMeaning, strSample);

                        List<Map<String, String>> items=getAll();
                        setWordsListView(items);

                    }
                })
                //取消按钮及其动作
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create()//创建对话框
                .show();//显示对话框


    }
    //使用Sql语句删除单词
    private void DeleteUseSql(String strId) {
        String sql="delete from words where _id='"+strId+"'";

        //Gets the data repository in write mode*/
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        db.execSQL(sql);
    }

    //删除单词
    private void Delete(String strId) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        // 定义where子句
        String selection = Word.Words._ID + " = ?";

        // 指定占位符对应的实际参数
        String[] selectionArgs = {strId};

        // Issue SQL statement.
        db.delete(Word.Words.TABLE_NAME, selection, selectionArgs);
    }


    //删除对话框
    private void DeleteDialog(final String strId){
        new AlertDialog.Builder(this).setTitle("删除单词").setMessage("是否真的删除单词?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //既可以使用Sql语句删除，也可以使用使用delete方法删除
                DeleteUseSql(strId);
                //Delete(strId);
                setWordsListView(getAll());
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).create().show();
    }

    //使用Sql语句更新单词
    private void UpdateUseSql(String strId,String strWord, String strMeaning, String strSample) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String sql="update words set word=?,meaning=?,sample=? where _id=?";
        db.execSQL(sql, new String[]{strWord, strMeaning, strSample,strId});
    }

    //使用方法更新
    private void Update(String strId,String strWord, String strMeaning, String strSample) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(Word.Words.COLUMN_NAME_WORD, strWord);
        values.put(Word.Words.COLUMN_NAME_MEANING, strMeaning);
        values.put(Word.Words.COLUMN_NAME_SAMPLE, strSample);

        String selection = Word.Words._ID + " = ?";
        String[] selectionArgs = {strId};

        int count = db.update(
                Word.Words.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    //修改对话框
    private void UpdateDialog(final String strId, final String strWord, final String strMeaning, final String strSample) {
        final TableLayout tableLayout = (TableLayout) getLayoutInflater().inflate(R.layout.insert, null);
        ((EditText)tableLayout.findViewById(R.id.txtWord)).setText(strWord);
        ((EditText)tableLayout.findViewById(R.id.txtMeaning)).setText(strMeaning);
        ((EditText)tableLayout.findViewById(R.id.txtSample)).setText(strSample);
        new AlertDialog.Builder(this)
                .setTitle("修改单词")//标题
                .setView(tableLayout)//设置视图
                //确定按钮及其动作
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String strNewWord = ((EditText) tableLayout.findViewById(R.id.txtWord)).getText().toString();
                        String strNewMeaning = ((EditText) tableLayout.findViewById(R.id.txtMeaning)).getText().toString();
                        String strNewSample = ((EditText) tableLayout.findViewById(R.id.txtSample)).getText().toString();

                        //既可以使用Sql语句更新，也可以使用update方法更新
                        UpdateUseSql(strId, strNewWord, strNewMeaning, strNewSample);
                        //  Update(strId, strNewWord, strNewMeaning, strNewSample);
                        setWordsListView(getAll());
                    }
                })
                //取消按钮及其动作
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create()//创建对话框
                .show();//显示对话框


    }

    //使用Sql语句查找
    private ArrayList<Map<String, String>> SearchUseSql(String strWordSearch) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String sql="select * from words where word like ? order by word desc";
        Cursor c=db.rawQuery(sql,new String[]{"%"+strWordSearch+"%"});

        return ConvertCursor2List(c);
    }

    //使用query方法查找
    private List<Map<String, String>> Search(String strWordSearch) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                Word.Words._ID,
                Word.Words.COLUMN_NAME_WORD,
                Word.Words.COLUMN_NAME_MEANING,
                Word.Words.COLUMN_NAME_SAMPLE
        };

        String sortOrder =
                Word.Words.COLUMN_NAME_WORD + " DESC";

        String selection = Word.Words.COLUMN_NAME_WORD + " LIKE ?";
        String[] selectionArgs = {"%"+strWordSearch+"%"};

        Cursor c = db.query(
                Word.Words.TABLE_NAME,  // The table to query
                projection,             // The columns to return
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,           // don't group the rows
                null,            // don't filter by row groups
                sortOrder               // The sort order
        );

        return ConvertCursor2List(c);
    }

    //查找对话框
    private void SearchDialog() {
        final TableLayout tableLayout = (TableLayout) getLayoutInflater().inflate(R.layout.serchterm, null);
        new AlertDialog.Builder(this)
                .setTitle("查找单词")//标题
                .setView(tableLayout)//设置视图
                //确定按钮及其动作
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String txtSearchWord=((EditText)tableLayout.findViewById(R.id.txtSearchWord)).getText().toString();

                        ArrayList<Map<String, String>> items=null;
                        //既可以使用Sql语句查询，也可以使用方法查询
                        items=SearchUseSql(txtSearchWord);
                         //items= (ArrayList<Map<String, String>>) Search(txtSearchWord);

                        if(items.size()>0) {
                            Bundle bundle=new Bundle();
                            bundle.putSerializable("result",items);
                            Intent intent=new Intent(ListActivity.this,SearchActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }else
                            Toast.makeText(ListActivity.this,"没有找到", Toast.LENGTH_LONG).show();


                    }
                })
                //取消按钮及其动作
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create()//创建对话框
                .show();//显示对话框

    }


}


