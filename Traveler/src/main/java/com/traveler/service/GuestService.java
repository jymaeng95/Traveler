package com.traveler.service;

import java.util.ArrayList;
import java.util.List;

import com.traveler.domain.GuestVO;

public interface GuestService {
   public boolean insertGuest(GuestVO guest) throws Exception;
   public int isJoin(GuestVO guest) throws Exception;
   public boolean deleteGuest(GuestVO guest) throws Exception;
   public ArrayList<GuestVO> readguestAcc(String guestId) throws Exception;
}