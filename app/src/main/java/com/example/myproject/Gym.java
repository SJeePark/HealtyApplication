package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Gym extends BaseActivity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_list_layout);
        TextView tvAddress = findViewById(R.id.tv_address);
        tvAddress.setText("헬스장");
        setPersonalButtonClickListener(R.id.imageView);

        ArrayList<Store> stores = new ArrayList<>();
        stores.add(new Store("고릴라짐", "★ 4.5 (100+)", R.drawable.gorilla));
        stores.add(new Store("피트니스넘버원", "★ 4.5 (100+)", R.drawable.number1));


        StoreAdapter adapter = new StoreAdapter(this, stores);

        // ListView와 어댑터 연결
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);


        //상세페이지로 넘어감
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Store clickedStore = stores.get(i);
                Intent intent = new Intent(getApplicationContext(), Gym_detail1.class);
                intent.putExtra("STORE_NAME", clickedStore.getName());  // 헬스장 이름 전달
                startActivity(intent);
            }
        });

//        setupDropdownMenu("gym");
    }

}
