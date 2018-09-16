package com.atguigu.ext;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Lazy
@Repository
public class UserDao {

	public void insert() {
		System.out.println("UserDao...insert....");
	}

}
