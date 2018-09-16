package com.atguigu.tx;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.DefaultTransactionStatus;


@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PlatformTransactionManager tm;
	
	@Transactional
	public void insertUser(){
		userDao.insert();
		//otherDao.other();xxx
		System.out.println("插入完成...");
		updateUser();
		//int i = 10/0;
	}
	
	
	
	public void updateUser() {
		
		userDao.update();
		int i = 10/0;
	}
	
	@Transactional
	public void activeTransaction() {
		updateUser();
		//TransactionStatus currentTransactionStatus = TransactionAspectSupport.currentTransactionStatus();
		/*currentTransactionStatus.setRollbackOnly();
		Object savepoint = currentTransactionStatus.createSavepoint();
		currentTransactionStatus.rollbackToSavepoint(savepoint);*/
		//currentTransactionStatus.isCompleted();
		/*if(true) {
			throw new RuntimeException("回滚...");
		}*/
		//tm.commit(currentTransactionStatus);//获取当前线程的事务管理器，提前提交事务，后续spring会再次提交事务，导致抛出多次提交事务的异常
		Object currentProxy = AopContext.currentProxy();
		insertUser();
	}

}
