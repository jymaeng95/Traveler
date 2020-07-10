package com.traveler.mapper;

import java.util.ArrayList;

import com.traveler.domain.BookmarkVO;

public interface BookmarkMapper {
	// Member CRUD ���� 
	// Create
	public int bookmarkInsert(BookmarkVO bookmark);
	
	// Read
	public ArrayList<BookmarkVO> bookmarkRead(BookmarkVO bookmark);

	// Update
	//public int bookmarkUpdate(BookmarkVO bookmark);
	
	// Delete
	public int bookmarkDelete(BookmarkVO bookmark);
	
	public int checkBookmark(BookmarkVO bookmark);
}
