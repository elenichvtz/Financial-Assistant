package com.example.finassistant.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finassistant.R;
import com.example.finassistant.domain.Expense;
import com.example.finassistant.domain.Product;
import com.example.finassistant.domain.ShoppingList;
import com.example.finassistant.ui.account.ShoppingListPresenter;
import com.example.finassistant.ui.account.ShoppingListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

public class ListActivity extends AppCompatActivity implements ShoppingListView {

    ListView lists;
    EditText price;
    EditText title;
    ShoppingListPresenter presenter;

    double priceValue;
    String list_titleValue;
    String product_titleValue;
    ShoppingList list;
    ArrayList<ShoppingList> sList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        presenter = new ShoppingListPresenter(this);
    }

    public void onStart() {
        super.onStart();
        lists = findViewById(R.id.button_shopping_list);
        price = findViewById(R.id.txt_input);
        title = findViewById(R.id.title);
        final FloatingActionButton add = findViewById(R.id.add);
        final Button submit = findViewById(R.id.submit);
        lists.setVisibility(View.VISIBLE);
        add.setVisibility(View.VISIBLE);
        submit.setVisibility(View.GONE);
        price.setVisibility(View.GONE);
        title.setVisibility(View.GONE);

        Iterator<ShoppingList> iterator = presenter.getAccount().getShoppingList().iterator();
        while(iterator.hasNext()) {
            sList.add(iterator.next());
        }

        final ArrayAdapter adapter = new ArrayAdapter(ListActivity.this, android.R.layout.simple_list_item_1, sList);
        lists.setAdapter(adapter);

        lists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                //emfanizei ta stoixeia tou expense
                android.app.AlertDialog.Builder info = new android.app.AlertDialog.Builder(ListActivity.this);
                info.setTitle("Details");

                final Iterator<Product> productIterator = sList.get(position).getProducts().iterator();

                ArrayList<Product> products = new ArrayList();

                while(productIterator.hasNext()) {
                    products.add(productIterator.next());
                }

                //TODO otan pataei na tou emfanizei ta proionta tis listas
                info.setPositiveButton("OK", null);

                info.setNeutralButton("Add List to Expenses", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        android.app.AlertDialog.Builder addexpense = new android.app.AlertDialog.Builder(ListActivity.this);
                        addexpense.setTitle("Are you sure you want to add the list to your expenses?");
                        addexpense.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
                        addexpense.setPositiveButton("Yes", new android.app.AlertDialog.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ShoppingList llist = sList.get(position);
                                presenter.getAccount().ShoppingExpenses(llist);
                                System.err.println(llist.getTotal());
                            }
                        });
                        addexpense.show();
                    }
                });

                info.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        android.app.AlertDialog.Builder delete = new android.app.AlertDialog.Builder(ListActivity.this);
                        delete.setTitle("Are you sure you want to delete the list?");
                        final int positionToRemove = position;
                        delete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
                        delete.setPositiveButton("Delete", new android.app.AlertDialog.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ShoppingList list = sList.get(position);
                                presenter.getAccount().removeList(list);
                                adapter.notifyDataSetChanged();
                            }});
                        delete.show();
                    }
                });
                info.show();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submit.setVisibility(View.VISIBLE);
                add.setVisibility(View.GONE);
                lists.setVisibility(View.GONE);
                title.setVisibility(View.VISIBLE);

                list = new ShoppingList();

                //TODO add product

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        title = findViewById(R.id.title);
                        list_titleValue = title.getText().toString();

                        presenter.getAccount().addList(list);

                        sList.add(list);
                    }
                });
            }
        });
    }

    @Override
    public void addProduct(Product product){

    }

    @Override
    public void addTitle(String title) {

    }

    @Override
    public void addPrice(double price) {

    }

    @Override
    public void addTitleList(String title) {

    }
}
