package com.traveler.service;

import org.springframework.stereotype.Service;

import com.traveler.domain.HostVO;
import com.traveler.mapper.HostMapper;
import com.traveler.mapper.MemberMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class HostServiceImpl implements HostService{

   private HostMapper mapper;
   
   @Override
   public HostVO readHost(HostVO host) throws Exception {
      // TODO Auto-generated method stub
      return mapper.readHost(host);
   }

   @Override
   public boolean insertHost(HostVO host) throws Exception {
      // TODO Auto-generated method stub
      return mapper.insertHost(host)>0;
   }


   @Override
   public boolean updateHost(HostVO host) throws Exception {
      // TODO Auto-generated method stub
      return mapper.updateHost(host)>0;
   }
   
   @Override
   public boolean addcurperson(HostVO host) throws Exception {
      // TODO Auto-generated method stub
      String str = host.getTitle().toString();
      String title = str.substring(str.indexOf("<")+1,str.indexOf(">"));
      host.setTitle(title);
      
      return mapper.addcurperson(host)>0;
   }

   @Override
   public boolean mincurperson(HostVO host) throws Exception {
      // TODO Auto-generated method stub      
      return mapper.mincurperson(host)>0;
   }

   @Override
   public HostVO readnum(int accbno) throws Exception {
      // TODO Auto-generated method stub
      return mapper.readnum(accbno);
   }

}