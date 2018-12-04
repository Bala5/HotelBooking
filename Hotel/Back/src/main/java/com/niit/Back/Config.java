package com.niit.Back;

import java.util.Properties;

import java.sql.*; 

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages="com.niit.Back")
@EnableTransactionManagement
 
class OracleCon{  
public static void main(String args[]){  
try{    
Class.forName("oracle.jdbc.driver.OracleDriver");  
   
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:xe","username","password");  
   
con.close();  
  
}catch(Exception e){ System.out.println(e);}  
  
}  
	
	
@Bean(name="myprop")
Properties m2()
{
	Properties p=new Properties();
	p.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	p.setProperty("hibernate.show_sql", "true");
	p.setProperty("hibernate.hbm2ddl.auto", "update");
	return p;
}


@Autowired
@Bean(name="sf")
LocalSessionFactoryBean m3(javax.sql.DataSource mydatasource,Properties myprop)
{
	LocalSessionFactoryBean lsfb=new LocalSessionFactoryBean();
	lsfb.setDataSource(mydatasource);
	lsfb.setHibernateProperties(myprop);
	lsfb.setPackagesToScan("com.niit.Back");
	return lsfb;
}

@Bean(name="mytrans")
@Autowired
HibernateTransactionManager m4(SessionFactory sf)
{
	HibernateTransactionManager htm=new HibernateTransactionManager();
	htm.setSessionFactory(sf);
	return htm;
	
}
	
}

