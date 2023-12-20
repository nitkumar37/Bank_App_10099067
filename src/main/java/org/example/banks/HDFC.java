package org.example.banks;

import org.example.Main;

public class HDFC implements RBI{
    int time_limit=3;
    int min_balance = 1000;
    float[] arr = new float[4];
    float ROI = 6F;
    //0-home loan, 1-education loan, 2- personal loan, 3-car loan
    Main obj;


    public HDFC(Main obj){
        arr[0] = 8.5F;
        arr[1] = 5.5F;
        arr[2] = 30F;
        arr[3] = 7.9F;
        this.obj = obj;
    }

    @Override
    public float depositMoney(float money,float balance) {
        balance += money;
        obj.printer("updated balance is "+balance);
        return balance;
    }

    @Override
    public float withdrawMoney(float money,float balance,int times) {
        if(balance-money < this.min_balance)obj.printer("Insufficient money");
        else {
            if (times >= this.time_limit){
                obj.printer("1% interest will be charged");
                balance=balance-money-(float)(0.01*money);
            }
            else{
                balance=(balance-money);
            }
        }
        obj.printer("updated balance is "+balance);
        return balance;
    }

    @Override
    public void openFD(float amount, int years) {
        float tempmoney=amount;
        int t=1;
        while(t<=years){
            tempmoney+=tempmoney*(this.ROI/100);
            obj.printer("Year "+t+" - "+tempmoney);
            t=t+1;
        }
        obj.printer("Total profit - "+(tempmoney-amount));

    }

    @Override
    public void applyLoan(int loanType, float amount, int years,float balance) {
        if ((this.min_balance * 2) < balance) obj.printer("Not eligible for loan");
        else {
            float tempmoney = amount;
            int t = 1;
            float roi = this.arr[loanType];
            while (t <= years) {
                tempmoney += tempmoney * (roi / 100);
                obj.printer("Year " + t + " - " + tempmoney);
                t = t + 1;
            }
            obj.printer("Total Interest paid - " + (tempmoney - amount));
        }
    }

    @Override
    public void applyCreditCard(float balance) {
        if ((this.min_balance * 2) < balance) obj.printer("Eligible for Credit Card");
        else obj.printer("Not eligible for the Credit Card");
    }
}
