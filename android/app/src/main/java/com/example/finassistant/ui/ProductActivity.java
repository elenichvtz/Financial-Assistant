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
import com.example.finassistant.ui.account.ProductPresenter;
import com.example.finassistant.ui.account.ProductView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * The type Product activity.
 */
public class ProductActivity extends AppCompatActivity implements ProductView {
    /**
     * The Price.
     */
    static EditText price;
    /**
     * The Product name.
     */
    EditText product_name;
    /**
     * The Product.
     */
    Product product;
    /**
     * The Addproduct.
     */
    FloatingActionButton addproduct;
    /**
     * The List.
     */
    static ShoppingList list;
    /**
     * The Num of clicks.
     */
    static int numOfClicks = 0;
    /**
     * The Products.
     */
    ArrayList<Product> products = new ArrayList<>();
    /**
     * The Presenter.
     */
    ProductPresenter presenter ;
    /**
     * The Amount.
     */
    static double amount;
    /**
     * The Prname.
     */
    static String prname;
    /**
     * The constant EXTRA_MESSAGE.
     */
    public static final String EXTRA_MESSAGE = "com.example.finassistant.MESSAGE";
    /**
     * The constant RESULT_OK.
     */
    public static final int RESULT_OK = 1;
    /**
     * The Intent.
     */
    Intent intent;

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
        list = new ShoppingList();
        presenter = new ProductPresenter(this);
        intent = new Intent(ProductActivity.this, ListActivity.class);
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

                    product_name.setVisibility(View.VISIBLE);
                    price.setVisibility(View.VISIBLE);

                    product_name = findViewById(R.id.title_product);
                    price = findViewById(R.id.txt_input);
                    prname = product_name.getText().toString();
                    
                }
                else if (numOfClicks%2 == 0){
                    android.app.AlertDialog.Builder info = new android.app.AlertDialog.Builder(ProductActivity.this);
                    info.setTitle("Do you wish to put this product to your list?");

                    info.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            boolean isValidTitle = presenter.validateTitle(product_name.getText().toString());
                            if (isValidTitle) {

                                if (price.getText().toString().equals("")) {
                                    price.setText("0");
                                }
                                amount = Double.parseDouble(price.getText().toString());

                                boolean isValid = presenter.validateAmount(amount);
                                if (isValid) {
                                    product = new Product();
                                    addAmount(amount);

                                    addTitle(product_name.getText().toString());

                                    products.add(product);
                                    product_name.setText("");
                                    price.setText("");
                                }
                                numOfClicks++;
                            }
                            else{
                                numOfClicks--;
                            }
                        }
                    });

                    info.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            android.app.AlertDialog.Builder delete = new android.app.AlertDialog.Builder(ProductActivity.this);
                            delete.setTitle("Are you sure you want to delete the product?");
                            product_name.setText("");
                            price.setText("");
                            numOfClicks--;
                        }
                    });
                    info.show();
                }
            }
        });

        goBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                for(int i=0; i< products.size(); i++){
                    list.addProduct(products.get(i));
                }
                numOfClicks=0;
                String itemValue = "OK";
                intent.putExtra(EXTRA_MESSAGE, itemValue);
                setResult(ProductActivity.RESULT_OK, intent);
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
