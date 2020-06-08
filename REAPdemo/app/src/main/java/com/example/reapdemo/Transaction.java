package com.example.reapdemo;

public class Transaction {

    private String UserName1;
    private String UserName2;
    private String Uid1;
    private String Uid2;
    private String Email1;
    private String Email2;
    private String Amount;
    private String DateTime;

    public Transaction() {
    }

    public Transaction(String userName1, String userName2, String email1, String email2, String amount, String dateTime) {
        UserName1 = userName1;
        UserName2 = userName2;
        Email1 = email1;
        Email2 = email2;
        Amount = amount;
        DateTime = dateTime;
    }

    public Transaction(String userName1, String userName2, String uid1, String uid2, String email1, String email2, String amount, String dateTime) {
        UserName1 = userName1;
        UserName2 = userName2;
        Uid1 = uid1;
        Uid2 = uid2;
        Email1 = email1;
        Email2 = email2;
        Amount = amount;
        DateTime = dateTime;
    }

    public String getUserName1() {
        return UserName1;
    }

    public void setUserName1(String userName1) {
        UserName1 = userName1;
    }

    public String getUserName2() {
        return UserName2;
    }

    public void setUserName2(String userName2) {
        UserName2 = userName2;
    }

    public String getUid1() {
        return Uid1;
    }

    public void setUid1(String uid1) {
        Uid1 = uid1;
    }

    public String getUid2() {
        return Uid2;
    }

    public void setUid2(String uid2) {
        Uid2 = uid2;
    }

    public String getEmail1() {
        return Email1;
    }

    public void setEmail1(String email1) {
        Email1 = email1;
    }

    public String getEmail2() {
        return Email2;
    }

    public void setEmail2(String email2) {
        Email2 = email2;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }
}
