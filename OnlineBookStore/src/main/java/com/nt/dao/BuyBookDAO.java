package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BuyBookDAO {
	private  static final String BUY_BOOK_QWERY="UPDATE  TABLE_BOOK SET QUANTITY=QUANTITY-1 WHERE BOOKCODE=?";
	DBConnection db;
	public BuyBookDAO() {
		db=new DBConnection();
	}
	public int buy(String bookcode)throws Exception{
		
		Connection con=db.getPooledConnection();
		PreparedStatement ps=con.prepareStatement(BUY_BOOK_QWERY);
		ps.setString(1, bookcode);
		int rs=ps.executeUpdate();
		return rs;
	}
}
