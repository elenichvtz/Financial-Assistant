package com.example.finassistant.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
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

import com.example.finassistant.R;
import com.example.finassistant.domain.Income;
import com.example.finassistant.domain.IncomeCategory;
import com.example.finassistant.ui.account.AccountPresenter;
import com.example.finassistant.ui.account.AccountView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class IncomeActivity extends AppCompatActivity implements AccountView {

    Spinner incomeCategory;
    ListView incomes;
    EditText amount;
    EditText endDate;
    double amountValue;
    Date dateValue;
    IncomeCategory selected_category;
    static AccountPresenter presenter;
    TextView textView2;
    TextView textView3;
    TextView taxfree;

    ArrayList<Income> incomelist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        presenter = new AccountPresenter(this);
    }

    public void onStart() {
        super.onStart();

        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        taxfree = findViewById(R.id.taxfree);
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
        taxfree.setVisibility(View.VISIBLE);
        incomes.setVisibility(View.VISIBLE);
        add.setVisibility(View.VISIBLE);
        submit.setVisibility(View.GONE);

        textView3.setText("Total income: " + presenter.getAccount().CalculateTotalIncome() + " €");
        taxfree.setText("Your tax free is: " + presenter.getAccount().CalculateCurrentTaxFree() + "€ out of " + presenter.getAccount().CalculateTaxFree() + " €");

        Iterator<Income> iterator = presenter.getAccount().getIncome().iterator();
        while(iterator.hasNext()) {
            incomelist.add(iterator.next());
        }

        final ArrayAdapter arrayAdapter = new ArrayAdapter(IncomeActivity.this, android.R.layout.simple_list_item_1, incomelist);
        incomes.setAdapter(arrayAdapter);

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
                        delete.setTitle("Are you sure you want to delete the income?");
                        final int positionToRemove = position;
                        delete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
                        delete.setPositiveButton("Delete", new android.app.AlertDialog.OnClickListener() {
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

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                incomeCategory.setVisibility(View.VISIBLE);
                amount.setVisibility(View.VISIBLE);
                endDate.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.GONE);
                taxfree.setVisibility(View.GONE);
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
                        //addAmount(amountValue);

                        endDate = findViewById(R.id.date);

                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                        try {
                            dateValue = formatter.parse(endDate.toString());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        incomes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                String clickedItem = String.valueOf(position);
                                if (clickedItem.equalsIgnoreCase("SALARY")) {
                                    selected_category = IncomeCategory.SALARY;
                                } else if (clickedItem.equalsIgnoreCase("REGULAR")) {
                                    selected_category = IncomeCategory.REGULAR;
                                } else if (clickedItem.equalsIgnoreCase("NONREGULAR")) {
                                    selected_category = IncomeCategory.NONREGULAR;
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        Income income = new Income(amountValue, dateValue, selected_category);

                        presenter.getAccount().addIncome(income);
                        System.out.println("presenter.getAccount().getIncome().size() "+presenter.getAccount().getIncome().size());

                        incomelist.add(income);

                        textView3.setText("Total income: " + presenter.getAccount().CalculateTotalIncome() + " €");
                        taxfree.setText("Your tax free is: " + presenter.getAccount().CalculateCurrentTaxFree() + "€ out of " + presenter.getAccount().CalculateTaxFree() + " €");

                        incomes.setVisibility(View.VISIBLE);
                        add.setVisibility(View.VISIBLE);
                        amount.setVisibility(View.GONE);
                        endDate.setVisibility(View.GONE);
                        submit.setVisibility(View.GONE);
                        incomeCategory.setVisibility(View.GONE);
                        textView2.setVisibility(View.GONE);
                        textView3.setVisibility(View.VISIBLE);
                        taxfree.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }

    public void openDialog(){
        MyDialogue myDialogue = new MyDialogue();
        myDialogue.show(getSupportFragmentManager(),"dialogue");
    }

    @Override
    public void addCategory(IncomeCategory category){
        //income2.setCategory(category);
    }

    @Override
    public void addAmount(Double amount){
        //income2.setSum(amount);
    }

    @Override
    public void addDate(Date date){
        //income2.setDateEnd(date);
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
