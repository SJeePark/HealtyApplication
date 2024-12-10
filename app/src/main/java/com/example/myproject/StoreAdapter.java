package com.example.myproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class StoreAdapter extends ArrayAdapter<Store> {


    public StoreAdapter(Context context, ArrayList<Store> stores) {
        super(context, 0, stores);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 리스트 아이템이 재사용될 수 있도록 View를 재사용
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list_layout, parent, false);
        }

        // 현재 Store 객체 가져오기
        Store currentStore = getItem(position);

        // XML 파일의 View와 데이터를 연결
        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView storeName = convertView.findViewById(R.id.storeName);
        TextView rating = convertView.findViewById(R.id.rating);


        // 데이터를 View에 설정
        if (currentStore != null) {
            imageView.setImageResource(currentStore.getImageResource());
            storeName.setText(currentStore.getName());
            rating.setText(currentStore.getRating());

        }

        return convertView;
    }

}
