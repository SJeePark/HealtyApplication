package com.example.myproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>{
    private final List<Store> data;

    public SearchResultsAdapter(List<Store> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // store_item.xml을 아이템 레이아웃으로 사용
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // 현재 아이템 가져오기
        Store store = data.get(position);

        // 데이터 바인딩
        holder.storeName.setText(store.getName());
        holder.rating.setText(store.getRating());
        holder.imageView.setImageResource(store.getImageResource());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    // ViewHolder 클래스
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView storeName;
        public TextView rating;
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            storeName = itemView.findViewById(R.id.storeName);
            rating = itemView.findViewById(R.id.rating);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
