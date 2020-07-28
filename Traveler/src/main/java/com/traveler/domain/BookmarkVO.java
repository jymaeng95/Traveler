package com.traveler.domain;

import lombok.Data;

@Data	
public class BookmarkVO {
	private String userId;
	private String contentId;
	private String contentTypeId;
	private String title;
	private String addr;
	private String img_src;
	private String overview;
	private String mapX;
	private String mapY;
	private MemberVO memberVO;
}
