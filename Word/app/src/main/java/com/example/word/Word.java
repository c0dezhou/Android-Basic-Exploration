package com.example.word;

import android.provider.BaseColumns;

public class Word {
    public Word() {
    }

    public static abstract class Words implements BaseColumns {
        public static final String TABLE_NAME="words";
        public static final String COLUMN_NAME_WORD="word";//列：单词
        public static final String COLUMN_NAME_MEANING="meaning";//列：单词含义
        public static final String COLUMN_NAME_SAMPLE="sample";//单词示例


    }
}
