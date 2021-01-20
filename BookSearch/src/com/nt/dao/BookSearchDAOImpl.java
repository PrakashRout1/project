package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.nt.bo.BookBO;

public class BookSearchDAOImpl implements BookSearchDAO {
	private static final String GET_BOOKS_BY_QUERY="SELECT BOOKID,BOOKNAME,AUTHOR,PRICE,STATUS,CATEGORY FROM BOOK_DETAILS WHERE CATEGORY=?";
	private Connection getPolledConnection()throws Exception{
		Connection con=null;
		InitialContext ic=null;
		DataSource ds=null;
		ic=new InitialContext();
		ds=(DataSource) ic.lookup("java:/comp/env/DsJndi");
		con=ds.getConnection();
		return con;
	}//getPolledConnection
	
	@Override
	public List<BookBO> findBooks(String category) throws Exception {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<BookBO> listBO=null;
		BookBO bo=null;
		try {
		//get connection from connection pool
		con=getPolledConnection();
		//create prepare statement 
		ps=con.prepareStatement(GET_BOOKS_BY_QUERY);
		//SET param values
		ps.setString(1,category);
			//execute the query
			rs=ps.executeQuery();
			//copy result set object record ListBO
			listBO=new ArrayList<BookBO>();
			while(rs.next()) {
				//copy record from rs to bo
				bo=new BookBO();
				bo.setBookId(rs.getInt(1));
				bo.setBookName(rs.getString(2));
				bo.setAuthor(rs.getString(3));
				bo.setPrice(rs.getFloat(4));
				bo.setStatus(rs.getString(5));
				bo.setCategory(rs.getString(6));
				//add BookBO object to ListBO
				listBO.add(bo);
			}//while
		}//try
		catch(SQLException se) {
			se.printStackTrace();
			throw se;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(ps!=null)
					ps.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
		return listBO;
	}//method

}//class
