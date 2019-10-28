package com.example.word;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

//public class CreateWordDB extends SQLiteOpenHelper {
//
//    public static final String CREATE_WORDS="create table words(" +
//            "nlID integer primary key autoincrement," +
//            "word text," +
//            "explain text," +
//            "sample text)";
//
//    private Context mContex;
//    public CreateWordDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//        //若相同名字的数据库已经存在则先删除
//        if(getDatabaseName()==name)
//        {
//            context.deleteDatabase(name);
//        }
//        mContex=context;
//    }
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(CREATE_WORDS);
//        db.execSQL("insert into WordTable values(1,'add','增加','add it');");
//        db.execSQL("insert into WordTable values(2,'update','修改','update it');");
//        Toast.makeText(mContex,"create succeeded",Toast.LENGTH_SHORT).show();
//    }
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//        db.execSQL("drop table if exists WordTable");
//        onCreate(db);
//    }
//}

public class CreateWordDB extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "WORDS.db";//数据库名字
    private final static int DATABASE_VERSION = 1;//数据库版本

    //建表SQL
    private final static String SQL_CREATE_DATABASE = "CREATE TABLE " + Word.Words.TABLE_NAME + " (" +
            Word.Words._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            Word.Words.COLUMN_NAME_WORD + " TEXT," +
            Word.Words.COLUMN_NAME_MEANING + " TEXT," +
            Word.Words.COLUMN_NAME_SAMPLE + " TEXT)";

    //删表SQL
    private final static String SQL_DELETE_DATABASE = "DROP TABLE IF EXISTS " + Word.Words.TABLE_NAME;

    public CreateWordDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据库
        db.execSQL(SQL_CREATE_DATABASE);
        db.execSQL("insert into  words(word,meaning,sample) values('add','增加','Please add it to milk')");
        db.execSQL("insert into  words(word,meaning,sample) values('fix','使固定; 安装; 解决方法;','It is fixed on the wall')");
        db.execSQL("insert into  words(word,meaning,sample) values('manage','管理；经营；做成；','He managed the company when his father was away.')");
        db.execSQL("insert into  words(word,meaning,sample) values('direct','直接的；笔直的；坦白的','I am in direct contact with the hijackers.')");
        db.execSQL("insert into  words(word,meaning,sample) values('corrupt','腐败的；堕落的；讹误的','She wrote a book declaiming against our corrupt society.')");
        db.execSQL("insert into  words(word,meaning,sample) values('pure','纯的；纯洁的；纯粹的','Water can be made pure by distilling it.')");
        db.execSQL("insert into  words(word,meaning,sample) values('batch','一批；一炉；一次所制之量','The second batch of sugar is better than the first.')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //当数据库升级时被调用，首先删除旧表，然后调用OnCreate()创建新表
        sqLiteDatabase.execSQL(SQL_DELETE_DATABASE);
        onCreate(sqLiteDatabase);
    }
}