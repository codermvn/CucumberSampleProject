package com.lh.test.framework.exceptions;

public class EndTestException extends RuntimeException {

    public EndTestException(){
        super("Error Occurred. Forced Stop Test");
    }

    public EndTestException(String msg){
        super(msg);
    }

}
