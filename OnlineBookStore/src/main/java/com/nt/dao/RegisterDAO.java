package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.nt.dto.RegisterDTO;

public class RegisterDAO {
	public  static final String REGISTER_QWERY="INSERT INTO TABLE_USER VALUES(?,?,?,?,?,?,?)";
	DBConnection db;
	public RegisterDAO() {
		db=new DBConnection();
	}
	public boolean register(RegisterDTO dto)throws Exception{
		Connection con=db.getPooledConnection();
		PreparedStatement ps=con.prepareStatement(REGISTER_QWERY);
		ps.setString(1, dto.getUsername());
		ps.setString(2, dto.getPassword());
		ps.setString(3, dto.getfName());
		ps.setString(4, dto.getlName());
		ps.setString(5, dto.getAddr());
		ps.setInt(6, dto.getPhNo());
		ps.setString(7, dto.getMailId());
		int r=ps.executeUpdate();
		if(r==1)
			return true;
		else
			return false;
	}
}
