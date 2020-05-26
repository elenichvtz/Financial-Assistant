package com.example.finassistant.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
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
import com.example.finassistant.domain.Account;
import com.example.finassistant.domain.Expense;
import com.example.finassistant.domain.ExpenseCategory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;

public class ExpenseActivity extends AppCompatActivity {

    TextView textView2;
    TextView textView3;
    ListView expenses;
    ArrayList<Expense> expenselist = new ArrayList<>();
    double amountValue;
    double newamountValue;
    Date dateValue;
    Date newdateValue;
    ExpenseCategory selected_category;
    ExpenseCategory newselected_category;
    Spinner categories;
    EditText amount;
    EditText date;
    //TODO prosvasi sto account
    Account account = new Account();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
    }

    public void onStart() {
        super.onStart();

        Expense expense = new Expense();
        expense.setSum(76.34);
        account.addExpense(expense);

        categories = findViewById(R.id.category);
        amount = findViewById(R.id.txt_input);
        date = findViewById(R.id.date);
        expenses = findViewById(R.id.button_expense);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        final FloatingActionButton add = findViewById(R.id.add);
        final Button submit = findViewById(R.id.submit);
        date.setVisibility(View.GONE);
        amount.setVisibility(View.GONE);
        submit.setVisibility(View.GONE);
        categories.setVisibility(View.GONE);
        textView2.setVisibility(View.GONE);
        textView3.setVisibility(View.VISIBLE);
        expenses.setVisibility(View.VISIBLE);
        add.setVisibility(View.VISIBLE);

        textView3.setText("Total expenses: " + account.CalculateTotalExpense() + " €");

        final ArrayAdapter arrayAdapter = new ArrayAdapter(ExpenseActivity.this, android.R.layout.simple_list_item_1, expenselist);
        expenses.setAdapter(arrayAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ExpenseCategory[] items = new ExpenseCategory[]{ExpenseCategory.HEALTH, ExpenseCategory.ENTERTAINMENT, ExpenseCategory.SHOPPING, ExpenseCategory.TRANSPORT, ExpenseCategory.OBLIGATION};
                //create an adapter to describe how the items are displayed, adapters are used in several places in android.
                //There are multiple variations of this, but this is the basic variant.
                ArrayAdapter<String> adapter = new ArrayAdapter(ExpenseActivity.this, android.R.layout.simple_spinner_dropdown_item, items);
                //set the spinners adapter to the previously created one.
                categories.setAdapter(adapter);

                date.setVisibility(View.VISIBLE);
                amount.setVisibility(View.VISIBLE);
                categories.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.GONE);
                expenses.setVisibility(View.GONE);
                add.setVisibility(View.GONE);

                amount.setOnKeyListener(new View.OnKeyListener() {
                    public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                        //If the keyevent is a key-down event on the "enter" button
                        if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {

                            amount = findViewById(R.id.txt_input);
                            amountValue = Double.parseDouble(amount.getText().toString());
                            return true;
                        }
                        return false;
                    }
                });

                date.setOnKeyListener(new View.OnKeyListener() {
                    public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                        //If the keyevent is a key-down event on the "enter" button
                        if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {

                            date = findViewById(R.id.date);
                            dateValue = (Date) date.getText();
                            System.out.println("fffffffffffffffffffffffffffffffffffffffffff"+date.toString());

                            return true;
                        }
                        return false;
                    }
                });

                //category
                categories.setOnKeyListener(new View.OnKeyListener() {
                    public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                        //If the keyevent is a key-down event on the "enter" button
                        if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {

                            selected_category = (ExpenseCategory) categories.getSelectedItem();
                            System.out.println("1111111111111111111111111111 "+selected_category.toString());

                            return true;
                        }
                        return false;
                    }
                });


                //otan pataei submit na ftiaxnetai expense kai na mpainei stin expense list tou account
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        expenses.setVisibility(View.VISIBLE);
                        add.setVisibility(View.VISIBLE);
                        amount.setVisibility(View.GONE);
                        date.setVisibility(View.GONE);
                        submit.setVisibility(View.GONE);
                        categories.setVisibility(View.GONE);

                        Expense expense = new Expense(amountValue, dateValue, selected_category);
                        System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq "+amountValue);

                        //TODO apothikeusi sto account
                        account.addExpense(expense);

                        expenselist.add(expense);
                        System.out.println("iiifgyyggggggggggggggggggggggg   " +account.getExpenses().size());

                        add.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.GONE);
                        textView3.setVisibility(View.VISIBLE);
                        textView3.setText("Total expenses: " + account.CalculateTotalExpense() + " €");
                        //otan ksanapataei add, sta pedia exei tis times tou proigoumenou expense alla ama ta allakseis apothikevei kainourgio eksodo
                        //den apothikevei ta eksoda pou ftiaksame an vgeis kai ksanampeis -> DAO(?)
                        //den doulevei to enter mono to submit
                    }
                });

                expenses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                        //emfanizei ta stoixeia tou expense
                        AlertDialog.Builder info = new AlertDialog.Builder(ExpenseActivity.this);
                        info.setTitle("");
                        info.setMessage("Category: " + expenselist.get(position).getCategory() +"\n\n" + "Amount: " +
                                expenselist.get(position).getSum() + " €\n\n" + "End Date: " + expenselist.get(position).getDateEnd());

                        info.setPositiveButton("OK", null);

                        //edit expense
                        info.setNeutralButton("Edit", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                date.setVisibility(View.VISIBLE);
                                amount.setVisibility(View.VISIBLE);
                                categories.setVisibility(View.VISIBLE);
                                submit.setVisibility(View.VISIBLE);
                                textView2.setVisibility(View.VISIBLE);
                                textView3.setVisibility(View.GONE);
                                expenses.setVisibility(View.GONE);
                                add.setVisibility(View.GONE);

                                amount.setOnKeyListener(new View.OnKeyListener() {
                                    public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                                        //If the keyevent is a key-down event on the "enter" button
                                        if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {

                                            amount = findViewById(R.id.txt_input);
                                            newamountValue = Double.parseDouble(amount.getText().toString());
                                            return true;
                                        }
                                        return false;
                                    }
                                });

                                date.setOnKeyListener(new View.OnKeyListener() {
                                    public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                                        //If the keyevent is a key-down event on the "enter" button
                                        if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {

                                            date = findViewById(R.id.date);
                                            newdateValue = (Date) date.getText();

                                            return true;
                                        }
                                        return false;
                                    }
                                });

                                //category
                                categories.setOnKeyListener(new View.OnKeyListener() {
                                    public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                                        //If the keyevent is a key-down event on the "enter" button
                                        if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {

                                            newselected_category = (ExpenseCategory) categories.getSelectedItem();
                                            System.out.println("1111111111111111111111111111 "+ newselected_category.toString());

                                            return true;
                                        }
                                        return false;
                                    }
                                });

                                submit.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        expenses.setVisibility(View.VISIBLE);
                                        add.setVisibility(View.VISIBLE);
                                        amount.setVisibility(View.GONE);
                                        date.setVisibility(View.GONE);
                                        submit.setVisibility(View.GONE);
                                        categories.setVisibility(View.GONE);

                                        //TODO apothikeusi sto account
                                        expenselist.get(position).setCategory(newselected_category);
                                        expenselist.get(position).setDateEnd(newdateValue);
                                        expenselist.get(position).setSum(newamountValue);

                                        add.setVisibility(View.VISIBLE);
                                        textView2.setVisibility(View.GONE);
                                        textView3.setVisibility(View.VISIBLE);
                                        textView3.setText("Total expenses: " + account.CalculateTotalExpense() + " €");
                                        //otan ksanapataei add, sta pedia exei tis times tou proigoumenou expense alla ama ta allakseis apothikevei kainourgio eksodo
                                        //den apothikevei ta eksoda pou ftiaksame an vgeis kai ksanampeis -> DAO(?)
                                        //den doulevei to enter mono to submit
                                    }
                                });
                            }
                        });

                        info.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                AlertDialog.Builder delete = new AlertDialog.Builder(ExpenseActivity.this);
                                delete.setTitle("Delete?");
                                delete.setMessage("Are you sure you want to delete " + position);
                                final int positionToRemove = position;
                                delete.setNegativeButton("Cancel", null);
                                delete.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        //TODO diagrafi apo to account
                                        //MyDataObject.remove(positionToRemove);
                                        arrayAdapter.notifyDataSetChanged();
                                    }});
                                delete.show();
                            }
                        });
                        info.show();
                    }
                });
            }
        });
    }
}
