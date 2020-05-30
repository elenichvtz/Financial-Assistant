package com.example.finassistant.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finassistant.R;
import com.example.finassistant.domain.Expense;
import com.example.finassistant.domain.Goal;
import com.example.finassistant.domain.IncomeCategory;
import com.example.finassistant.ui.account.AccountPresenter;
import com.example.finassistant.ui.account.AccountView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class GoalActivity extends AppCompatActivity implements AccountView {

    ListView goallist;
    EditText amount;
    EditText amount2;
    double amountValue;
    double addedamountValue;
    EditText title;
    String titleValue;
    EditText date;
    Date dateValue;
    static AccountPresenter presenter;
    ArrayList<Goal> goals = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);
        presenter = new AccountPresenter(this);
    }

    public void onStart() {
        super.onStart();

        final FloatingActionButton add = findViewById(R.id.add);
        final Button submit = findViewById(R.id.submit);
        goallist = findViewById((R.id.goallist));
        amount = findViewById(R.id.txt_input);
        amount2 = findViewById(R.id.txt_input2);
        title = findViewById(R.id.title);
        date = findViewById(R.id.date);
        goallist.setVisibility(View.VISIBLE);
        add.setVisibility(View.VISIBLE);
        amount.setVisibility(View.GONE);
        amount2.setVisibility(View.GONE);
        title.setVisibility(View.GONE);
        date.setVisibility(View.GONE);
        submit.setVisibility(View.GONE);

        final Iterator<Goal> iterator = presenter.getAccount().getGoals().iterator();
        while(iterator.hasNext()) {
            goals.add(iterator.next());
        }

        final ArrayAdapter arrayAdapter = new ArrayAdapter(GoalActivity.this, android.R.layout.simple_list_item_1, goals);
        goallist.setAdapter(arrayAdapter);

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
                info.setPositiveButton("OK", null);

                info.setNeutralButton("Add Amount", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder add = new AlertDialog.Builder(GoalActivity.this);
                        add.setTitle("Add Saved Amount");

                        //TODO den emfanizei to amount2 gia na eisagei o xristis

                        //get user input
                        amount2.setVisibility(View.VISIBLE);
                        amount2 = findViewById(R.id.txt_input2);
                        addedamountValue = Double.parseDouble(amount.getText().toString());

                        AlertDialog.Builder added = new AlertDialog.Builder(GoalActivity.this);

                        added.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                while(iterator.hasNext()) {
                                    Goal e = iterator.next();
                                    if(e.hashCode()==goals.get(position).hashCode()) {
                                        e.GoalCompletion(addedamountValue);
                                        arrayAdapter.notifyDataSetChanged();
                                        break;
                                    }
                                }
                                Toast.makeText(GoalActivity.this,"Saved amount added.",Toast.LENGTH_SHORT).show();
                            }
                        });

                        added.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

                        amount2.setVisibility(View.GONE);
                        added.show();
                    }
                });

                info.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder delete = new AlertDialog.Builder(GoalActivity.this);
                        delete.setTitle("Are you sure you want to delete the goal?");
                        final int positionToRemove = position;
                        delete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
                        delete.setPositiveButton("Delete", new AlertDialog.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //TODO diagrafi apo to account
                                presenter.getAccount().getGoals().remove(positionToRemove);     //den to diagrafei
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
                presenter.getAccount().addGoal(goal);

                goals.add(goal);

                add.setVisibility(View.VISIBLE);
                //otan ksanapataei add, sta pedia exei tis times tou proigoumenou goal alla ama ta allakseis apothikevei kainourgio stoxo


            }
        });

    }

    @Override
    public void addAmount(Double amount) {

    }

    @Override
    public void addDate(Date date) {

    }

    @Override
    public void addCategory(IncomeCategory category) {

    }

    @Override
    public void showErrorMessage(String title, String message) {

    }
}
