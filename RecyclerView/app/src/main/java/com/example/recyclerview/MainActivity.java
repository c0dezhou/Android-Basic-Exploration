package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Jo> jojolist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initJo();
        RecyclerView recyclerView =(RecyclerView) findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //LinearLayout layoutManager= new LinearLayout(this);
        JoAdapter adapter =new JoAdapter(jojolist);
        recyclerView.setAdapter(adapter);

    }
    private void initJo(){
        for(int i=0;i<2;i++){
            Jo pic1=new Jo(R.drawable.pic1);
            jojolist.add(pic1);
            Jo pic2=new Jo(R.drawable.pic2);
            jojolist.add(pic2);
            Jo pic3=new Jo(R.drawable.pic3);
            jojolist.add(pic3);
            Jo pic4=new Jo(R.drawable.pic4);
            jojolist.add(pic4);
            Jo pic5=new Jo(R.drawable.pic5);
            jojolist.add(pic5);
            Jo pic6=new Jo(R.drawable.pic6);
            jojolist.add(pic6);
            Jo pic7=new Jo(R.drawable.pic7);
            jojolist.add(pic7);
            Jo pic8=new Jo(R.drawable.pic8);
            jojolist.add(pic8);
            Jo pic9=new Jo(R.drawable.pic9);
            jojolist.add(pic9);
            Jo pic10=new Jo(R.drawable.pic10);
            jojolist.add(pic10);
            Jo pic12=new Jo(R.drawable.pic12);
            jojolist.add(pic12);
            Jo pic13=new Jo(R.drawable.pic13);
            jojolist.add(pic13);
            Jo pic14=new Jo(R.drawable.pic14);
            jojolist.add(pic14);
            Jo pic15=new Jo(R.drawable.pic15);
            jojolist.add(pic15);
            Jo pic16=new Jo(R.drawable.pic16);
            jojolist.add(pic16);
            Jo pic17=new Jo(R.drawable.pic17);
            jojolist.add(pic17);
            Jo pic18=new Jo(R.drawable.pic18);
            jojolist.add(pic18);
            Jo pic19=new Jo(R.drawable.pic19);
            jojolist.add(pic19);
            Jo pic20=new Jo(R.drawable.pic20);
            jojolist.add(pic20);
            Jo pic21=new Jo(R.drawable.pic21);
            jojolist.add(pic21);
        }
    }

}

