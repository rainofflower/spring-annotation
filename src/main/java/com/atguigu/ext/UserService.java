package com.atguigu.ext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * Any beans specified are guaranteed to be created by the container before this bean.
 * @DependsOn注解可以使容器创建当前bean时先创建depends-on指定的bean
 * Spring创建普通bean的顺序是无序的，要保证某个bean在另一个bean创建之后创建，可以使用@DependsOn
 * 
 * @author yanghui
 *
 */
//@DependsOn("UserDao")
@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@EventListener(classes={ApplicationEvent.class})
	public void listen(ApplicationEvent event){
		System.out.println("UserService。。监听到的事件："+event);
	}
	
	public void add() {
		userDao.insert();
	}

}
