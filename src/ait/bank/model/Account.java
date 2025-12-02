package ait.bank.model;

import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private final Lock mutex = new ReentrantLock();
    private final int accNumber;
    private int balance;

    public Account(int accNumber) {
        this.accNumber = accNumber;
    }

    public int getAccNumber() {
        return accNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void debit(int amount) {
        balance = balance + amount;
    }

    public void credit(int amount) {
        balance = balance - amount;
    }

    public void lock(){
        mutex.lock();
    }

    public void unlock(){
        mutex.unlock();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accNumber == account.accNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(accNumber);
    }
}
