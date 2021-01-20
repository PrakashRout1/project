package com.nt.dao;

import java.util.List;

import com.nt.bo.BookBO;

public interface BookSearchDAO {

	public List<BookBO> findBooks(String category)throws Exception;
}
