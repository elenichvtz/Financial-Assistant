package com.example.finassistant.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
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

import com.example.finassistant.R;
import com.example.finassistant.dao.AccountDAO;
import com.example.finassistant.domain.Account;
import com.example.finassistant.domain.Income;
import com.example.finassistant.domain.IncomeCategory;
import com.example.finassistant.memorydao.AccountDAOMemory;
import com.example.finassistant.ui.account.AccountPresenter;
import com.example.finassistant.ui.account.AccountView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class IncomeActivity extends AppCompatActivity implements AccountView {

    Spinner incomeCategory;
    ListView incomes;
    EditText amount;
    EditText endDate;
    double amountValue;
    static AccountPresenter presenter;
    Income income2;
    TextView textView2;
    TextView textView3;

    ArrayList<Income> incomelist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        presenter = new AccountPresenter(this);
        income2 = new Income();
        //accountDAO = new AccountDAOMemory();
        //account = accountDAO.find(1234);
    }

    @SuppressLint("WrongViewCast")
    public void onStart() {
        super.onStart();

        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        incomes = findViewById((R.id.button_income));
        incomeCategory = findViewById((R.id.incomecategory));
        amount = findViewById(R.id.txt_input);
        endDate = findViewById(R.id.date);
        final FloatingActionButton add = findViewById(R.id.add);
        final Button submit = findViewById(R.id.submit);
        incomeCategory.setVisibility(View.GONE);
        amount.setVisibility(View.GONE);
        endDate.setVisibility(View.GONE);
        textView2.setVisibility(View.GONE);
        textView3.setVisibility(View.VISIBLE);
        incomes.setVisibility(View.VISIBLE);
        add.setVisibility(View.VISIBLE);
        submit.setVisibility(View.GONE);

        textView3.setText("Total expenses: " + presenter.getAccount().CalculateTotalIncome() + " €");

        final ArrayAdapter arrayAdapter = new ArrayAdapter(IncomeActivity.this, android.R.layout.simple_list_item_1, incomelist);
        incomes.setAdapter(arrayAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                incomeCategory.setVisibility(View.VISIBLE);
                amount.setVisibility(View.VISIBLE);
                endDate.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.GONE);
                textView2.setVisibility(View.VISIBLE);
                incomes.setVisibility(View.GONE);
                add.setVisibility(View.GONE);

                final ArrayList<IncomeCategory> categories = new ArrayList<>();
                categories.add(IncomeCategory.SALARY);
                categories.add(IncomeCategory.REGULAR);
                categories.add(IncomeCategory.NONREGULAR);

                final ArrayAdapter<String> adapter = new ArrayAdapter(IncomeActivity.this, android.R.layout.simple_spinner_dropdown_item, categories);

                incomeCategory.setAdapter(adapter);

                ArrayAdapter arrayAdapter = new ArrayAdapter(IncomeActivity.this, android.R.layout.simple_list_item_1, categories);

                incomeCategory.setAdapter(arrayAdapter);

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        amount = findViewById(R.id.txt_input);
                        amountValue = Double.parseDouble(amount.getText().toString());
                        System.err.println("amountvalue :" + amountValue);
                        addAmount(amountValue);

                        endDate = findViewById(R.id.date);

                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                        try {
                            Date parsedDate = formatter.parse(endDate.toString());
                            addDate(parsedDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        incomes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                String clickedItem = String.valueOf(position);
                                if (clickedItem.equalsIgnoreCase("SALARY")) {
                                    //expense.setCategory(ExpenseCategory.HEALTH);
                                    addCategory(IncomeCategory.SALARY);
                                } else if (clickedItem.equalsIgnoreCase("REGULAR")) {
                                    //expense.setCategory(ExpenseCategory.ENTERTAINMENT);
                                    addCategory(IncomeCategory.REGULAR);
                                } else if (clickedItem.equalsIgnoreCase("NONREGULAR")) {
                                    //expense.setCategory(ExpenseCategory.SHOPPING);
                                    addCategory(IncomeCategory.NONREGULAR);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        presenter.getAccount().addIncome(income2);
                        System.out.println("ggggggggggggggggggggggggggggggg "+presenter.getAccount().getIncome().size());

                        incomelist.add(income2);

                        /*AccountDAO accountDAO;
                        Account account;

                        accountDAO = new AccountDAOMemory();
                        account = accountDAO.find(1234);*/

                        textView3.setText("Total expensessss: " + presenter.getAccount().CalculateTotalIncome() + " €");

                        incomes.setVisibility(View.VISIBLE);
                        add.setVisibility(View.VISIBLE);
                        amount.setVisibility(View.GONE);
                        endDate.setVisibility(View.GONE);
                        submit.setVisibility(View.GONE);
                        incomeCategory.setVisibility(View.GONE);
                        textView2.setVisibility(View.GONE);
                        textView3.setVisibility(View.VISIBLE);
                    }
                });

                textView3.setText("Total expensessss: " + presenter.getAccount().CalculateTotalIncome() + " €");
            }
        });

        incomes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                //emfanizei ta stoixeia tou expense
                android.app.AlertDialog.Builder info = new android.app.AlertDialog.Builder(IncomeActivity.this);
                info.setTitle("Details");

                info.setMessage("Category: " + incomelist.get(position).getCategory() +"\n\n" + "Amount: " +
                        incomelist.get(position).getSum() + " €\n\n" + "End Date: " + incomelist.get(position).getDateEnd());

                info.setPositiveButton("OK", null);

                info.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        android.app.AlertDialog.Builder delete = new android.app.AlertDialog.Builder(IncomeActivity.this);
                        delete.setTitle("Delete?");
                        delete.setMessage("Are you sure you want to delete the income?");
                        final int positionToRemove = position;
                        delete.setNegativeButton("Cancel", null);
                        delete.setPositiveButton("Ok", new android.app.AlertDialog.OnClickListener() {
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

                /*openDialog();
                //System.err.println("Total income is: "+account.CalculateTotalIncome());
                MyDialogue myDialogue = new MyDialogue();
                myDialogue.show(getSupportFragmentManager(),"message");
                /*AlertDialog.Builder builder = new AlertDialog.Builder();
                builder.setTitle("Total Income Results")
                        .setMessage("Your total income is: "+account.CalculateTotalIncome()); */
    }

    public void openDialog(){
        MyDialogue myDialogue = new MyDialogue();
        myDialogue.show(getSupportFragmentManager(),"dialogue");
    }

    @Override
    public void addCategory(IncomeCategory category){
        income2.setCategory(category);
    }

    @Override
    public void addAmount(Double amount){
        income2.setSum(amount);
    }

    @Override
    public void addDate(Date date){
        income2.setDateEnd(date);
    }

    @Override
    public void showErrorMessage(String title,String message){
        new AlertDialog.Builder(IncomeActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }
}
