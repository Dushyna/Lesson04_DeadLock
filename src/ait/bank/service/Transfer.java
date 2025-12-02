package ait.bank.service;

import ait.bank.model.Account;

public class Transfer implements Runnable {
    private final Account accountFrom;
    private final Account accountTo;
    private final int amount;

    public Transfer(Account accountFrom, Account accountTo, int amount) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }


    @Override
    public void run() {

        Account lock1;
        Account lock2;

        if (accountFrom.getAccNumber() > accountTo.getAccNumber()) {
            lock1 = accountFrom;
            lock2 = accountTo;
        } else  {
            lock2 = accountFrom;
            lock1 = accountTo;
        }

        synchronized (lock2) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (lock1) {
                if (accountFrom.getBalance() >= amount) {
                    accountFrom.credit(amount);
                    accountTo.debit(amount);
                }
            }
        }
    }
}
