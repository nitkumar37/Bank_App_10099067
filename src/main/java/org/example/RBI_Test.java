package org.example;

public class RBI_Test {
    public float depositMoney(float money,float balance) {
        balance += money;
        return balance;
    }
    public float withdrawMoney(float money,float balance,int times,float min_balance, int time_limit) {
        if(balance-money > min_balance){
            if (times >= time_limit){
                System.out.println("1% interest will be charged");
                balance=balance-money-(float)(0.01*money);
            }
            else{
                balance=(balance-money);
            }
        }
        return balance;
    }

    public float openFD(float amount, int years,float ROI) {
        float tempmoney=amount;
        int t=1;
        while(t<=years){
            tempmoney+=tempmoney*(ROI/100);
            t=t+1;
        }
        return (tempmoney-amount);

    }

    public String applyLoan(float roi, float amount, int years,float balance,float min_balance) {
        if ((min_balance * 2) > balance) {
            return "Not eligible";
        }
        else {
            float tempmoney = amount;
            int t = 1;
            while (t <= years) {
                tempmoney += tempmoney * (roi / 100);
                t = t + 1;
            }
            return (tempmoney-amount) +"";
        }
    }

    public String applyCreditCard(float balance,float min_balance) {
        if ((min_balance * 2) < balance){
            return "eligible";
        }
        else return "not eligible";
    }
}
