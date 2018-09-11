package com.atguigu.ext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * Any beans specified are guaranteed to be created by the container before this bean.
 * @DependsOnע�����ʹ����������ǰbeanʱ�ȴ���depends-onָ����bean
 * Spring������ͨbean��˳��������ģ�Ҫ��֤ĳ��bean����һ��bean����֮�󴴽�������ʹ��@DependsOn
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
		System.out.println("UserService�������������¼���"+event);
	}
	
	public void add() {
		userDao.insert();
	}

}
