package com.example.demo.exseptions;

public class RepeatProductException extends Exception{

    private int art;

    public RepeatProductException(int art) {
        this.art = art;
    }

    @Override
    public String getMessage() {
        return "товар  с данным артикулом уже существует";
    }
}