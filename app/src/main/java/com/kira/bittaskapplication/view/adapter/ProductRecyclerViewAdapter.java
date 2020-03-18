package com.kira.bittaskapplication.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kira.bittaskapplication.R;
import com.kira.bittaskapplication.model.Product;
import com.kira.bittaskapplication.view.ui.CategoryProductActivity;
import com.kira.bittaskapplication.view.ui.ProductDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ProductViewHolder> {

    private List<Product> products = new ArrayList<>();
    private Context mContext;
    private boolean ischecked = false;

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        this.mContext = parent.getContext();
        return new ProductViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        String productName = this.products.get(position).getName();
        String produtWeight = this.products.get(position).getWeight();
        String productPrice = this.products.get(position).getPrice();
        String productImage = this.products.get(position).getProductImg();
        holder.mProductNameTextView.setText(productName);
        holder.mProductWeightTextView.setText(produtWeight);
        holder.mProductPriceTextView.setText(productPrice);
        Picasso.get().load(productImage).into(holder.mProductImageView);
        holder.mProductCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProductDetailsActivity.class);
                intent.putExtra("productImage", productImage);
                intent.putExtra("productPrice",productPrice);
                intent.putExtra("productWeight",produtWeight);
                intent.putExtra("productName",productName );
                mContext.startActivity(intent);
            }
        });
        holder.mAddTOCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ischecked) {
                    holder.mAddTOCartButton.setImageResource(R.drawable.ic_check_circle);
                   ischecked=true;
                }else {
                    holder.mAddTOCartButton.setImageResource(R.drawable.ic_add_circle);
                    ischecked=false;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setListItem(List<Product> productsList) {
        this.products = productsList;
        notifyDataSetChanged();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView mProductImageView;
        CardView mProductCardView;
        TextView mProductNameTextView;
        TextView mProductWeightTextView;
        TextView mProductPriceTextView;
        ImageButton mAddTOCartButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            mProductImageView = itemView.findViewById(R.id.productImageView);
            mProductNameTextView = itemView.findViewById(R.id.nameTextView);
            mProductWeightTextView = itemView.findViewById(R.id.weightTextView);
            mProductPriceTextView = itemView.findViewById(R.id.priceTextView);
            mAddTOCartButton = itemView.findViewById(R.id.addTOCartBtn);
            mProductCardView = itemView.findViewById(R.id.productItemCardView);


        }
    }


}
