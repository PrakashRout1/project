package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDAO {
	public static final String ADMIN="SELECT * FROM TABLE_ADMIN WHERE USERNAME=? AND PASSWORD=?"; 
	DBConnection db=null;
	public AdminDAO() {
		db=new DBConnection();
	}
	public boolean check(String username,String password) throws Exception {
		Connection con=db.getPooledConnection();
		PreparedStatement ps=con.prepareStatement(ADMIN);
		ps.setString(1, username);
		ps.setString(2,password);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
			return true;
		else
			return false;
	}
	
}
