package com.atguigu.bean;

import org.springframework.beans.factory.annotation.Autowired;

public class MessageServiceImpl implements MessageService {

    @Autowired
    private Yellow yellow;

//    private MessageServiceImpl(){}

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public Yellow getYellow(){
        return yellow;
    }
}
