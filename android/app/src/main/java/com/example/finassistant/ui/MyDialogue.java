package com.example.finassistant.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.finassistant.dao.AccountDAO;
import com.example.finassistant.domain.Account;
import com.example.finassistant.memorydao.AccountDAOMemory;

public class MyDialogue extends AppCompatDialogFragment {

    static AccountDAO accountDAO;
    static Account account;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        accountDAO = new AccountDAOMemory();
        account = accountDAO.find(1234);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Total Income Results")
                .setMessage("Your total income is: "+account.CalculateTotalIncome())
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }
}
