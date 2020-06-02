package com.example.finassistant.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.finassistant.R;
import com.example.finassistant.domain.Product;
import com.example.finassistant.domain.ShoppingList;
import com.example.finassistant.ui.account.AccountView;
import com.example.finassistant.ui.account.ProductPresenter;
import com.example.finassistant.ui.account.ProductView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity implements ProductView {
    EditText price;
    EditText product_name;
    Product product;
    FloatingActionButton addproduct;
    ShoppingList list;
    static int numOfClicks = 0;
    ArrayList<Product> products = new ArrayList<>();
    //ProductPresenter presenter = new ProductPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        product_name = findViewById(R.id.title_product);
        addproduct = findViewById(R.id.addproduct);
        price = findViewById(R.id.txt_input);
        product_name.setVisibility(View.GONE);
        price.setVisibility(View.GONE);
        addproduct.setVisibility(View.VISIBLE);
        System.err.println("Inside product activity");
        list = new ShoppingList();
    }

    public void onStart(){
        super.onStart();
        final FloatingActionButton goBack = findViewById(R.id.goBack);
        final Button submit = findViewById(R.id.submit);
        submit.setVisibility(View.GONE);



        addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numOfClicks++;
                if(numOfClicks%2 != 0) {
                    product = new Product();
                    product_name.setVisibility(View.VISIBLE);
                    price.setVisibility(View.VISIBLE);
                    //submit.setVisibility(View.VISIBLE);
                    //addproduct.setVisibility(View.GONE);
                    System.err.println("Inside add product");
                    System.err.println(price.getText().toString());
                    if (!price.getText().toString().isEmpty()) {
                        String title = product_name.getText().toString();
                        addTitle(title);
                        double amount = Double.parseDouble(price.getText().toString());
                        addAmount(amount);
                        System.err.println("priceee " + product.getPrice());
                        //list.addProduct(product);
                        products.add(product);
                        System.err.println("List products: "+products.toString());
                        //numOfClicks++;
                        //product = new Product(product_name.getText().toString(), amount);

                    /*submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            list.addProduct(product);
                            product_name.setText("");
                            price.setText("");

                        }
                    });*/
                        //finish();
                        //Intent intent = new Intent(ProductActivity.this, ListActivity.class);

                    }
                }else if (numOfClicks%2 == 0){
                    android.app.AlertDialog.Builder info = new android.app.AlertDialog.Builder(ProductActivity.this);
                    info.setTitle("Details");
                    info.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            product_name.setText("");
                            price.setText("");
                            numOfClicks++;
                        }
                    });
                    info.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            android.app.AlertDialog.Builder delete = new android.app.AlertDialog.Builder(ProductActivity.this);
                            delete.setTitle("Are you sure you want to delete the list?");

                            products.remove(products.size()-1);


                        }
                    });
                    info.show();
                }
            }
        });

        goBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });


    }

    public void addAmount(Double amount){
        product.setPrice(amount);

    }

    public void addTitle(String title){
        product.setTitle(title);
    }

    public void showErrorMessage(String title,String message){
        new AlertDialog.Builder(ProductActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }


}
