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

import androidx.appcompat.app.AppCompatActivity;

import com.example.finassistant.R;
import com.example.finassistant.domain.Account;
import com.example.finassistant.domain.Goal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GoalActivity extends AppCompatActivity {

    ListView goallist;
    EditText amount;
    double amountValue;
    EditText title;
    String titleValue;
    EditText date;
    Date dateValue;
    //TODO prosvasi sto account
    Account account = new Account();
    ArrayList<Goal> goals = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);
    }

    public void onStart() {
        super.onStart();

        final FloatingActionButton add = findViewById(R.id.add);
        final Button submit = findViewById(R.id.submit);
        goallist = findViewById((R.id.goallist));
        amount = findViewById(R.id.txt_input);
        title = findViewById(R.id.title);
        date = findViewById(R.id.date);
        goallist.setVisibility(View.VISIBLE);
        add.setVisibility(View.VISIBLE);
        amount.setVisibility(View.GONE);
        title.setVisibility(View.GONE);
        date.setVisibility(View.GONE);
        submit.setVisibility(View.GONE);

        final ArrayAdapter arrayAdapter = new ArrayAdapter(GoalActivity.this, android.R.layout.simple_list_item_1, goals);
        goallist.setAdapter(arrayAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goallist.setVisibility(View.GONE);
                add.setVisibility(View.GONE);
                amount.setVisibility(View.VISIBLE);
                title.setVisibility(View.VISIBLE);
                date.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);
            }
        });

        //otan pataei submit na ftiaxnetai goal kai na mpainei stin goal list tou account
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goallist.setVisibility(View.VISIBLE);
                add.setVisibility(View.GONE);
                amount.setVisibility(View.GONE);
                title.setVisibility(View.GONE);
                date.setVisibility(View.GONE);
                submit.setVisibility(View.GONE);

                title = findViewById(R.id.title);
                titleValue = title.getText().toString();

                amount = findViewById(R.id.txt_input);
                amountValue = Double.parseDouble(amount.getText().toString());

                date = findViewById(R.id.date);
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    dateValue = formatter.parse(date.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                final Goal goal = new Goal(titleValue, amountValue, dateValue);

                //TODO apothikeusi sto account
                account.addGoal(goal);

                goals.add(goal);

                add.setVisibility(View.VISIBLE);
                //otan ksanapataei add, sta pedia exei tis times tou proigoumenou goal alla ama ta allakseis apothikevei kainourgio stoxo

                goallist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                        //emfanizei ta stoixeia tou expense
                        AlertDialog.Builder info = new AlertDialog.Builder(GoalActivity.this);
                        info.setTitle("Details");

                        double compl = (goals.get(position).getAmount() - goals.get(position).getCurrentAmount())/goals.get(position).getAmount();

                        info.setMessage("Title: " + goals.get(position).getTitle() +"\n\n" + "Amount: " +
                                goals.get(position).getAmount() + " €\n\n" + "End Date: " + goals.get(position).getEndDate() +
                                "\n\nCompletion: " + compl + "%");

                        //TODO apothikeusi sto account:
                        info.setNeutralButton("Add Amount", null);

                        info.setPositiveButton("OK", null);

                        info.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                AlertDialog.Builder delete = new AlertDialog.Builder(GoalActivity.this);
                                delete.setTitle("Delete?");
                                delete.setMessage("Are you sure you want to delete the goal?");
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
