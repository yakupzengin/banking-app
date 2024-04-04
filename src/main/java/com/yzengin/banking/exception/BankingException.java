package com.yzengin.banking.exception;

public class BankingException extends RuntimeException{
    public BankingException(String message) {
        super(message);
    }

    public BankingException(String message, Throwable cause) {
        super(message, cause);
    }
}
