package com.lh.test.framework.exceptions;

public class OptionNotFound extends RuntimeException {

    public OptionNotFound(){
        super("Error Occurred.");
    }

    public OptionNotFound(String msg){
        super(msg);
    }
}
