package org.example;

public class IDFC implements RBI{
    int time_limit=3;
    int min_balance = 1000;
    float[] arr = new float[4];
    float ROI = 6F;
    //0-home loan, 1-education loan, 2- personal loan, 3-car loan



    public IDFC(){
        arr[0] = 8.5F;
        arr[1] = 5.5F;
        arr[2] = 30F;
        arr[3] = 7.9F;
    }

    @Override
    public float depositMoney(float money,float balance) {
        balance += money;
        System.out.println("updated balance is "+balance);
        return balance;
    }

    @Override
    public float withdrawMoney(float money,float balance,int times) {
        if(balance-money < this.min_balance)System.out.println("Insufficient money");
        else {
            if (times >= this.time_limit){
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

    @Override
    public void openFD(float amount, int years) {
        float tempmoney=amount;
        int t=1;
        while(t<=years){
            tempmoney+=tempmoney*(this.ROI/100);
            System.out.println("Year "+t+" - "+tempmoney);
            t=t+1;
        }
        System.out.println("Total profit - "+(tempmoney-amount));

    }

    @Override
    public void applyLoan(int loanType, float amount, int years,float balance) {
        if ((this.min_balance * 2) < balance) System.out.println("Not eligible for loan");
        else {
            float tempmoney = amount;
            int t = 1;
            float roi = this.arr[loanType];
            while (t <= years) {
                tempmoney += tempmoney * (roi / 100);
                System.out.println("Year " + t + " - " + tempmoney);
                t = t + 1;
            }
            System.out.println("Total Interest paid - " + (tempmoney - amount));
        }
    }

    @Override
    public void applyCreditCard(float balance) {
        if ((this.min_balance * 2) < balance) System.out.println("Eligible for Credit Card");
        else System.out.println("Not eligible for the Credit Card");
    }
}
