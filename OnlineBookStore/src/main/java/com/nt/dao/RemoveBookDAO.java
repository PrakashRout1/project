package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RemoveBookDAO {
	private  static final String REMOVE_BOOK_QWERY="DELETE FROM TABLE_BOOK WHERE BOOKCODE=?";
	DBConnection db;
	public RemoveBookDAO() {
		db=new DBConnection();
	}
	public boolean remove(String bookcode)throws Exception{
		Connection con=db.getPooledConnection();
		PreparedStatement ps=con.prepareStatement(REMOVE_BOOK_QWERY);
		ps.setString(1,bookcode);
		int r=ps.executeUpdate();
		if(r==1)
			return true;
		return false;
	}
}
