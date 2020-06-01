package com.example.finassistant.dao;

import com.example.finassistant.domain.Account;
import com.example.finassistant.domain.Email;
import com.example.finassistant.domain.ExchangeCategory;
import com.example.finassistant.domain.Expense;
import com.example.finassistant.domain.ExpenseCategory;
import com.example.finassistant.domain.Goal;
import com.example.finassistant.domain.Income;
import com.example.finassistant.domain.IncomeCategory;
import com.example.finassistant.domain.Product;
import com.example.finassistant.domain.ShoppingList;
import com.example.finassistant.domain.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Initializer {

    protected abstract void eraseData();

    protected abstract AccountDAO getAccountDAO();
    protected abstract UserDAO getUserDAO();

    public void prepareData() throws ParseException {
        eraseData();
        User user1 = new User("John Wick",new Email("john@gmail.com"),"ProtectAllDogs",9755);
        Account account = new Account(1234, user1);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date parsedDate = formatter.parse("29/05/2020");
        Goal goal =  new Goal("Save 20 euros",20, parsedDate);
        account.addGoal(goal);

        /**
         * Initializing Income instances to add to Income set of the Account
         *
         */
        Income income1 = new Income(1000,formatter.parse("01/05/2020"), IncomeCategory.SALARY);
        Income income2 = new Income(15,formatter.parse("04/05/2020"), IncomeCategory.REGULAR);
        Income income3 = new Income(240,formatter.parse("16/05/2020"), IncomeCategory.REGULAR);
        Income income4 = new Income(5.99,formatter.parse("13/05/2020"), IncomeCategory.NONREGULAR);
        Income income5 = new Income(2.50,formatter.parse("09/05/2020"), IncomeCategory.NONREGULAR);
        Income income6 = new Income(450,formatter.parse("22/05/2020"), IncomeCategory.REGULAR);
        income1.setExchange(ExchangeCategory.ONLINE);
        income2.setExchange(ExchangeCategory.CASH);
        income3.setExchange(ExchangeCategory.ONLINE);
        income4.setExchange(ExchangeCategory.CASH);
        income5.setExchange(ExchangeCategory.ONLINE);
        income6.setExchange(ExchangeCategory.CASH);

        /**
         * Adding income to account
         */
        account.addIncome(income1);
        account.addIncome(income2);
        account.addIncome(income3);
        account.addIncome(income4);
        account.addIncome(income5);
        account.addIncome(income6);

        Expense expense1 = new Expense(230,formatter.parse("31/05/2020"), ExpenseCategory.OBLIGATION);
        Expense expense2 = new Expense(345,formatter.parse("29/05/2020"), ExpenseCategory.OBLIGATION);
        Expense expense3 = new Expense(129.56,formatter.parse("28/05/2020"), ExpenseCategory.OBLIGATION);
        Expense expense5 = new Expense(69.99,formatter.parse("02/05/2020"), ExpenseCategory.ENTERTAINMENT);
        Expense expense6 = new Expense(67.58,formatter.parse("06/05/2020"), ExpenseCategory.SHOPPING);
        expense1.setExchange(ExchangeCategory.CASH);
        expense2.setExchange(ExchangeCategory.CASH);
        expense3.setExchange(ExchangeCategory.CASH);
        expense5.setExchange(ExchangeCategory.ONLINE);
        expense6.setExchange(ExchangeCategory.CASH);

        /**
         * Adding expenses into account
         */
        account.addExpense(expense1);
        account.addExpense(expense2);
        account.addExpense(expense3);
        account.addExpense(expense5);
        account.addExpense(expense6);

        /**
         * Initializing Products and Shopping List
         * and adding the expenses from that list to Expenses Set
         *
         */
        Product product = new Product("toothbrush",2.99);
        Product product2 = new Product("vegetables",20.59);
        Product product3 = new Product("drinks",15.40);
        ShoppingList list = new ShoppingList("Super market essentials");
        list.addProduct(product);
        list.addProduct(product2);
        list.addProduct(product3);
        account.addList(list);
        //account.ShoppingExpenses(list);
        //account.getTemp().get(1).setDateEnd(formatter.parse("03/05/2020"));

        getAccountDAO().save(account);
        getUserDAO().save(user1);



    }
}
