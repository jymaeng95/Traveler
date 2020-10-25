package com.traveler.domain;

import lombok.Data;

@Data
public class GuestVO {
	private String guestId;
	private String hostId;
	private int planNo;
	private String title;
	private int accBno;
}
