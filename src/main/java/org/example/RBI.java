package org.example;

public interface RBI {

    float interest_cc=2F,interest_loan=1F;
    public float depositMoney(float money,float balance);
    public float withdrawMoney(float money,float balance,int times);
    public void openFD(float amount, int years);
    public void applyLoan(int loanType, float amount,int years,float balance);
    public void applyCreditCard(float balance);
}
