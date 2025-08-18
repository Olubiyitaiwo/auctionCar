package org.olubiyi.mycarauction.exceptions;

public class ItemOutOfStockException extends RuntimeException{
    public ItemOutOfStockException(String message) {

        super(message);
    }
}
