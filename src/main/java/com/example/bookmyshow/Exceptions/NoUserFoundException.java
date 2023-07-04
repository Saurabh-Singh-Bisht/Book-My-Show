package com.example.bookmyshow.Exceptions;

public class NoUserFoundException extends RuntimeException{
    public NoUserFoundException(String msg){
        super(msg);
    }
}
