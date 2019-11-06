package com.example.word;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YDUtil {
    private static final String key = "webKey";
    private static final String value = "webValue";

        public static YDWord parseJsonWithGson(String jsonString)throws JSONException {
            Gson gson = new Gson();
            YDWord youDaoWord = new YDWord();

            // 将JSON转换为对象
            JSONObject jsonObject = new JSONObject(jsonString);

            youDaoWord.setQuery(jsonObject.getString("query"));
            youDaoWord.setTranslation(jsonObject.getString("translation"));

            // 因为web属性返回来是一个列表，所以放在List中，方便在ListView中显示
            List<Map<String, String>> listMap = new ArrayList<>();
            JSONArray jsonArray = jsonObject.getJSONArray("web");
            for(int i=0; i<jsonArray.length(); i++){
                Map<String, String> map = new HashMap<>();
                map.put(key, jsonArray.getJSONObject(i).getString("key"));
                map.put(value, jsonArray.getJSONObject(i).getString("value"));
                listMap.add(map);
            }
            youDaoWord.setWebs(listMap);

            String basicString = jsonObject.getString("basic");
            jsonObject = new JSONObject(basicString);
            String[] phonetics = new String[3];
            phonetics[0] = jsonObject.getString("us-phonetic");
            phonetics[1] = jsonObject.getString("uk-phonetic");
            youDaoWord.setPhonetics(phonetics);

            // 将所有的explain的值取出
            jsonArray = jsonObject.getJSONArray("explains");
            List<String> list = new ArrayList<>();
            for(int i=0; i<jsonArray.length(); i++){
                list.add(jsonArray.get(i).toString());
            }
            youDaoWord.setExplains(list);

            return youDaoWord;
        }



}
