package com.bridgelabz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class StockAccount {

    String name;
    int cash;
    int count;
    int total;
    CompanyShares[] companyShares;
    MyStack<String> transactionsStack;
    MyQueue<String> transactionsQueue;
    MyLinkedList<CompanyShares> list;

    public static void main(String[] args) {
        StockAccount stockAccount = new StockAccount("hello");
        stockAccount.addOrRemoveStock();
    }

    void addOrRemoveStock() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type 'buy' to buy stock or type 'sell' to sell stock or type print to display: ");
        String action = scanner.next();
        if (action.equals("buy")) {
            System.out.print("Enter amount, symbol, price of the stock: ");
            int amount = scanner.nextInt();
            String symbol = scanner.next();
            int price = scanner.nextInt();
            buy(amount, symbol, price);
            addOrRemoveStock();
        } else if (action.equals("sell")) {
            System.out.print("Enter amount, symbol, price of the stock: ");
            int amount = scanner.nextInt();
            String symbol = scanner.next();
            int price = scanner.nextInt();
            sell(amount, symbol, price);
            addOrRemoveStock();
        } else {
            printReport();
            System.out.println("\nTotal stock value: " + valueOf());
            printTransactionStack();
            printTransactionQueue();
        }
    }

    public StockAccount(String fileName) {
        transactionsStack = new MyStack<>();
        transactionsQueue = new MyQueue<>();
    }

    public int valueOf() {
        total = cash;
        for (int i = 0; i < count; i++) {
            total += companyShares[i].getValue();
        }
        return total;
    }


    public void printReport() {
        System.out.println("\n" + name + "\n");
        System.out.println("Symbol\tNo. of Shares\tPrice\tValue\tDate");
        for (int i = 0; i < count; i++) {
            CompanyShares shares = companyShares[i];
            System.out.println(shares.getSymbol() + "\t" + shares.getNumberOfShares() + "\t\t" + shares.getPrice()
                    + "\t" + shares.getValue() + "\t" + shares.getDateTime());
        }
    }


    public void buy(int amount, String symbol, int price) {
        String dateTime = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        CompanyShares companyShareObject = new CompanyShares(symbol, amount / price, price, dateTime);
        companyShares[count] = companyShareObject;
        count++;
        transactionsStack.push("Purchased");
        transactionsQueue.enqueue(dateTime);
    }

    public void sell(int amount, String symbol, int price) {
        int numberOfShares = amount / price;
        String dateTime = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        transactionsStack.push("Sold");
        transactionsQueue.enqueue(dateTime);
        for (int i = 0; i < count; i++) {
            if (companyShares[i].getSymbol().equals(symbol)) {
                companyShares[i].setNumberOfShares(companyShares[i].getNumberOfShares() - numberOfShares);
                companyShares[i].setDateTime(dateTime);
                break;
            }
        }
    }

    void printTransactionQueue() {
        System.out.println("\nTransactions Queue:");
        while (!transactionsQueue.isEmpty()) {
            System.out.println(transactionsQueue.dequeue());
        }
    }

    void printTransactionStack() {
        System.out.println("\nTransactions Stack:");
        while (!transactionsStack.isEmpty()) {
            System.out.println(transactionsStack.pop());
        }
    }
}

