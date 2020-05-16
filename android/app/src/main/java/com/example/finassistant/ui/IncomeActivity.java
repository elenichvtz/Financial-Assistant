package com.example.finassistant.ui;

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

import java.util.ArrayList;

public class IncomeActivity extends AppCompatActivity {
    Button income;
    Button total_income;
    ListView incomeCategory;
    EditText amount;
    EditText endDate;
    double amountValue;

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
                        amount.setOnKeyListener(new View.OnKeyListener() {
                            public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                                //If the keyevent is a key-down event on the "enter" button
                                if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {

                                    amount = findViewById(R.id.txt_input);
                                    amountValue = Double.parseDouble(amount.getText().toString());
                                    endDate.setVisibility(View.VISIBLE);
                                    return true;
                                }
                                return false;
                            }
                        });
                    }
                });
            }
        });
    }
}
