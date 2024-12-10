package com.example.myproject;
// BannerAdapter.java
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder> {
    private List<BannerItem> bannerItems;
    private Context context;

    public BannerAdapter(Context context, List<BannerItem> bannerItems) {
        this.context = context;
        this.bannerItems = bannerItems;
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout_banner, parent, false);
        return new BannerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {
        BannerItem bannerItem = bannerItems.get(position);
        holder.imageView.setImageResource(bannerItem.getImage());
    }

    @Override
    public int getItemCount() {
        return bannerItems.size();
    }

    static class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public BannerViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_banner_image);
        }
    }
}
