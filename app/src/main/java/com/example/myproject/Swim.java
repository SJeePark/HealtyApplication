package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Swim extends MainActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_list_layout);
        TextView tvAddress = findViewById(R.id.tv_address);
        tvAddress.setText("수영");

        ArrayList<Store> stores = new ArrayList<>();
        stores.add(new Store("송파구체육문화회관", "★ 4.9 (100+)", R.drawable.swim));
        stores.add(new Store("천안북부스포츠센터", "★ 4.5 (100+)", R.drawable.swim));

        StoreAdapter adapter = new StoreAdapter(this, stores);

        // ListView와 어댑터 연결
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Store clickedStore = stores.get(i);
                Intent intent = new Intent(getApplicationContext(), Swim_detail.class);
                intent.putExtra("STORE_NAME", clickedStore.getName());  // 업체 이름 전달
                startActivity(intent);
            }
        });
    }
}
