package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nt.dto.BookDTO;

public class ViewDAO {
	public static final String VIEW_BOOK_QWERY="SELECT * FROM TABLE_BOOK"; 
	DBConnection db=null;
	public ViewDAO() {
		db=new DBConnection();
	}
	public List<BookDTO> search()throws Exception{
		List<BookDTO> al=new ArrayList<BookDTO>();
		BookDTO dto=null;
		Connection con=db.getPooledConnection();
		PreparedStatement ps=con.prepareStatement(VIEW_BOOK_QWERY);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			dto=new BookDTO();
			dto.setBookCode(rs.getString(1));
			dto.setBookName(rs.getString(2));
			dto.setAuthor(rs.getString(3));
			dto.setPrice(rs.getFloat(4));
			dto.setQuantity(rs.getInt(5));
			al.add(dto);
		}
		return al;
	}
	public boolean updateBook(int bQty, String bCode) throws Exception {
		Connection con=db.getPooledConnection();
		PreparedStatement ps1 = con.prepareStatement("UPDATE TABLE_BOOK SET QUANTITY=? where BOOKCODE=?");
		ps1.setInt(1, bQty);
		ps1.setString(2, bCode);
		int i=ps1.executeUpdate();
		if(i!=0) {
			return true;
		}
		return false;
	}
}
