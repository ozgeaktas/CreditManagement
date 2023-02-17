package com.ozgeakdas.credit.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String s) {
        super(s + " bulunamadı!");
    }
}
