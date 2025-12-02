package ait.bank.service;

import ait.bank.model.Account;

public class Transfer implements Runnable {
    private static Object mutex = new Object();
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

        synchronized (mutex) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
                if (accountFrom.getBalance() >= amount) {
                    accountFrom.credit(amount);
                    accountTo. debit(amount);
                }

        }
    }
}
