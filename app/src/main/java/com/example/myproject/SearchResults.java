package com.example.myproject;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchResults extends AppCompatActivity {

    private List<Store> originalStoreList = new ArrayList<>(); // 전체 데이터
    private List<Store> filteredStoreList = new ArrayList<>(); // 필터링된 데이터
    private SearchResultsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        // RecyclerView와 어댑터 설정
        RecyclerView recyclerView = findViewById(R.id.searchResultsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 더미 데이터 초기화
        initializeStoreData(originalStoreList);

        // 어댑터 생성 및 설정
        filteredStoreList.addAll(originalStoreList); // 초기에는 모든 데이터 표시
        adapter = new SearchResultsAdapter(filteredStoreList);
        recyclerView.setAdapter(adapter);

        // 검색어 처리
        String query = getIntent().getStringExtra("query");
        if (query != null && !query.isEmpty()) {
            filterData(query);
        }
    }

    private void filterData(String query) {
        filteredStoreList.clear(); // 기존 필터링 결과 초기화

        for (Store store : originalStoreList) {
            // 대소문자 구분 없이 검색
            if (store.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredStoreList.add(store);
            }
        }

        // 필터링 결과 갱신
        if (!filteredStoreList.isEmpty()) {
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    // 더미 데이터 생성
    private void initializeStoreData(List<Store> storeList) {
        storeList.add(new Store("고릴라짐", "★ 4.5 (100+)", R.drawable.gym));
        storeList.add(new Store("피트니스넘버원", "★ 4.5 (100+)", R.drawable.gym));
        storeList.add(new Store("백석필라테스", "★ 4.5 (100+)", R.drawable.pila));
        storeList.add(new Store("짱짱필라테스", "★ 4.5 (100+)", R.drawable.pila));
        storeList.add(new Store("송파구체육문화회관", "★ 4.9 (100+)", R.drawable.swim));
        storeList.add(new Store("천안북부스포츠센터", "★ 4.5 (100+)", R.drawable.swim));
        storeList.add(new Store("천안테니스", "★ 4.5 (100+)", R.drawable.tennis));
        storeList.add(new Store("오랑우탄테니스", "★ 4.5 (100+)", R.drawable.tennis));
    }
}
