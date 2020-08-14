package com.atguigu.handler;

import java.util.LinkedList;

/**
 * ${DESCRIPTION}
 *
 * @author yanghui
 * @date 2020-08-14 11:40
 **/
public class CommandHandlerPipeline {

    private LinkedList<CommandHandler> list = new LinkedList<>();

    public CommandHandlerPipeline addLast(String name,CommandHandler handler){
        System.out.println("--- add "+name);
        list.addLast(handler);
        return this;
    }

    public CommandHandlerPipeline addFirst(CommandHandler handler){
        list.addFirst(handler);
        return this;
    }

}
