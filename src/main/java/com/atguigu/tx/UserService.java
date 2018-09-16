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
		System.out.println("�������...");
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
			throw new RuntimeException("�ع�...");
		}*/
		//tm.commit(currentTransactionStatus);//��ȡ��ǰ�̵߳��������������ǰ�ύ���񣬺���spring���ٴ��ύ���񣬵����׳�����ύ������쳣
		Object currentProxy = AopContext.currentProxy();
		insertUser();
	}

}
