package com.traveler.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.traveler.api.SpotAPI;
import com.traveler.domain.BookmarkVO;
import com.traveler.domain.PageVO;
import com.traveler.domain.SpotVO;
import com.traveler.service.BookmarkService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
public class SpotController {

	private static int totalCount;

	@Autowired
	private SpotAPI spot;

	private BookmarkService service;
	@RequestMapping(value="/spot/spot", method=RequestMethod.GET)
	public String spotIndex(@RequestParam(value="pageNum", defaultValue="1") String pageNo,
			@RequestParam(value="sigunguCode", defaultValue="") String sigunguCode, @RequestParam(value="contentTypeId",defaultValue="") String contentTypeId,Model model) throws Exception {

		totalCount = spot.getTotalCount(contentTypeId, sigunguCode);
		model.addAttribute("pageMaker", new PageVO(pageNo, totalCount,10));
		model.addAttribute("sigunguCode",sigunguCode);
		model.addAttribute("contentTypeId",contentTypeId);
		log.info("spot index");
		return "/spot/spot";
	}

	@RequestMapping(value="/spot/detail",method=RequestMethod.GET)
	public String spotDetail(SpotVO spotVO, Model model) throws Exception {
		log.info("spot detail >>" + spotVO);
		model.addAttribute("contentId",spotVO.getContentId());
		model.addAttribute("contentTypeId",spotVO.getContentTypeId());

		return "/spot/detail";
	}


	//spot 불러오기 
	@ResponseBody
	@RequestMapping(value="/spot/information",method = RequestMethod.GET)
	public List<SpotVO> getSpotInfo2(SpotVO spotVO, Model model) throws Exception{
		log.info("information 2");
		if(spotVO.getPageNo()==null) spotVO.setPageNo("1");
		if(spotVO.getSigunguCode()==null) spotVO.setSigunguCode("");
		if(spotVO.getContentTypeId()==null) spotVO.setContentTypeId("");
		log.info(spotVO);
		return spot.getInformation(spotVO, totalCount);
	}

	
	@ResponseBody
	@RequestMapping(value="/spot/details", method=RequestMethod.GET)
	public List<String> getDetailsInfo(SpotVO spotVO) throws Exception {
		log.info("details 2");
		return spot.getEachInformation(spotVO);
	}

	@ResponseBody
	@RequestMapping(value="/spot/keyword",method=RequestMethod.GET)
	public List<SpotVO> getKeywordInfo(SpotVO spotVO, String keyword, Model model) throws Exception {
		log.info("keyword");
		log.info(spotVO);
		if(spotVO.getPageNo()==null) spotVO.setPageNo("1");
		List<SpotVO> resultKeyword = spot.getKeywordInformation(spotVO, keyword);
		log.info(resultKeyword);
		return resultKeyword;
	}

	@ResponseBody
	@RequestMapping(value="/spot/check/bookmark", method=RequestMethod.GET)
	public boolean checkBookmark(BookmarkVO bookmark) throws Exception {
		log.info(bookmark);
		return service.checkBookmark(bookmark);
	}

	@ResponseBody
	@RequestMapping(value="/spot/add/bookmark", method=RequestMethod.POST)
	public boolean addBookmark(BookmarkVO bookmark) throws Exception {
		log.info(bookmark);
		return service.addBookmark(bookmark);
	}

	@ResponseBody
	@RequestMapping(value="/spot/delete/bookmark", method=RequestMethod.POST)
	public boolean deleteBookmark(BookmarkVO bookmark) throws Exception {
		log.info(bookmark);
		return service.deleteBookmark(bookmark);
	}

	//SpotVO ����� Ȥ�� @RequestParam ���� �ޱ� 
	@ResponseBody
	@RequestMapping(value="/spot/subimages", method=RequestMethod.GET)
	public List<String> getSubImages(SpotVO spotVO) throws Exception {
		log.info("get Subimages");
		return spot.getDiffImages(spotVO.getContentId(), spotVO.getContentTypeId(),spotVO.getNumOfRow());
	}
}
