package com.example.finassistant.ui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finassistant.R;
import com.example.finassistant.domain.Account;
import com.example.finassistant.domain.Goal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Iterator;

public class GoalActivity extends AppCompatActivity {

    ListView goallist;
    Button add;
    EditText amount;
    double amountValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);
        final FloatingActionButton add = findViewById(R.id.floatingActionButton2);
        goallist = findViewById((R.id.goallist));
        amount = findViewById(R.id.txt_input);
        goallist.setVisibility(View.VISIBLE);
        add.setVisibility(View.VISIBLE);
        amount.setVisibility(View.GONE);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goallist.setVisibility(View.GONE);
                add.setVisibility(View.GONE);
                amount.setVisibility(View.VISIBLE);

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

                /*ArrayList<Goal> goals = new ArrayList<>();
                Account account = new Account();

                Iterator<Goal> i = account.getGoals().iterator();
                while (i.hasNext()) goals.add(i.next());*/

            }
        });

    }
}

