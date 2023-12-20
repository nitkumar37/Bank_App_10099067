package org.example;

import java.util.logging.Logger;
import org.example.banks.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.LogManager;

public class Main implements Runnable{

    BufferedReader buff;
    InputStreamReader isr;
    LogManager lgmngr;
    Logger log;
    int bankclick,selectedOperation,csid,accountexistance,currentcsid;
    String selectedBank;

    public Main() {
        if(isr == null)
            isr = new InputStreamReader(System.in);
        if(buff==null)
            buff = new BufferedReader(isr);
        customerdb = new HashMap<>();
        csid=0;
        customerdb.put("AXIS", new HashMap<>());
        customerdb.put("ICICI", new HashMap<>());
        customerdb.put("IDFC", new HashMap<>());
        customerdb.put("HDFC", new HashMap<>());
        customerdb.put("SBI", new HashMap<>());
        lgmngr = LogManager.getLogManager();
        log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);
    }
    public void printer(String s){
        log.log(Level.INFO, s);
    }

    Map<String,Map<Integer, Customer>> customerdb;


    //for reading string buffer
    public String string_reader(BufferedReader buff){
        try {
            return buff.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return " ";
    }

    //for reading integer buffer
    public int integer_reader(BufferedReader buff){
        try {
            return Integer.parseInt(buff.readLine());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    //for reading float buffer
    public float float_reader(BufferedReader buff){
        try {
            return Float.parseFloat(buff.readLine());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return -1F;
    }

    public void sleeping(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        }
        catch (InterruptedException e) {
            printer("Interrupted "
                    + "while Sleeping");
        }
    }


    @Override
    public void run(){

        boolean welcome=true;
        while(welcome) {
            Main obj =this;

//            wrongbank:

            obj.printer("Welcome to IBS\nPlease select your bank\n1. ICICI\n2. HDFC\n3. SBI\n4. AXIS\n5. IDFC");
            obj.bankclick = obj.integer_reader(obj.buff);


            //Bank object initialisation
            RBI mRBI = null;
            switch (obj.bankclick) {
                case 1:
                    obj.selectedBank="ICICI";
                    mRBI = new ICICI(obj);
                    break;
                case 2:
                    obj.selectedBank="HDFC";
                    mRBI = new HDFC(obj);
                    break;
                case 3:
                    obj.selectedBank="SBI";
                    mRBI = new SBI(obj);
                    break;
                case 4:
                    obj.selectedBank="AXIS";
                    mRBI = new AXIS(obj);
                    break;
                case 5:
                    obj.selectedBank="IDFC";
                    mRBI = new IDFC(obj);
                    break;
                default:
                    obj.printer("Please select correct input");
//                    obj.sleeping(30);
//                    break wrongbank;
            }


            //user details entry
            Customer newcustomer = null;

            obj.printer("Do you have an account\n1. Yes\n2. No");
            obj.accountexistance= obj.integer_reader(obj.buff);
            //for new customer
            while(obj.csid==0 && obj.accountexistance==1){
                obj.printer("Account Doesnt Exist\nDo you have an account\n1. Yes\n2. No");
                obj.accountexistance= obj.integer_reader(obj.buff);
            }

            obj.printer("Welcome to " + obj.selectedBank+" Bank");
            if(obj.accountexistance == 2){
                newcustomer = new Customer();
                newcustomer.bankname = obj.selectedBank;
                newcustomer.csid=obj.csid;
                obj.csid+=1;

                obj.printer("Enter name - ");
                newcustomer.customerName = obj.string_reader(obj.buff);


                obj.printer("Enter email - ");
                newcustomer.customerEmail = obj.string_reader(obj.buff);

                obj.printer("Enter address - ");
                newcustomer.customerAddress = obj.string_reader(obj.buff);

                obj.printer("Enter gender - ");
                newcustomer.customerGender = obj.string_reader(obj.buff);

                obj.printer("Enter aadhar - ");
                newcustomer.customerAadhar = obj.string_reader(obj.buff);

                obj.printer("Enter phone no - ");
                newcustomer.customerPhone = obj.string_reader(obj.buff);

                obj.printer("Enter initial amount (minimum 1000) - ");
                newcustomer.balance = obj.float_reader(obj.buff);
                while (newcustomer.balance < 1000) {
                    obj.printer("Please Enter more than 1000");
                    newcustomer.balance = obj.float_reader(obj.buff);
                }
                obj.printer("Your CSID is "+obj.csid);
                obj.currentcsid=obj.csid;
                obj.csid+=1;

            }
            //for existing customer
            else{
                obj.printer("Enter your csid - ");
                obj.currentcsid = obj.integer_reader(obj.buff);
                if(obj.customerdb.get(obj.selectedBank).containsKey(obj.currentcsid)){
                    newcustomer = new Customer(obj.customerdb.get(obj.selectedBank).getOrDefault(obj.currentcsid,null));
                    obj.printer("Welcome "+ newcustomer.customerName);
                }
                else obj.printer("Customer doesnt exist");
            }



            boolean op = true;
            while (op) {
                obj.printer("Select your choice\n1. Deposit\n2. Withdrawl\n3. OpenFD\n4. Apply Loan\n5. Apply CC");
                obj.selectedOperation = obj.integer_reader(obj.buff);
                obj.printer("Customer Selected " + obj.selectedOperation);

                //performing operations
                float money;
                int years, Loantype;
                switch (obj.selectedOperation) {
                    case 1:
                        obj.printer("Enter money to deposit - ");
                        money = obj.float_reader(obj.buff);
                        newcustomer.balance = mRBI.depositMoney(money, newcustomer.balance);
                        break;
                    case 2:
                        obj.printer("Enter money to withdraw - ");
                        money = obj.float_reader(obj.buff);
                        newcustomer.balance = mRBI.withdrawMoney(money, newcustomer.balance, newcustomer.times);
                        newcustomer.times += 1;
                        break;
                    case 3:
                        obj.printer("Enter the amount - ");
                        money = obj.float_reader(obj.buff);
                        obj.printer("Enter the years - ");
                        years = obj.integer_reader(obj.buff);
                        mRBI.openFD(money, years);
                        break;
                    case 4:
                        obj.printer("Enter the loan type - \n1. Home Loan\n2. Education Loan\n3. Personal Loan\n4. Car Loan");
                        Loantype = obj.integer_reader(obj.buff);
                        while (Loantype < 0 || Loantype > 3) {
                            obj.printer("Select the correct Loan Type\nEnter the loan type - \n1. Home Loan\n2. Education Loan\n3. Personal Loan\n4. Car Loan");
                            Loantype = obj.integer_reader(obj.buff);
                        }
                        obj.printer("Enter the amount - ");
                        money = obj.float_reader(obj.buff);
                        obj.printer("Enter the years - ");
                        years = obj.integer_reader(obj.buff);
                        mRBI.applyLoan(Loantype, money, years, newcustomer.balance);
                        break;
                    case 5:
                        mRBI.applyCreditCard(newcustomer.balance);
                        break;
//                    case 6:
//                        op=false;
//                        break banknumber;
                    default:
                        obj.printer("Please select a correct operation");
                        obj.sleeping(5);
                        continue;
                }
                obj.printer("Do you want to continue:\n1.Yes\n2.No");
                int op_options = obj.integer_reader(obj.buff);
                op = (op_options == 1);

            }
//            banknumber:

            obj.customerdb.get(obj.selectedBank).put(obj.currentcsid, newcustomer);
            obj.printer("Do you want to see the number of customers in each bank\n1. Yes\n2. No");
            int op_options = obj.integer_reader(obj.buff);
            if(op_options==1){
                for (Map.Entry<String, Map<Integer,Customer>> entry : obj.customerdb.entrySet())
                    obj.printer(entry.getKey()+" - "+entry.getValue().size());
                obj.sleeping(5);
            }

        }
    }

    public static void main(String[] args) {
        Main obj = new Main();
        Thread t = new Thread((obj));
        t.start();
    }
}