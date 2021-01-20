package com.nt.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.InitialContext;
import javax.sql.DataSource;


public class DBConnection {
	public Connection getPooledConnection() throws Exception {
		Connection con=null;
		InitialContext ic=null;
		DataSource ds=null;
		ic=new InitialContext();
		ds=(DataSource) ic.lookup("DsJndi");
		con=ds.getConnection();
		
		return con;
	}
}
