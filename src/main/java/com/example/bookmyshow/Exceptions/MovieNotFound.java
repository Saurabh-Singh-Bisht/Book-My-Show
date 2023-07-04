package com.example.bookmyshow.Exceptions;

public class MovieNotFound extends RuntimeException{
    public MovieNotFound(String msg){
        super(msg);
    }
}
