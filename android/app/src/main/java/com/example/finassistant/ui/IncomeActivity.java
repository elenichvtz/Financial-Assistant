package com.example.finassistant.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.finassistant.R;
import com.example.finassistant.dao.AccountDAO;
import com.example.finassistant.dao.UserDAO;
import com.example.finassistant.domain.Account;
import com.example.finassistant.domain.Income;
import com.example.finassistant.domain.IncomeCategory;
import com.example.finassistant.domain.User;
import com.example.finassistant.memorydao.AccountDAOMemory;
import com.example.finassistant.memorydao.UserDAOMemory;
import com.example.finassistant.ui.account.AccountPresenter;
import com.example.finassistant.ui.account.AccountView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class IncomeActivity extends AppCompatActivity implements AccountView {
    Button income;
    Button total_income;
    ListView incomeCategory;
    EditText amount;
    EditText endDate;
    double amountValue;
    //static AccountDAO accountDAO;
    //static Account account;
    static AccountPresenter presenter;
    Income income2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        income = findViewById(R.id.button2);
        total_income = findViewById(R.id.button5);
        incomeCategory = findViewById((R.id.goallist));
        amount = findViewById(R.id.txt_input);
        endDate = findViewById(R.id.date);
        incomeCategory.setVisibility(View.GONE);
        income.setVisibility(View.VISIBLE);
        total_income.setVisibility(View.VISIBLE);
        amount.setVisibility(View.GONE);
        endDate.setVisibility(View.GONE);


        presenter = new AccountPresenter(this);
        income2 = new Income();
        //accountDAO = new AccountDAOMemory();
        //account = accountDAO.find(1234);


        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total_income.setVisibility(View.GONE);
                income.setVisibility(View.GONE);
                incomeCategory.setVisibility(View.VISIBLE);
                ArrayList<String> categories = new ArrayList<>();
                categories.add("salary");
                categories.add("regular");
                categories.add("nonRegular");

                ArrayAdapter arrayAdapter = new ArrayAdapter(IncomeActivity.this, android.R.layout.simple_list_item_1, categories);

                incomeCategory.setAdapter(arrayAdapter);
                incomeCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(final AdapterView<?> parent, View view, int position, long id) {
                        String itemValue = (String) incomeCategory.getItemAtPosition(position);
                        System.err.println("Item value is: "+itemValue);
                        final Income income = new Income();
                        if( itemValue.equalsIgnoreCase("salary")){
                            income.setCategory(IncomeCategory.SALARY);
                            addCategory(IncomeCategory.SALARY);
                        }else if(itemValue.equalsIgnoreCase("regular")){
                            income.setCategory(IncomeCategory.REGULAR);
                            addCategory(IncomeCategory.REGULAR);
                        }else{
                            income.setCategory(IncomeCategory.NONREGULAR);
                            addCategory(IncomeCategory.NONREGULAR);
                        }
                        incomeCategory.setVisibility(View.GONE);
                        amount.setVisibility(View.VISIBLE);
                        amount.setOnKeyListener(new View.OnKeyListener() {
                            public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                                //If the keyevent is a key-down event on the "enter" button
                                if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {

                                    amount = findViewById(R.id.txt_input);
                                    amountValue = Double.parseDouble(amount.getText().toString());
                                    presenter.validateAmount(amountValue);
                                    addAmount(amountValue);
                                    //income.setSum(amountValue);
                                    endDate.setVisibility(View.VISIBLE);
                                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                    try {
                                        Date parsedDate = formatter.parse(endDate.toString());
                                        addDate(parsedDate);
                                        //income.setDateEnd(parsedDate);
                                        //Income income = new Income(amountValue,parsedDate,)
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }

                                    //account.addIncome(income);
                                    System.err.println( presenter.getAccount().getId());
                                    presenter.getAccount().addIncome(income2);
                                    //System.err.println("Total income is: "+account.CalculateTotalIncome());



                                    return true;
                                }
                                return false;
                            }
                        });
                    }
                });
            }
        });

        total_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openDialog();
                //System.err.println("Total income is: "+account.CalculateTotalIncome());
                total_income.setVisibility(View.GONE);
                income.setVisibility(View.GONE);
                MyDialogue myDialogue = new MyDialogue();
                myDialogue.show(getSupportFragmentManager(),"message");
                /*AlertDialog.Builder builder = new AlertDialog.Builder();
                builder.setTitle("Total Income Results")
                        .setMessage("Your total income is: "+account.CalculateTotalIncome());

*/
            }


        });
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
