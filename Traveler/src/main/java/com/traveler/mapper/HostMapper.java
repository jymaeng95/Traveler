package com.traveler.mapper;

import com.traveler.domain.HostVO;

public interface HostMapper {
   public HostVO readHost(HostVO host);
   public int insertHost(HostVO host);
   public int updateHost(HostVO host);
}