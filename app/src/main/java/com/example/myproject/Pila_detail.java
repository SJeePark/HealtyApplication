package com.example.myproject;

import android.os.Bundle;
import android.widget.TextView;

public class Pila_detail extends Pila{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_detail_layout);
        TextView tvDetail1 = findViewById(R.id.detailTv1);
        TextView tvDetail2 = findViewById(R.id.detailTv2);
        String storeName = getIntent().getStringExtra("STORE_NAME"); // key를 사용해 데이터를 꺼냄
        // 텍스트뷰에 동적으로 데이터 표시
        tvDetail1.setText(storeName); // 헬스장 이름 표시
        tvDetail2.setText(storeName);
    }

}
