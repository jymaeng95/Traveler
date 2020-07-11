package com.traveler.domain;

import lombok.Data;

@Data
public class MemberVO {
	private String userId;
	private String userPw;
	private String email;
	private String nickname;
	private String is_kakao;
	private String user_img;
}
