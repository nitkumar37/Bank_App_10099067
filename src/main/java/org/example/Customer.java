package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Customer {
    String bankname,customerName, customerEmail, customerAddress, customerGender, customerAadhar, customerPhone;
    float balance;
    int times,csid;


    public Customer(){
        this.times=0;
    }
    public Customer(String customerName, String customerEmail, String customerAddress, String customerGender, String customerAadhar, String customerPhone, float balance,String bankname,int csid) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.customerGender = customerGender;
        this.customerAadhar = customerAadhar;
        this.customerPhone = customerPhone;
        this.balance = balance;
        this.times=0;
        this.bankname=bankname;
        this.csid=csid;
    }

    public Customer(Customer newcustomer){
        this.customerName = newcustomer.customerName;
        this.customerEmail = newcustomer.customerEmail;
        this.customerAddress = newcustomer.customerAddress;
        this.customerGender = newcustomer.customerGender;
        this.customerAadhar = newcustomer.customerAadhar;
        this.customerPhone = newcustomer.customerPhone;
        this.balance = newcustomer.balance;
        this.times= newcustomer.times;
        this.bankname= newcustomer.bankname;
        this.csid= newcustomer.csid;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(String customerGender) {
        this.customerGender = customerGender;
    }

    public String getCustomerAadhar() {
        return customerAadhar;
    }

    public void setCustomerAadhar(String customerAadhar) {
        this.customerAadhar = customerAadhar;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getBankname() {
        return bankname;
    }

    public int getCsid() {
        return csid;
    }
}
