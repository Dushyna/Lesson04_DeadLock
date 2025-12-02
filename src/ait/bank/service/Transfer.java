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
        Account firstLock = accountFrom.getAccNumber() > accountTo.getAccNumber()  ? accountTo : accountFrom;
        Account secondtLock = accountFrom.getAccNumber() > accountTo.getAccNumber()  ? accountFrom : accountTo;
        firstLock.lock();

            try {
                Thread.sleep(1000);
                secondtLock.lock();
                try {
                    if (accountFrom.getBalance() >= amount) {
                        accountFrom.credit(amount);
                        accountTo.debit(amount);
                    }
                } finally {
                    secondtLock.unlock();
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                firstLock.unlock();
            }

            }
}
