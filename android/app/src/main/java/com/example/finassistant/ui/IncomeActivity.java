package com.example.finassistant.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import com.example.finassistant.ui.account.IncomePresenter;
import com.example.finassistant.ui.account.IncomeView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * The type Income activity.
 */
public class IncomeActivity extends AppCompatActivity implements IncomeView {

    /**
     * The Income category.
     */
    Spinner incomeCategory;
    /**
     * The Incomes.
     */
    ListView incomes;
    /**
     * The Amount.
     */
    EditText amount;
    /**
     * The End date.
     */
    EditText endDate;
    /**
     * The Text view 2.
     */
    TextView textView2;
    /**
     * The Text view 3.
     */
    TextView textView3;
    /**
     * The Tax free.
     */
    TextView taxFree;

    /**
     * The Income list.
     */
    ArrayList<Income> incomeList = new ArrayList<>();
    /**
     * The Amount value.
     */
    double amountValue;
    /**
     * The Date value.
     */
    Date dateValue;
    /**
     * The Selected category.
     */
    IncomeCategory selected_category;

    /**
     * The Presenter.
     */
    static IncomePresenter presenter;
    /**
     * The Income.
     */
    Income income;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        presenter = new IncomePresenter(this);
    }

    public void onStart() {
        super.onStart();

        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        taxFree = findViewById(R.id.taxfree);
        incomes = findViewById(R.id.button_income);
        incomeCategory = findViewById(R.id.incomecategory);
        amount = findViewById(R.id.txt_input);
        endDate = findViewById(R.id.date);
        final FloatingActionButton add = findViewById(R.id.add);
        final Button submit = findViewById(R.id.submit);
        incomeCategory.setVisibility(View.GONE);
        amount.setVisibility(View.GONE);
        endDate.setVisibility(View.GONE);
        textView2.setVisibility(View.GONE);
        textView3.setVisibility(View.VISIBLE);
        taxFree.setVisibility(View.VISIBLE);
        incomes.setVisibility(View.VISIBLE);
        add.setVisibility(View.VISIBLE);
        submit.setVisibility(View.GONE);
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        textView3.setText("Total income: " + numberFormat.format(presenter.getAccount().CalculateTotalIncome()) + " €");
        taxFree.setText("Your tax free is: " + numberFormat.format(presenter.getAccount().CalculateCurrentTaxFree()) + "€ out of " + presenter.getAccount().CalculateTaxFree() + " €");

        Iterator<Income> iterator = presenter.getAccount().getIncome().iterator();
        while(iterator.hasNext()) {
            incomeList.add(iterator.next());
        }

        final ArrayAdapter arrayAdapter = new ArrayAdapter(IncomeActivity.this, android.R.layout.simple_list_item_1, incomeList);
        incomes.setAdapter(arrayAdapter);

        incomes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                android.app.AlertDialog.Builder info = new android.app.AlertDialog.Builder(IncomeActivity.this);
                info.setTitle("Details");
                DecimalFormat numberFormat = new DecimalFormat("#.00");
                info.setMessage("Category: " + incomeList.get(position).getCategory() +"\n\n" + "Amount: " +
                        numberFormat.format(incomeList.get(position).getSum())+ " €\n\n" + "End Date: " + incomeList.get(position).getDateEnd());

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
                                Income income = incomeList.get(position);
                                presenter.getAccount().removeIncome(income);
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
                taxFree.setVisibility(View.GONE);
                textView2.setVisibility(View.VISIBLE);
                incomes.setVisibility(View.GONE);
                add.setVisibility(View.GONE);

                final ArrayList<IncomeCategory> categories = new ArrayList<>();
                categories.add(IncomeCategory.SALARY);
                categories.add(IncomeCategory.REGULAR);
                categories.add(IncomeCategory.NONREGULAR);

                final ArrayAdapter<String> adapter = new ArrayAdapter(IncomeActivity.this, android.R.layout.simple_spinner_dropdown_item, categories);

                income = new Income();

                incomeCategory.setAdapter(adapter);
                incomeCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String clickedItem = String.valueOf(position);
                        if (clickedItem.equals("0")) {
                            addCategory(IncomeCategory.SALARY);
                            selected_category = IncomeCategory.SALARY;
                        } else if (clickedItem.equals("1")) {
                            addCategory(IncomeCategory.REGULAR);
                            selected_category = IncomeCategory.REGULAR;
                        } else if (clickedItem.equals("2")) {
                            addCategory(IncomeCategory.NONREGULAR);
                            selected_category = IncomeCategory.NONREGULAR;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                System.err.println("Selected category is: "+selected_category);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        amount = findViewById(R.id.txt_input);

                        if(amount.getText().toString().equals("")){
                            amount.setText("0");
                        }

                        amountValue = Double.parseDouble(amount.getText().toString());
                        amount.setText("");

                        boolean isValid = presenter.validateAmount(amountValue);
                        if (isValid) {
                            addAmount(amountValue);

                            endDate = findViewById(R.id.date);

                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                            String parsedDate = (endDate.getText().toString());
                            if(parsedDate.equals("")){
                                addDate(new Date());
                            }else {
                                try {
                                    dateValue = formatter.parse(parsedDate);
                                    addDate(dateValue);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }

                            presenter.getAccount().addIncome(income);

                            incomeList.add(income);
                            DecimalFormat numberFormat = new DecimalFormat("#.00");
                            textView3.setText("Total income: " + numberFormat.format(presenter.getAccount().CalculateTotalIncome()) + " €");
                            taxFree.setText("Your tax free is: " + numberFormat.format(presenter.getAccount().CalculateCurrentTaxFree()) + "€ out of " + presenter.getAccount().CalculateTaxFree() + " €");

                            incomes.setVisibility(View.VISIBLE);
                            add.setVisibility(View.VISIBLE);
                            amount.setVisibility(View.GONE);
                            endDate.setVisibility(View.GONE);
                            submit.setVisibility(View.GONE);
                            incomeCategory.setVisibility(View.GONE);
                            textView2.setVisibility(View.GONE);
                            textView3.setVisibility(View.VISIBLE);
                            taxFree.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        });
    }

    @Override
    public void addCategory(IncomeCategory category){
        income.setCategory(category);
    }

    @Override
    public void addAmount(Double amount){
        income.setSum(amount);
    }

    @Override
    public void addDate(Date date){
        income.setDateEnd(date);
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
