package com.example.bookmyshow.Exceptions;

public class TheaterNotFound extends RuntimeException{
    public TheaterNotFound(String msg){
        super(msg);
    }
}
