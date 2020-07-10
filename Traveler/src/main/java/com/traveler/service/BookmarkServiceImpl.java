package com.traveler.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.traveler.domain.BookmarkVO;
import com.traveler.mapper.BookmarkMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {
	private BookmarkMapper mapper;
	
	@Override
	public boolean addBookmark(BookmarkVO bookmark) throws Exception {
		// TODO Auto-generated method stub
		log.info("�ϸ�ũ �߰�  : " + bookmark);
		int resultCount = mapper.bookmarkInsert(bookmark);
		return resultCount > 0;
	}

	@Override
	public ArrayList<BookmarkVO> getUserBookmark(BookmarkVO bookmark) throws Exception {
		// TODO Auto-generated method stub
		log.info("�ϸ�ũ �ҷ�����  :" + bookmark);
		return mapper.bookmarkRead(bookmark);
	}

	@Override
	public boolean deleteBookmark(BookmarkVO bookmark) throws Exception {
		// TODO Auto-generated method stub
		log.info("�ϸ�ũ ���� : "+bookmark);
		return mapper.bookmarkDelete(bookmark) > 0;
	}

	@Override
	public boolean checkBookmark(BookmarkVO bookmark) throws Exception {
		// TODO Auto-generated method stub
		log.info("�ϸ�ũ üũ"+bookmark);
		return mapper.checkBookmark(bookmark) > 0;
	}

}
