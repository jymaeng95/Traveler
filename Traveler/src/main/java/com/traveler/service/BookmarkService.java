package com.traveler.service;

import java.util.ArrayList;

import com.traveler.domain.BookmarkVO;

public interface BookmarkService {
	//�ϸ�ũ �߰� 
	public boolean addBookmark(BookmarkVO bookmark) throws Exception;
	
	//�ϸ�ũ �ҷ�����
	public ArrayList<BookmarkVO> getUserBookmark(BookmarkVO bookmark) throws Exception;
	
	//�ϸ�ũ ���� 
	public boolean deleteBookmark(BookmarkVO bookmark) throws Exception;
	
	//�ϸ�ũ Ȯ��
	public boolean checkBookmark(BookmarkVO bookmark) throws Exception;
}
