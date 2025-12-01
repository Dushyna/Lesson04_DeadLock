package ait.bank;

import ait.bank.model.Account;
import ait.bank.service.Transfer;

public class BankDeadLockAppl {
    public static void main(String[] args) throws InterruptedException {
        Account dad = new Account(10_000);
        Account son = new Account(20_000);
        dad.debit(1000);
        son.debit(1000);
        Transfer transfer1 = new Transfer(dad, son, 900);
        Transfer transfer2 = new Transfer(son, dad, 900);
        Thread t1 = new Thread(transfer1);
        Thread t2 = new Thread(transfer2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("dad" + dad.getAccNumber() + " " + dad.getBalance());
        System.out.println("son" + son.getAccNumber() + " " + son.getBalance());
    }
}
