package com.example.myapp.Model;

public class CardsList {

    private final String id;
    private final String winAmount;
    //when user scratch a card this value true so user won't scan card again
    private final boolean scratchStatus;

    public CardsList(String id, String winAmount, boolean scratchStatus) {
        this.id = id;
        this.winAmount = winAmount;
        this.scratchStatus = scratchStatus;
    }

    public String getId() {
        return id;
    }

    public String getWinAmount() {
        return winAmount;
    }

    public boolean isScratchStatus() {
        return scratchStatus;
    }
}
