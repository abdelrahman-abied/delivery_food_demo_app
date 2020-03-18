package com.kira.bittaskapplication.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.kira.bittaskapplication.R;
import com.kira.bittaskapplication.model.Product;
import com.kira.bittaskapplication.view.adapter.ProductRecyclerViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryProductActivity extends AppCompatActivity {
    private AppBarLayout appBarLayout;
    private ImageView imageView;
    private Toolbar toolbar;
    private TextView appbar_title;
    private String mUrl, mCategoryName;
    private RecyclerView recyclerView;
    ProductRecyclerViewAdapter productRecyclerViewAdapter;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_product);
        appBarLayout = findViewById(R.id.appbar);
        imageView = findViewById(R.id.backdrop);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.productRecycleView);
        productRecyclerViewAdapter = new ProductRecyclerViewAdapter();
        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");
        appBarLayout = findViewById(R.id.appbar);

        toolbar.setCollapsible(false);

        appbar_title = findViewById(R.id.title_on_appbar);
        Intent intent = getIntent();
        mUrl = intent.getStringExtra("URL");
        mCategoryName = intent.getStringExtra("categoryName");
        List<Product> productsList=intent.getParcelableArrayListExtra("productList");
        Picasso.get().load(mUrl).into(imageView);
        getSupportActionBar().setTitle(mCategoryName);
        appbar_title.setText(mCategoryName);

        productRecyclerViewAdapter.setListItem(productsList);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(productRecyclerViewAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_menu, menu);
        return true;
    }
}
