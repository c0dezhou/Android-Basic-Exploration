package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private final static String CHARACTER ="character";
    private final static String CHINESE ="chinese";
    private final static String STAND ="stand";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] character ={"Giorno","Bucciarati","Abbacchio","Mista","Narancia"};
        String[] chinese={"乔鲁诺","布加拉提","阿帕基","米斯达","纳兰珈"};
        String[] stand={"Gold Experience","Sticky Fingers","Moody Blues","Sex Pistols","Aero Smith"};

        List<Map<String,Object>> items=new ArrayList<Map<String,Object>>();
        for(int i=0;i<character.length;i++){
            Map<String,Object> item=new HashMap<String,Object>();
            item.put(CHARACTER,character[i]);
            item.put(CHINESE,chinese[i]);
            item.put(STAND,stand[i]);
            items.add(item);
        }
        SimpleAdapter adapter =new SimpleAdapter(this,items,R.layout.item,new String[]{CHARACTER,CHINESE,STAND},
                new int[]{R.id.character,R.id.chinese,R.id.stand});

        ListView list =(ListView)findViewById(R.id.list_view);
        list.setAdapter(adapter);

    }
}
