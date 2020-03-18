package com.kira.bittaskapplication.view.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.kira.bittaskapplication.R;
import com.squareup.picasso.Picasso;

public class ProductDetailsActivity extends AppCompatActivity {
ImageView imageView;
TextView productName,productWeight,productPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Intent intent=getIntent();
        imageView=findViewById(R.id.imageView2);
        productName=findViewById(R.id.productName);
        productWeight=findViewById(R.id.productWeight);
        productPrice=findViewById(R.id.productPrice);
        Picasso.get().load(intent.getStringExtra("productImage")).into(imageView);
        productName.setText(intent.getStringExtra("productName"));
        productWeight.setText(intent.getStringExtra("productWeight"));
        productPrice.setText(intent.getStringExtra("productPrice"));

    }
}
