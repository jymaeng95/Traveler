package com.traveler.service;


import com.traveler.domain.HostVO;

public interface HostService {
   
   public HostVO readHost(HostVO host) throws Exception;
   public boolean insertHost(HostVO host) throws Exception;
   public boolean deleteHost(HostVO host) throws Exception;
   public boolean updateHost(HostVO host) throws Exception;
}