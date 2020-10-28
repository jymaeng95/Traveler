package com.traveler.mapper;

import java.util.ArrayList;
import java.util.List;

import com.traveler.domain.GuestVO;

public interface GuestMapper {
   
   public int insertGuest(GuestVO guest);
   public int isJoin(GuestVO guest);
   public int deleteGuest(GuestVO guest);
   public ArrayList<GuestVO> readguestAcc(String guestId);
}