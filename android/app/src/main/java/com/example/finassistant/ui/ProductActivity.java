package com.example.finassistant.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.finassistant.R;
import com.example.finassistant.domain.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProductActivity extends AppCompatActivity {
    EditText price;
    EditText product_name;
    static Product product;
    FloatingActionButton addproduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        product_name = findViewById(R.id.title_product);
        addproduct = findViewById(R.id.addproduct);
        price = findViewById(R.id.txt_input);
        product_name.setVisibility(View.VISIBLE);
        price.setVisibility(View.VISIBLE);
        addproduct.setVisibility(View.VISIBLE);
        System.err.println("Inside product activity");
    }

    public void onStart(){
        super.onStart();

        addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!price.getText().toString().isEmpty()) {
                    double amount = Double.parseDouble(price.getText().toString());
                    product = new Product(product_name.getText().toString(), amount);
                    finish();
                    //Intent intent = new Intent(ProductActivity.this, ListActivity.class);

                }
            }
        });


    }


}
