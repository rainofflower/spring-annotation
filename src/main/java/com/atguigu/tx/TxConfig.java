package com.atguigu.tx;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.InfrastructureAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration;
import org.springframework.transaction.annotation.TransactionManagementConfigurationSelector;
import org.springframework.transaction.annotation.Transactional;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * ?????????
 * 
 * ????????
 * 1?????????????
 * 		??????????????????Spring-jdbc???
 * 2?????????????JdbcTemplate??Spring???????????????????????????
 * 3???????????? @Transactional ??????????????????????
 * 4?? @EnableTransactionManagement ??????????????????????
 * 		@EnableXXX
 * 5?????????????????????????;
 * 		@Bean
 * 		public PlatformTransactionManager transactionManager()
 * 
 * 
 * ???
 * 1????@EnableTransactionManagement
 * 			????TransactionManagementConfigurationSelector???????л??????
 * 			???????????
 * 			AutoProxyRegistrar
 * 			ProxyTransactionManagementConfiguration
 * 2????AutoProxyRegistrar??
 * 			?????????????? InfrastructureAdvisorAutoProxyCreator ?????
 * 			InfrastructureAdvisorAutoProxyCreator????
 * 			???ú??????????????????????????????????????????????????????????з??????????????????е????
 * 
 * 3????ProxyTransactionManagementConfiguration ????????
 * 			1????????????????????????
 * 				1?????????????????????????????AnnotationTransactionAttributeSource???????????
 * 				2????????????????
 * 					TransactionInterceptor????????????????????????????????
 * 					??????? MethodInterceptor??
 * 					?????????е????
 * 						?????????????
 * 						????????????
 * 							1????????????????????
 * 							2????????PlatformTransactionManager???????????????????κ?transactionmanger
 * 								???????????а????????????PlatformTransactionManager??
 * 							3????????????
 * 								??????????????????????????????????????????
 * 								??????????????????????????????
 * 			
 */
@EnableTransactionManagement
@ComponentScan("com.atguigu.tx")
@Configuration
public class TxConfig {
	
	//?????
	@Bean
	public DataSource dataSource() throws Exception{
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("1314");
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false");
		return dataSource;
	}
	
	//
	@Bean
	public JdbcTemplate jdbcTemplate() throws Exception{
		//Spring??@Configuration????????????????м?????????????ε???????????????????
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
		return jdbcTemplate;
	}
	
	//????????????????????
	@Bean
	public PlatformTransactionManager transactionManager() throws Exception{
		return new DataSourceTransactionManager(dataSource());
	}
	

}
