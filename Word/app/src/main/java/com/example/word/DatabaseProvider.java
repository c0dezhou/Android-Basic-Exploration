package com.example.word;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class DatabaseProvider extends ContentProvider {
    public static final int WORDS_DIR = 0;
    public static final int WORDS_ITEM = 1;

    public static final String AUTHORITY = "com.example.word.provider";

    private CreateWordDB dbHelper;

    private static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "words", WORDS_DIR);
        uriMatcher.addURI(AUTHORITY, "words/#", WORDS_ITEM);

    }

    public DatabaseProvider() {
    }

    @Override
    public boolean onCreate() {
        dbHelper = new CreateWordDB(getContext());
        return true;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // 删除数据
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deleteRows = 0;
        switch (uriMatcher.match(uri)) {
            case WORDS_DIR:
                deleteRows = db.delete("words", selection, selectionArgs);
                break;
            case WORDS_ITEM:
                String wordId = uri.getPathSegments().get(1);
                deleteRows = db.delete("words", "id=?", new String[]{Word.Words._ID});
                break;

            default:
                break;
        }
        return deleteRows;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case WORDS_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.word.provider.words";
            case WORDS_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.word.provider.words";

            default:
                return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // 添加数据
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)) {
            case WORDS_DIR:
            case WORDS_ITEM:
                final long newwordsId = db.insert("words", null, values);
                uriReturn = Uri.parse("content://" + AUTHORITY + "/words/" + newwordsId);
                break;

        }
        return uriReturn;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // 查询数据
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case WORDS_DIR:
                cursor = db.query("words", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case WORDS_ITEM:
                String wordId = uri.getPathSegments().get(1);
                cursor = db.query("words", projection, "id=?", new String[]{Word.Words._ID}, null, null, sortOrder);
                break;

            default:
                break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // 更新数据
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        int updateRows = 0;
        switch (uriMatcher.match(uri)) {
            case WORDS_DIR:
                updateRows = db.update("words", values, selection, selectionArgs);
                break;
            case WORDS_ITEM:
                String wordId = uri.getPathSegments().get(1);
                updateRows = db.update("words", values, "id=?", new String[]{Word.Words._ID});
                break;

            default:
                break;
        }
        return updateRows;
    }
}

