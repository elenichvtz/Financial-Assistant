package com.example.finassistant.ui;

import android.content.DialogInterface;
import android.content.Intent;
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

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * The type List activity.
 */
public class ListActivity extends AppCompatActivity implements ShoppingListView {

    /**
     * The Lists.
     */
    ListView lists;
    /**
     * The Price.
     */
    EditText price;
    /**
     * The Title.
     */
    static EditText title;
    /**
     * The Presenter.
     */
    ShoppingListPresenter presenter;

    /**
     * The List.
     */
    ShoppingList list  = new ShoppingList();
    /**
     * The S list.
     */
    ArrayList<ShoppingList> sList = new ArrayList<>();

    /**
     * The constant LAUNCH_ACTIVITY.
     */
    public static final int LAUNCH_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        presenter = new ShoppingListPresenter(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == LAUNCH_ACTIVITY) {
            if(resultCode == ProductActivity.RESULT_OK){

                System.out.println("Fourth");
                ProductActivity pact = new ProductActivity();
                if(title.getText().toString().equals("")){
                    title.setText("New List");
                }
                list = pact.list;
                list.setTitle(title.getText().toString());

                presenter.getAccount().addList(list);

                sList.add(list);


            }
            if (resultCode == ProductActivity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

    public void onStart() {
        super.onStart();
        lists = findViewById(R.id.button_shopping_list);

        title = findViewById(R.id.title);

        final FloatingActionButton add = findViewById(R.id.add);

        final Button submit = findViewById(R.id.submit);
        lists.setVisibility(View.VISIBLE);
        add.setVisibility(View.VISIBLE);
        submit.setVisibility(View.GONE);

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

                android.app.AlertDialog.Builder info = new android.app.AlertDialog.Builder(ListActivity.this);
                info.setTitle("Details");
                DecimalFormat numberFormat = new DecimalFormat("#.00");
                info.setMessage("Title: " + sList.get(position).getTitle() +"\n\n" + "Total: " +
                        numberFormat.format(sList.get(position).getTotal()) + " €\n\n" + "Products: " + sList.get(position).getProducts());

                final Iterator<Product> productIterator = sList.get(position).getProducts().iterator();

                ArrayList<Product> products = new ArrayList();

                while(productIterator.hasNext()) {
                    products.add(productIterator.next());
                }

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
                                lists.setAdapter(adapter);
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
                title.setVisibility(View.VISIBLE);

                title = findViewById(R.id.title);
                submit.setVisibility(View.VISIBLE);
                add.setVisibility(View.GONE);
                lists.setVisibility(View.GONE);

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO add product
                        System.out.println("title: " + title.getText().toString());

                        Intent intent= new Intent(ListActivity.this, ProductActivity.class);
                        startActivityForResult(intent, LAUNCH_ACTIVITY);
                        System.out.println("Now on ListActivity");
                    }
                });
            }
        });
    }

    @Override
    public void addProduct(Product product){
        list.addProduct(product);
    }

    @Override
    public void addTitle(String title) {

    }

    @Override
    public void addPrice(double price) {

    }

    @Override
    public void addTitleList(String title) {
        list.setTitle(title);
    }



}
