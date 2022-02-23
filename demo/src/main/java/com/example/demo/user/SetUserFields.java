package com.example.demo.user;

public class SetUserFields {

    User user;
    private int userId;
    private String userDateofBirth;
    private String userJob;
    private String userAddress;
    private boolean canOpenBankAccount;
    private boolean canTradeStocks;
    private String userPromotionalMessage;


    public SetUserFields(User user, int userId, String userDateofBirth, String userJob, String userAddress,
                         boolean canOpenBankAccount, boolean canTradeStocks, String userPromotionalMessage) {

        this.user = user;
        this.userId = userId;
        this.userDateofBirth = userDateofBirth;
        this.userJob = userJob;
        this.userAddress = userAddress;
        this.canOpenBankAccount = canOpenBankAccount;
        this.canTradeStocks = canTradeStocks;
        this.userPromotionalMessage = userPromotionalMessage;

    }

    public void setTheFields() {

        user.setUserId(userId);
        user.setUserDateOfBirth(userDateofBirth);
        user.setUserJob(userJob);
        user.setUserAddress(userAddress);
        user.setCanOpenBankAccount(canOpenBankAccount);
        user.setCanTradeStocks(canTradeStocks);
        user.setUserPromotionalMessage(userPromotionalMessage);
    }


}
