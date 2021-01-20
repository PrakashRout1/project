package com.nt.service;

import java.util.ArrayList;
import java.util.List;

import com.nt.bo.BookBO;
import com.nt.dao.BookSearchDAO;
import com.nt.dao.BookSearchDAOImpl;
import com.nt.dto.BookDTO;

public class BookSearchServiceImpl implements BookSearchService {
	BookSearchDAO dao;
	
	public BookSearchServiceImpl() {
	dao=new BookSearchDAOImpl(); 
	}
	
	@Override
	public List<BookDTO> search(String category) throws Exception {
		List<BookBO> listBO=null;
		List<BookDTO> listDTO=null;
		BookDTO dto=null;
		listBO=dao.findBooks(category);
		//convert ListBO to ListDTO
		listDTO=new ArrayList<BookDTO>();
		for(BookBO bo:listBO) {
			//copy each bo object to each Dto object
			dto=new BookDTO();
			dto.setBookId(bo.getBookId());
			dto.setBookName(bo.getBookName());
			dto.setAuthor(bo.getAuthor());
			dto.setPrice(Math.round(bo.getPrice()));
			dto.setCategory(bo.getCategory());
			dto.setStatus(bo.getStatus());
			//add dto class object to list collection
			listDTO.add(dto);
		}//for
		return listDTO;
	}

}
