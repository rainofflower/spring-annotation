package com.atguigu.tx;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void insert(){
		String sql = "INSERT INTO `tbl_user`(username,age) VALUES(?,?)";
		String username = UUID.randomUUID().toString().substring(0, 5);
		jdbcTemplate.update(sql,username,19);
	}
	
	public void update() {
		String sql = "update `tbl_user` set username = ? where id = ?";
		//String username = UUID.randomUUID().toString().substring(0, 5);
		String username = "测试name";
		Integer id = 3;
		jdbcTemplate.update(sql,username,id);
	}

}
