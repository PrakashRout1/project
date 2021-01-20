package com.nt.service;

import java.util.List;

import com.nt.dto.BookDTO;

public interface BookSearchService {

	public List<BookDTO> search(String category)throws Exception;
}
