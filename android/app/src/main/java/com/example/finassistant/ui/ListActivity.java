package com.example.finassistant.ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finassistant.R;
import com.example.finassistant.domain.Product;
import com.example.finassistant.domain.ShoppingList;
import com.example.finassistant.ui.account.AccountPresenter;
import com.example.finassistant.ui.account.AccountView;

import java.util.ArrayList;
import java.util.Iterator;

public class ListActivity extends AppCompatActivity implements ListView  {

    Spinner myList;
    ListPresenter presenter;
    ShoppingList list;
    ArrayList<ShoppingList> sList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        presenter = new ListPresenter(this);
    }

    public void onStart() {
        super.onStart();
        myList = findViewById(R.id.listView);

        Iterator<ShoppingList> iterator = presenter.getAccount().getShoppingList().iterator();
        while(iterator.hasNext()) {
            sList.add(iterator.next());
        }

        final ArrayAdapter arrayAdapter = new ArrayAdapter(ListActivity.this, android.R.layout.simple_list_item_1, sList);
        myList.setAdapter(arrayAdapter);
    }

    @Override
    public void addProduct(Product product){

    }

    
    public void addPrice(){

    }

    public void addTitle(){

    }

    public void addTitleList(){

    }

    //public


}
