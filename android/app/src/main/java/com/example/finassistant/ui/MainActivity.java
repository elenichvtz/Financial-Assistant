package com.example.finassistant.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.finassistant.R;
import com.example.finassistant.memorydao.MemoryInitializer;

import java.text.ParseException;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private static boolean initialized = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button)findViewById(R.id.button_income);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityIncome();
            }
        });

        button2 = (Button)findViewById(R.id.button_expense);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityExpense();
            }
        });

        button3 = (Button)findViewById(R.id.button_goals);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityGoal();
            }
        });

        button4 = (Button)findViewById(R.id.button_shopping_list);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityList();
            }
        });

        if(!initialized){
            try {
                new MemoryInitializer().prepareData();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            initialized = true;

        }

    }
    public void openActivityIncome(){
        Intent intent = new Intent(this, IncomeActivity.class);
        startActivity(intent);
    }

    public void openActivityExpense(){
        Intent intent = new Intent(this, ExpenseActivity.class);
        startActivity(intent);
    }

    public void openActivityGoal(){
        Intent intent = new Intent(this, GoalActivity.class);
        startActivity(intent);
    }

    public void openActivityList(){
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
}
