package com.neil.oms.model;

/**
 * Created by neilmendum on 16/12/2017.
 */
public enum OrderStatusMessage {

    SUCCESS("Order successful", true),
    INVALID_SELL("Invalid sell order - you don't own this to sell", false),
    INVALID_SELL_NEGATIVE("Invalid sell order - you don't own enough of the stock", false),
    INVALID_BUY("Invalid buy order - not enough balance on the Portfolio", false),
    INVALID_NEGATIVE("You can't buy or sell a zero or negative amount", false);

    private String message;
    private boolean valid;

    OrderStatusMessage(String message, boolean valid) {
        this.message = message;
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public boolean isValid() {
        return valid;
    }

}
