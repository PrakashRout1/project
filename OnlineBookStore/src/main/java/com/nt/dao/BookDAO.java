package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.nt.dto.BookDTO;

public class BookDAO {
	private  static final String ADD_BOOK_QWERY="INSERT INTO TABLE_BOOK VALUES(?,?,?,?,?)";
	DBConnection db;
	public BookDAO() {
		db=new DBConnection();
	}
	public boolean addBook(BookDTO dto)throws Exception{
		Connection con=db.getPooledConnection();
		PreparedStatement ps=con.prepareStatement(ADD_BOOK_QWERY);
		ps.setString(1,dto.getBookCode());
		ps.setString(2,dto.getBookName());
		ps.setString(3,dto.getAuthor());
		ps.setFloat(4,dto.getPrice());
		ps.setInt(5, dto.getQuantity());
		int r=ps.executeUpdate();
		if(r==1)
			return true;
		else
			return false;
	}
}