package com.example.demo.exception;

public class WrongDateTimeException extends Exception{
    public WrongDateTimeException(){
        super("Please enter valid date time");
    }
}
