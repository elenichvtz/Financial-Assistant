package com.example.finassistant.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finassistant.R;
import com.example.finassistant.domain.Goal;
import com.example.finassistant.ui.account.GoalPresenter;
import com.example.finassistant.ui.account.GoalView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * The type Goal activity.
 */
public class GoalActivity extends AppCompatActivity implements GoalView {

    /**
     * The Goal list.
     */
    ListView goalList;
    /**
     * The Amount.
     */
    EditText amount;
    /**
     * The Amount 2.
     */
    EditText amount2;
    /**
     * The Amount value.
     */
    double amountValue;
    /**
     * The Addedamount value.
     */
    double addedamountValue;
    /**
     * The Title.
     */
    EditText title;
    /**
     * The Title value.
     */
    String titleValue;
    /**
     * The Date.
     */
    EditText date;
    /**
     * The Date value.
     */
    Date dateValue;
    /**
     * The Presenter.
     */
    static GoalPresenter presenter;
    /**
     * The Goal.
     */
    Goal goal;
    /**
     * The Goals.
     */
    ArrayList<Goal> goals = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);
        presenter = new GoalPresenter(this);
    }

    public void onStart() {
        super.onStart();

        final FloatingActionButton add = findViewById(R.id.add);
        final Button submit = findViewById(R.id.submit);
        goalList = findViewById((R.id.goallist));
        amount = findViewById(R.id.txt_input);
        amount2 = findViewById(R.id.txt_input2);
        title = findViewById(R.id.title);
        date = findViewById(R.id.date);
        goalList.setVisibility(View.VISIBLE);
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
        goalList.setAdapter(arrayAdapter);

        goalList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder info = new AlertDialog.Builder(GoalActivity.this);
                info.setTitle("Details");

                double compl = 100 - ((goals.get(position).getAmount() - goals.get(position).getCurrentAmount())/goals.get(position).getAmount())*100;
                DecimalFormat numberFormat = new DecimalFormat("#.00");
                info.setMessage("Title: " + goals.get(position).getTitle() +"\n\n" + "Amount: " +
                        numberFormat.format(goals.get(position).getAmount()) + " €\n\n" + "End Date: " + goals.get(position).getEndDate() +
                        "\n\nCompletion: " + compl + "%");

                info.setPositiveButton("OK", null);

                info.setNeutralButton("Add Amount", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        AlertDialog.Builder add = new AlertDialog.Builder(GoalActivity.this);
                        add.setTitle("Add Saved Amount");

                        //get user input
                        final EditText input = new EditText(GoalActivity.this);

                        input.setInputType(InputType.TYPE_CLASS_NUMBER);

                        add.setView(input);

                        add.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                addedamountValue = Double.parseDouble(input.getText().toString());
                                while(iterator.hasNext()) {
                                }

                                Goal goal = goals.get(position);
                                presenter.getAccount().updateGoalExpenses(goal, addedamountValue);

                                Toast.makeText(GoalActivity.this,"Saved amount added.",Toast.LENGTH_SHORT).show();
                            }
                        });

                        add.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

                        add.show();
                    }
                });

                info.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder delete = new AlertDialog.Builder(GoalActivity.this);
                        delete.setTitle("Are you sure you want to delete the goal?");
                        delete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
                        delete.setPositiveButton("Delete", new AlertDialog.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Goal goal = goals.get(position);
                                presenter.getAccount().removeGoal(goal);
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
                goalList.setVisibility(View.GONE);
                add.setVisibility(View.GONE);
                amount.setVisibility(View.VISIBLE);
                title.setVisibility(View.VISIBLE);
                date.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);

                goal = new Goal();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goalList.setVisibility(View.VISIBLE);
                add.setVisibility(View.GONE);
                amount.setVisibility(View.GONE);
                title.setVisibility(View.GONE);
                date.setVisibility(View.GONE);
                submit.setVisibility(View.GONE);

                title = findViewById(R.id.title);
                titleValue = title.getText().toString();

                boolean isValidTitle = presenter.validateTitle(titleValue);
                if (isValidTitle) {
                    addTitle(titleValue);

                    amount = findViewById(R.id.txt_input);
                    if(amount.getText().toString().equals("")){
                        amount.setText("0");
                        amountValue = Double.parseDouble(amount.getText().toString());
                    }
                    amountValue = Double.parseDouble(amount.getText().toString());
                    boolean isValid = presenter.validateAmount(amountValue);
                    if (isValid) {

                        addAmount(amountValue);

                        date = findViewById(R.id.date);
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        String parsedDate = (date.getText().toString());
                        if (parsedDate.equals("")) {
                            addDate(new Date());
                        } else {
                            try {
                                dateValue = formatter.parse(parsedDate);
                                addDate(dateValue);
                            } catch (ParseException e) {
                                e.printStackTrace();

                            }
                        }
                        presenter.getAccount().addGoal(goal);

                        goals.add(goal);

                        add.setVisibility(View.VISIBLE);
                    }else{
                        add.setVisibility(View.VISIBLE);
                    }
                }else{
                    add.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void addTitle(String title){
        goal.setTitle(title);
    }

    @Override
    public void addAmount(Double amount) {
        goal.setAmount(amount);
    }

    @Override
    public void addDate(Date date) {
        goal.setEndDate(date);
    }

    @Override
    public void showErrorMessage(String title,String message){
        new AlertDialog.Builder(GoalActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }
}
