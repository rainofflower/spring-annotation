package com.atguigu.handler;

/**
 * ${DESCRIPTION}
 *
 * @author yanghui
 * @date 2020-08-14 12:09
 **/
@Handler(value={@Handler.BindPipelineInfo(pipeline = MyPipeline1.class,order = 1)})
public class MyHandler2 extends CommandHandler {

}
