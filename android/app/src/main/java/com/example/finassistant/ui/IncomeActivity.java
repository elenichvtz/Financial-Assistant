package com.example.finassistant.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finassistant.R;
import com.example.finassistant.domain.ExchangeCategory;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class IncomeActivity extends AppCompatActivity {
    Button income;
    Button total_income;
    ListView incomeCategory;
    static EditText amount;
    EditText endDate;
    double amountValue;


    TextView er ;
    private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        income = (Button) findViewById(R.id.button2);
        total_income = (Button) findViewById(R.id.button5);
        incomeCategory = (ListView) findViewById((R.id.listview));
        amount = (EditText) findViewById(R.id.txt_input);
        endDate = (EditText) findViewById(R.id.date);


        incomeCategory.setVisibility(View.GONE);
        income.setVisibility(View.VISIBLE);
        total_income.setVisibility(View.VISIBLE);
        amount.setVisibility(View.GONE);
        endDate.setVisibility(View.GONE);



        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total_income.setVisibility(View.GONE);
                income.setVisibility(View.GONE);
                incomeCategory.setVisibility(View.VISIBLE);
                ArrayList<String> categories = new ArrayList<>();
                categories.add("salary");
                categories.add("gift");
                categories.add("overtime payment");
                categories.add("wages");
                ArrayAdapter arrayAdapter = new ArrayAdapter(IncomeActivity.this, android.R.layout.simple_list_item_1, categories);

                incomeCategory.setAdapter(arrayAdapter);
                incomeCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String itemValue = (String) incomeCategory.getItemAtPosition(position);
                        incomeCategory.setVisibility(View.GONE);

                        amount.setVisibility(View.VISIBLE);
                        insertAmount();
                        //amount = (EditText) findViewById(R.id.txt_input);
                        //String error = amount.getText().toString();


                        }


                });

            }
        });
    }

    public void insertAmount(){
        amount = (EditText) findViewById(R.id.txt_input);
        String error = amount.getText().toString();

        System.out.println("Inside insertAmount");
        amount.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                //If the keyevent is a key-down event on the "enter" button
                if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    //...
                    // Perform your action on key press here
                    // ...
                    amount = (EditText) findViewById(R.id.txt_input);


                    if((!amount.getText().toString().equals("")) && (!amount.getText().toString().equals("0"))) {
                        System.out.println("Amount without toString(): "+amount.getText());
                        System.out.println("Amount with toString(): "+amount.getText().toString());
                        amountValue = Double.parseDouble(amount.getText().toString());
                        endDate.setVisibility(View.VISIBLE);
                    }


                    return true;

                }

                return false;

            }

        });
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
