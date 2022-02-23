package com.example.demo.user;

public class User  {
    private String name;
    private int userId;
    private String userDateOfBirth;
    private String userJob;
    private String userAddress;
    private boolean canOpenBankAccount;
    private boolean canTradeStocks;
    private String userPromotionalMessage;

    public User(String name) {
        this.name = name;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserDateOfBirth() {
        return userDateOfBirth;
    }

    public void setUserDateOfBirth(String userDateOfBirth) {
        this.userDateOfBirth = userDateOfBirth;
    }

    public String getUserJob() {
        return userJob;
    }

    public void setUserJob(String userJob) {
        this.userJob = userJob;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public boolean isCanOpenBankAccount() {
        return canOpenBankAccount;
    }

    public void setCanOpenBankAccount(boolean canOpenBankAccount) {
        this.canOpenBankAccount = canOpenBankAccount;
    }

    public boolean isCanTradeStocks() {
        return canTradeStocks;
    }

    public void setCanTradeStocks(boolean canTradeStocks) {
        this.canTradeStocks = canTradeStocks;
    }

    public String getUserPromotionalMessage() {
        return userPromotionalMessage;
    }

    public void setUserPromotionalMessage(String userPromotionalMessage) {
        this.userPromotionalMessage = userPromotionalMessage;
    }
}
