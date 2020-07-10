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
	@RequestMapping(value="/spot/spot_index", method=RequestMethod.GET)
	public String spotIndex(@RequestParam(value="pageNum", defaultValue="1") String pageNo,
			@RequestParam(value="sigunguCode", defaultValue="") String sigunguCode, @RequestParam(value="contentTypeId",defaultValue="") String contentTypeId,Model model) throws Exception {

		totalCount = spot.getTotalCount(contentTypeId, sigunguCode);
		model.addAttribute("pageMaker", new PageVO(pageNo, totalCount));
		model.addAttribute("sigunguCode",sigunguCode);
		model.addAttribute("contentTypeId",contentTypeId);
		log.info("spot index");
		return "/spot/spot_index";
	}

	@RequestMapping(value="/spot/spot_detail",method=RequestMethod.GET)
	public String spotDetail(@RequestParam(value="contentId") String contentId,@RequestParam(value="contentTypeId") String contentTypeId, Model model) throws Exception {
		log.info("spot detail");
		model.addAttribute("contentId",contentId);
		model.addAttribute("contentTypeId",contentTypeId);
		log.info(contentId);
		log.info(contentTypeId);
		return "/spot/spot_detail";
	}


	//spot_index���� ���� ��ȯ
	@ResponseBody
	@RequestMapping(value="/spot/information",method = RequestMethod.GET)
	public List<String[]> getSpotInfo(@RequestParam(value="pageNo", defaultValue="1") String pageNo, @RequestParam(value="sigunguCode", defaultValue="") String sigunguCode, @RequestParam(value="contentTypeId",defaultValue="") String contentTypeId, Model model) throws Exception{
		log.info("������ ������ ������");
		ArrayList<String> contentIdList= spot.getContetnIdList(pageNo,sigunguCode,contentTypeId);
		ArrayList<NodeList> spotInfo = spot.getSpotInfo(contentIdList);
		List<String[]> info = new ArrayList<String[]>();

		//		List<SpotVO> info = new ArrayList<SpotVO>();
		log.info(spotInfo.get(0).item(1));
		for(int i=0; i<spotInfo.size();i++) {
			String[] info_arr = new String[7];
			for(int j=0;j<spotInfo.get(i).getLength();j++) {
				Node node = spotInfo.get(i).item(j);
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					Element element = (Element) node;
					info_arr[0] = SpotAPI.getTagValue("firstimage2", element);
					if(info_arr[0]==null) {
						info_arr[0] = "/resources/assets/img/spot_images/no_img.png";
					}
					info_arr[1] = SpotAPI.getTagValue("title", element);
					if(contentTypeId.equals("25")){
						info_arr[2]="";
					}else  {                  
						info_arr[2] = SpotAPI.getTagValue("addr1", element);
					}
					info_arr[3] = SpotAPI.getTagValue("overview", element);
					info_arr[3] = info_arr[3].replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
					info_arr[4] = SpotAPI.getTagValue("contentid", element);
					info_arr[5] = SpotAPI.getTagValue("contenttypeid", element);
					info_arr[6] = Integer.toString(totalCount);
				}
			}
			info.add(info_arr);
		}
		return info;
	}

	//���̵����� 
	@ResponseBody
	@RequestMapping(value="/spot/details", method=RequestMethod.GET)
	public List<String> getDetailsInfo(@RequestParam(value="contentId") String contentId, @RequestParam(value="contentTypeId") String contentTypeId) throws Exception{
		List<String> info = new ArrayList<String>();

		NodeList commonInfo = spot.getCommonInfo(contentId, contentTypeId);
		NodeList introduceInfo = spot.getIntroduceInfo(contentId, contentTypeId);
		//�������� 
		for(int i=0;i<commonInfo.getLength();i++) {
			Node node = commonInfo.item(i);
			if(node.getNodeType()==Node.ELEMENT_NODE) {
				Element element = (Element) node;
				if(SpotAPI.getTagValue("firstimage", element) == null)
					info.add("/resources/assets/img/spot_images/no_img.png");
				else info.add(SpotAPI.getTagValue("firstimage", element));
				info.add(SpotAPI.getTagValue("title", element));
				info.add(SpotAPI.getTagValue("overview", element));
				info.add(SpotAPI.getTagValue("addr1", element));
			}
		}
		log.info("test");

		//�Ұ����� 
		switch(contentTypeId) {
		case "12" :
			for(int i=0;i<introduceInfo.getLength();i++) {
				Node node = introduceInfo.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					Element element = (Element) node;
					info.add(SpotAPI.getTagValue("infocenter", element));
					info.add(SpotAPI.getTagValue("restdate", element));
					info.add(SpotAPI.getTagValue("usetime", element));
				}
			}
			break;
		case "14" :
			for(int i=0;i<introduceInfo.getLength();i++) {
				Node node = introduceInfo.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					Element element = (Element) node;
					info.add(SpotAPI.getTagValue("infocenterculture", element));
					info.add(SpotAPI.getTagValue("usefee", element));
					info.add(SpotAPI.getTagValue("usetimeculture", element));
				}
			}
			break;
		case "15" :
			for(int i=0;i<introduceInfo.getLength();i++) {
				Node node = introduceInfo.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					Element element = (Element) node;
					info.add(SpotAPI.getTagValue("eventhompage", element));
					info.add(SpotAPI.getTagValue("sponsor1tel", element));
					info.add(SpotAPI.getTagValue("playtime", element));
					info.add(SpotAPI.getTagValue("usetimefestival", element));
				}
			}
			break;
		case "25" :
			for(int i=0;i<introduceInfo.getLength();i++) {
				Node node = introduceInfo.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					Element element = (Element) node;
					info.add(SpotAPI.getTagValue("infocentertourcourse", element));
					info.add(SpotAPI.getTagValue("taketime", element));
					info.add(SpotAPI.getTagValue("theme", element));
				}
			}
			break;
		case "28" :
			for(int i=0;i<introduceInfo.getLength();i++) {
				Node node = introduceInfo.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					Element element = (Element) node;
					info.add(SpotAPI.getTagValue("infocenterleports", element));
					info.add(SpotAPI.getTagValue("restdateleports", element));
					info.add(SpotAPI.getTagValue("usefeeleports", element));
					info.add(SpotAPI.getTagValue("usetimeleports", element));
				}
			}
			break;
		case "32" :
			for(int i=0;i<introduceInfo.getLength();i++) {
				Node node = introduceInfo.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					Element element = (Element) node;
					info.add(SpotAPI.getTagValue("checkintime", element));
					info.add(SpotAPI.getTagValue("checkouttime", element));
					info.add(SpotAPI.getTagValue("infocenterlodging", element));
					info.add(SpotAPI.getTagValue("reservationurl", element));
					info.add(SpotAPI.getTagValue("reservationlodging", element));
				}
			}
			break;
		case "38" :
			for(int i=0;i<introduceInfo.getLength();i++) {
				Node node = introduceInfo.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					Element element = (Element) node;
					info.add(SpotAPI.getTagValue("infocentershopping", element));
					info.add(SpotAPI.getTagValue("opentime", element));
					info.add(SpotAPI.getTagValue("restdateshopping", element));
				}
			}
			break;
		case "39" :
			for(int i=0;i<introduceInfo.getLength();i++) {
				Node node = introduceInfo.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					Element element = (Element) node;
					info.add(SpotAPI.getTagValue("firstmenu", element));
					info.add(SpotAPI.getTagValue("infocenterfood", element));
					info.add(SpotAPI.getTagValue("opentimefood", element));
					info.add(SpotAPI.getTagValue("restdatefood", element));
				}
			}
			break;
		}
		return info;
	}

	@ResponseBody
	@RequestMapping(value="/spot/check/bookmark", method=RequestMethod.GET)
	public boolean checkBookmark(BookmarkVO bookmark) throws Exception {
		log.info(bookmark);
		return service.checkBookmark(bookmark);
	}

	@ResponseBody
	@RequestMapping(value="/spot/add/bookmark", method=RequestMethod.GET)
	public boolean addBookmark(BookmarkVO bookmark) throws Exception {
		log.info(bookmark);
		return service.addBookmark(bookmark);
	}

	@ResponseBody
	@RequestMapping(value="/spot/delete/bookmark", method=RequestMethod.GET)
	public boolean deleteBookmark(BookmarkVO bookmark) throws Exception {
		log.info(bookmark);
		return service.deleteBookmark(bookmark);
	}


	//SpotVO ����� Ȥ�� @RequestParam ���� �ޱ� 
	@ResponseBody
	@RequestMapping(value="/spot/subimages", method=RequestMethod.GET)
	public List<String> getSubImages(BookmarkVO bookmark) throws Exception {
		List<String> img_list = spot.getDiffImages(bookmark.getContentId(), bookmark.getContentTypeId());

		log.info(img_list);
		return img_list;

	}
}
