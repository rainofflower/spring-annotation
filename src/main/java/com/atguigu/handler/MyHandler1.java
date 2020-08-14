package com.atguigu.handler;

/**
 * @author yanghui
 * @date 2020-08-14 12:09
 **/
@Handler(value = {@Handler.BindPipelineInfo(pipeline = MyPipeline2.class, order = 5)})
public class MyHandler1 extends CommandHandler{
}
