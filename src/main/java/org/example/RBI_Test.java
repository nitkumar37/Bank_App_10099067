package org.example;

public class RBI_Test {
    public static float depositMoney(float money,float balance) {
        balance += money;
        System.out.println("updated balance is "+balance);
        return balance;
    }
    public static float withdrawMoney(float money,float balance,int times,float min_balance, int time_limit) {
        if(balance-money < min_balance)System.out.println("Insufficient money");
        else {
            if (times >= time_limit){
                System.out.println("1% interest will be charged");
                balance=balance-(float)(0.01*balance);
            }
            else{
                balance=(balance-money);
            }
        }
        System.out.println("updated balance is "+balance);
        return balance;
    }

    public static void openFD(float amount, int years,float ROI) {
        float tempmoney=amount;
        int t=1;
        while(t<=years){
            tempmoney+=tempmoney*(ROI/100);
            System.out.println("Year "+t+" - "+tempmoney);
            t=t+1;
        }
        System.out.println("Total profit - "+(tempmoney-amount));

    }

    public static void applyLoan(float roi, float amount, int years,float balance,float min_balance) {
        if ((min_balance * 2) < balance) System.out.println("Not eligible for loan");
        else {
            float tempmoney = amount;
            int t = 1;
            while (t <= years) {
                tempmoney += tempmoney * (roi / 100);
                System.out.println("Year " + t + " - " + tempmoney);
                t = t + 1;
            }
            System.out.println("Total Interest paid - " + (tempmoney - amount));
        }
    }

    public static void applyCreditCard(float balance,float min_balance) {
        if ((min_balance * 2) < balance) System.out.println("Eligible for Credit Card");
        else System.out.println("Not eligible for the Credit Card");
    }
}
