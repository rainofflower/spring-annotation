package com.atguigu.handler;

/**
 * @author yanghui
 * @date 2020-08-14 12:09
 **/
@Handler(value = {@Handler.BindPipelineInfo(pipeline = MyPipeline1.class, order = 2),
                    @Handler.BindPipelineInfo(pipeline = MyPipeline2.class, order = 1)})
public class MyHandler3 extends CommandHandler{
}
