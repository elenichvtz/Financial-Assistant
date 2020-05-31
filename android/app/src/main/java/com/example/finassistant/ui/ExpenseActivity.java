package com.example.finassistant.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finassistant.R;
import com.example.finassistant.domain.ExchangeCategory;
import com.example.finassistant.domain.Expense;
import com.example.finassistant.domain.ExpenseCategory;
import com.example.finassistant.ui.account.ExpensePresenter;
import com.example.finassistant.ui.account.ExpenseView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class ExpenseActivity extends AppCompatActivity implements ExpenseView {

    TextView textView2;
    TextView textView3;
    TextView textView4;
    ListView expenses;
    ExpenseCategory selected_category;
    ExchangeCategory selected_exchange_category;
    Spinner categories;
    Spinner exchange_category;
    EditText amount;
    EditText date;

    ArrayList<Expense> expenselist = new ArrayList<>();
    double amountValue;
    Date dateValue;

    static ExpensePresenter presenter;
    Expense expense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        presenter =  new ExpensePresenter(this);
    }

    public void onStart() {
        super.onStart();

        categories = findViewById(R.id.category);
        exchange_category = findViewById(R.id.exchange_category);
        amount = findViewById(R.id.txt_input);
        date = findViewById(R.id.date);
        expenses = findViewById(R.id.button_expense);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        final FloatingActionButton add = findViewById(R.id.add);
        final Button submit = findViewById(R.id.submit);
        date.setVisibility(View.GONE);
        amount.setVisibility(View.GONE);
        submit.setVisibility(View.GONE);
        categories.setVisibility(View.GONE);
        exchange_category.setVisibility(View.GONE);
        textView2.setVisibility(View.GONE);
        textView3.setVisibility(View.VISIBLE);
        textView4.setVisibility(View.GONE);
        expenses.setVisibility(View.VISIBLE);
        add.setVisibility(View.VISIBLE);

        textView3.setText("Total expenses: " + presenter.getAccount().CalculateTotalExpense() + " €");

        Iterator<Expense> iterator = presenter.getAccount().getExpenses().iterator();
        while(iterator.hasNext()) {
            expenselist.add(iterator.next());
        }

        final ArrayAdapter arrayAdapter = new ArrayAdapter(ExpenseActivity.this, android.R.layout.simple_list_item_1, expenselist);
        expenses.setAdapter(arrayAdapter);

        expenses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                //emfanizei ta stoixeia tou expense
                android.app.AlertDialog.Builder info = new android.app.AlertDialog.Builder(ExpenseActivity.this);
                info.setTitle("Details");

                info.setMessage("Category: " + expenselist.get(position).getCategory() +"\n\n" + "Amount: " +
                        expenselist.get(position).getSum() + " €\n\n" + "End Date: " + expenselist.get(position).getDateEnd() + "\n\nExchange Category: " +
                        expenselist.get(position).getExchange());

                info.setPositiveButton("OK", null);

                info.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        android.app.AlertDialog.Builder delete = new android.app.AlertDialog.Builder(ExpenseActivity.this);
                        delete.setTitle("Are you sure you want to delete the expense?");
                        final int positionToRemove = position;
                        delete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
                        delete.setPositiveButton("Delete", new android.app.AlertDialog.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Expense expense = expenselist.get(position);
                                presenter.getAccount().removeExpense(expense);
                                arrayAdapter.notifyDataSetChanged();
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

                ExpenseCategory[] items = new ExpenseCategory[]{ExpenseCategory.HEALTH, ExpenseCategory.ENTERTAINMENT, ExpenseCategory.SHOPPING, ExpenseCategory.TRANSPORT, ExpenseCategory.OBLIGATION};
                //create an adapter to describe how the items are displayed, adapters are used in several places in android.
                //There are multiple variations of this, but this is the basic variant.
                ArrayAdapter<String> adapter = new ArrayAdapter(ExpenseActivity.this, android.R.layout.simple_spinner_dropdown_item, items);
                //set the spinners adapter to the previously created one.
                categories.setAdapter(adapter);

                ExchangeCategory[] exchangeCategories = new ExchangeCategory[]{ExchangeCategory.CASH, ExchangeCategory.ONLINE};
                ArrayAdapter<String> eadapter = new ArrayAdapter(ExpenseActivity.this, android.R.layout.simple_spinner_dropdown_item, exchangeCategories);
                exchange_category.setAdapter(eadapter);

                date.setVisibility(View.VISIBLE);
                amount.setVisibility(View.VISIBLE);
                categories.setVisibility(View.VISIBLE);
                exchange_category.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.GONE);
                textView4.setVisibility(View.VISIBLE);
                expenses.setVisibility(View.GONE);
                add.setVisibility(View.GONE);

                expense = new Expense();

                categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String clickedItem = String.valueOf(position);
                        if (clickedItem.equals("0")){
                            addCategory(ExpenseCategory.HEALTH);
                            selected_category=ExpenseCategory.HEALTH;
                        }else if (clickedItem.equals("1")){
                            addCategory(ExpenseCategory.ENTERTAINMENT);
                            selected_category=ExpenseCategory.ENTERTAINMENT;
                        }else if(clickedItem.equals("2")){
                            addCategory(ExpenseCategory.SHOPPING);
                            selected_category=ExpenseCategory.SHOPPING;
                        }else if(clickedItem.equals("3")){
                            addCategory(ExpenseCategory.TRANSPORT);
                            selected_category=ExpenseCategory.TRANSPORT;
                        }else if(clickedItem.equals("4")){
                            addCategory(ExpenseCategory.OBLIGATION);
                            selected_category=ExpenseCategory.OBLIGATION;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        //addCategory(ExpenseCategory.HEALTH);
                    }
                });

                exchange_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String clickedItem = String.valueOf(position);
                        if (clickedItem.equals("0")){
                            selected_exchange_category = ExchangeCategory.CASH;
                        }else if (clickedItem.equals("1")){
                            selected_exchange_category = ExchangeCategory.ONLINE;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                //otan pataei submit na ftiaxnetai expense kai na mpainei stin expense list tou account
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        amount = findViewById(R.id.txt_input);
                        amountValue = Double.parseDouble(amount.getText().toString());
                        System.err.println("amountvalue :" +amountValue);
                        System.err.println("category "+selected_category);
                        addAmount(amountValue);

                        date = findViewById(R.id.date);
                        String parsedDate = (date.getText().toString());
                        //dateValue = (Date) date.getText();
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            dateValue = formatter.parse(parsedDate);
                            addDate(dateValue);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        expenses.setVisibility(View.VISIBLE);
                        add.setVisibility(View.VISIBLE);
                        amount.setVisibility(View.GONE);
                        date.setVisibility(View.GONE);
                        submit.setVisibility(View.GONE);
                        categories.setVisibility(View.GONE);
                        exchange_category.setVisibility(View.GONE);
                        textView2.setVisibility(View.GONE);
                        textView3.setVisibility(View.VISIBLE);
                        textView4.setVisibility(View.GONE);

                        presenter.getAccount().addExpense(expense);
                        System.out.println("presenter.getAccount().getExpenses().size() "+presenter.getAccount().getExpenses().size());

                        expense.setExchange(selected_exchange_category);

                        expenselist.add(expense);

                        presenter.getAccount().addExpense(expense);
                        textView3.setText("Total expenses: " + presenter.getAccount().CalculateTotalExpense() + " €");
                    }
                });
            }
        });
    }

    @Override
    public void addCategory(ExpenseCategory category){
        expense.setCategory(category);
    }

    @Override
    public void addAmount(Double amount){
        expense.setSum(amount);
    }

    @Override
    public void addDate(Date date){
        expense.setDateEnd(date);
    }

    @Override
    public void showErrorMessage(String title,String message){
        new AlertDialog.Builder(ExpenseActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }
}
