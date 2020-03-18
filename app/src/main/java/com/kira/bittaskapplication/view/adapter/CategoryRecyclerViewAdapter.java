package com.kira.bittaskapplication.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kira.bittaskapplication.R;
import com.kira.bittaskapplication.model.Category;
import com.kira.bittaskapplication.model.Product;
import com.kira.bittaskapplication.view.ui.CategoryProductActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.CategoryViewHolder> {

    private List<Category> categories = new ArrayList<>();
    private Context mContext;

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext=parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, final int position) {
        String imageUrl = categories.get(position).getCategoryImg();
        String categoryName=categories.get(position).getName();
        Picasso.get().load(imageUrl)
                .into(holder.mImageView);
        holder.mTextView.setText(categoryName);
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Product> products = categories.get(position).getProducts();
                Intent intent = new Intent(mContext, CategoryProductActivity.class);
                intent.putExtra("URL", imageUrl);
                intent.putExtra("categoryName",categoryName );
                intent.putParcelableArrayListExtra("productList", (ArrayList<? extends Parcelable>) products);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void setListItem(List<Category> listItem) {
        this.categories = listItem;
        notifyDataSetChanged();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        CardView mCardView;
        TextView mTextView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.categoryImageView);
            mCardView = itemView.findViewById(R.id.cardview_id);
            mTextView = itemView.findViewById(R.id.categoryNameTextView);
        }
    }


}
