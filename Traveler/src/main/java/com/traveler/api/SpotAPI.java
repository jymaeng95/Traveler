package com.traveler.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.traveler.domain.SpotVO;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class SpotAPI {
	//	final String serviceKey ="SHUTUe8Ya9tRYNvxndPl0GuDMGD%2F8T4d2LFqklaI9yoGzpnlZPEmo3uUsQ6BGhsVr%2F8vQZFAhkPg%2Fc4kGh1%2Big%3D%3D";
	final String serviceKey ="WPuoTd67PSQd4jXck%2FRkGCLmjVLTCkJWVcBKcZk36xiTy6meeE%2FuWi4OKVduoXWijK8jCUvaYXhYHnqSEkWlPw%3D%3D";
	//	final String serviceKey ="gr8r1Nu%2FamiYcpoKwNRrDhgCWT8T3dJPxG1%2F2MuEzrCiSq0m4%2B8%2BUqf5LqJpVfIBWrgndZHrRpZy27PUCT21rQ%3D%3D";
	//	final String serviceKey ="iv8Bz7xZDYyuYRnhmBekQj5GV732OijidErVpJets0Bw9rMM6FEWPF3t4Ow%2FIJ091nOnUnXFq%2FFjas8TzW13pA%3D%3D";

	final String TMAP_KEY = "l7xx15e7f0ab6ce4456f9a97564f50cf5e2f";
	// tag���� ������ �������� �޼ҵ�
	public static String getTagValue(String tag, Element eElement) {
		try {
			NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
			Node nValue = (Node) nlList.item(0);
			if(nValue == null) {
				System.out.println("null");
				return null;
			}
			System.out.println(nValue.getNodeValue());
			return nValue.getNodeValue();
		} catch(NullPointerException e) {
			System.out.println("catch");
			return null;
		}
	}

	//���� ���� ��� ���(��ü���� �κ�)
	public ArrayList<NodeList> getSpotInfo(ArrayList<String> contentIdList) throws Exception {
		ArrayList<NodeList> infoList = new ArrayList<NodeList>();
		for (int i=0;i<contentIdList.size();i++) {
			String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?"
					+ "serviceKey="+serviceKey
					+ "&numOfRows=1"
					+ "&pageNo=1"
					+ "&MobileOS=ETC"
					+ "&areacodeYN=Y"
					+ "&catcodeYN=Y"
					+ "&addrinfoYN=Y"
					+ "&MobileApp=AppTest"
					+ "&contentId="+contentIdList.get(i)
					+ "&contentTypeId="
					+ "&defaultYN=Y"
					+ "&firstImageYN=Y"
					+ "&mapinfoYN=Y"
					+ "&overviewYN=Y&";

			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(url);

			// root tag 
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			// �Ľ��� tag
			NodeList nList = doc.getElementsByTagName("item");
			infoList.add(nList);
			Thread.sleep(100);
		}
		return infoList;
	}

	/*
	 * ���ֵ� - 39 (total:321) �������� - 3 (total:144) ���ֽ� - 4 (total:176)
	 */
	//������� contentid�������� 
	public ArrayList<String> getContentIdList(String pageNo, String sigunguCode,String contentTypeId,String numOfRow) throws Exception {
		String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?"
				+ "serviceKey="+serviceKey
				+ "&pageNo="+pageNo
				+ "&numOfRows="+numOfRow
				+ "&MobileApp=AppTest"
				+ "&MobileOS=ETC"
				+ "&arrange=P"
				+ "&cat1="
				+ "&contentTypeId="+contentTypeId
				+ "&areaCode=39"
				+ "&sigunguCode="+sigunguCode
				+ "&cat2="
				+ "&cat3="
				+ "&listYN=Y"
				+ "&modifiedtime=&";

		DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
		Document doc = dBuilder.parse(url);

		// root tag 
		doc.getDocumentElement().normalize();
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

		// �Ľ��� tag
		NodeList nList = doc.getElementsByTagName("item");
		//System.out.println("�Ľ��� ����Ʈ �� : "+ nList.getLength());
		ArrayList<String> contentIdList = new ArrayList<String>();

		//NodeList�� return ������ҵ�
		//������ ���̵� ���� ����Ʈ�� ��ȯ�ϰ� ����� 
		for(int i=0;i<nList.getLength();i++) {
			Node node = nList.item(i);
			if(node.getNodeType()==Node.ELEMENT_NODE) {
				Element element = (Element) node;
				contentIdList.add(getTagValue("contentid", element));
			}
		}
		return contentIdList;
	}

	//공통 정보 가져오기 
	public List<String> getCommonInfo(String contentId,String contentTypeId, List<String> information) throws Exception {
		String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?"
				+ "serviceKey="+serviceKey
				+ "&numOfRows=1"
				+ "&pageNo=1"
				+ "&MobileOS=ETC"
				+ "&MobileApp=AppTest"
				+ "&contentId="+contentId
				+ "&contentTypeId="+contentTypeId
				+ "&defaultYN=Y"
				+ "&firstImageYN=Y"
				+ "&areacodeYN=Y"
				+ "&catcodeYN=Y"
				+ "&addrinfoYN=Y"
				+ "&mapinfoYN=Y"
				+ "&overviewYN=Y&";


		DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
		Document doc = dBuilder.parse(url);

		// root tag 
		doc.getDocumentElement().normalize();
		System.out.println("���� element :" + doc.getDocumentElement().getNodeName());

		// �Ľ��� tag
		NodeList nList = doc.getElementsByTagName("item");
		for(int i=0;i<nList.getLength();i++) {
			Node node = nList.item(i);
			if(node.getNodeType()==Node.ELEMENT_NODE) {
				Element element = (Element) node;
				if(getTagValue("firstimage", element) == null)
					information.add("/resources/assets/img/spot_images/no_img.png");
				else information.add(getTagValue("firstimage", element));
				information.add(getTagValue("title", element));
				information.add(getTagValue("overview", element));
				information.add(getTagValue("addr1", element));
				information.add(getTagValue("mapy", element));
				information.add(getTagValue("mapx",element));

			}
		}

		return information;
	}

	public String getFeeInfo(String contentId, String contentTypeId) throws Exception {
		String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?"
				+ "serviceKey="+serviceKey
				+ "&numOfRows=1"
				+ "&pageNo=1"
				+ "&MobileOS=ETC"
				+ "&MobileApp=AppTest"
				+ "&contentId="+contentId
				+ "&contentTypeId="+contentTypeId
				+ "&";

		DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
		Document doc = dBuilder.parse(url);
		// root tag 
		doc.getDocumentElement().normalize();
		System.out.println("�� element :" + doc.getDocumentElement().getNodeName());

		// �Ľ��� tag
		NodeList nList = doc.getElementsByTagName("item");
		System.out.println("getFeeInfo : "+nList.getLength() );
		switch(contentTypeId) {
		case "14":
			for(int i=0;i<nList.getLength();i++) {
				Node node = nList.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					Element element = (Element) node;
					return getTagValue("usefee",element);
				}
			}
			break;
		case "15":
			for(int i=0;i<nList.getLength();i++) {
				Node node = nList.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					Element element = (Element) node;
					return getTagValue("usetimefestival",element);
				}
			}
			break;
		case "28":
			for(int i=0;i<nList.getLength();i++) {
				Node node = nList.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					Element element = (Element) node;
					return getTagValue("usefeeleports",element);
				}
			}
			break;
		}
		return "";
	}

	//소개 기반 정보 (상세정보가져오기)
	public List<String> getIntroduceInfo(String contentId,String contentTypeId,List<String> information) throws Exception {
		String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?"
				+ "serviceKey="+serviceKey
				+ "&numOfRows=1"
				+ "&pageNo=1"
				+ "&MobileOS=ETC"
				+ "&MobileApp=AppTest"
				+ "&contentId="+contentId
				+ "&contentTypeId="+contentTypeId
				+ "&";

		DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
		Document doc = dBuilder.parse(url);
		// root tag 
		doc.getDocumentElement().normalize();
		System.out.println("�� element :" + doc.getDocumentElement().getNodeName());

		// �Ľ��� tag
		NodeList nList = doc.getElementsByTagName("item");

		switch(contentTypeId) {
		case "12" :
			for(int i=0;i<nList.getLength();i++) {
				Node node = nList.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					Element element = (Element) node;
					information.add(getTagValue("infocenter", element));
					information.add(getTagValue("restdate", element));
					information.add(getTagValue("usetime", element));
				}
			}
			break;
		case "14" :
			for(int i=0;i<nList.getLength();i++) {
				Node node = nList.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					Element element = (Element) node;
					information.add(getTagValue("infocenterculture", element));
					information.add(getTagValue("usefee", element));
					information.add(getTagValue("usetimeculture", element));
				}
			}
			break;
		case "15" :
			for(int i=0;i<nList.getLength();i++) {
				Node node = nList.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					Element element = (Element) node;
					information.add(getTagValue("eventhompage", element));
					information.add(getTagValue("sponsor1tel", element));
					information.add(getTagValue("playtime", element));
					information.add(getTagValue("usetimefestival", element));
				}
			}
			break;
		case "25" :
			for(int i=0;i<nList.getLength();i++) {
				Node node = nList.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					Element element = (Element) node;
					information.add(getTagValue("infocentertourcourse", element));
					information.add(getTagValue("taketime", element));
					information.add(getTagValue("theme", element));
				}
			}
			break;
		case "28" :
			for(int i=0;i<nList.getLength();i++) {
				Node node = nList.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					Element element = (Element) node;
					information.add(getTagValue("infocenterleports", element));
					information.add(getTagValue("restdateleports", element));
					information.add(getTagValue("usefeeleports", element));
					information.add(getTagValue("usetimeleports", element));
				}
			}
			break;
		case "32" :
			for(int i=0;i<nList.getLength();i++) {
				Node node = nList.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					Element element = (Element) node;
					information.add(getTagValue("checkintime", element));
					information.add(getTagValue("checkouttime", element));
					information.add(getTagValue("infocenterlodging", element));
					information.add(getTagValue("reservationurl", element));
					information.add(getTagValue("reservationlodging", element));
				}
			}
			break;
		case "38" :
			for(int i=0;i<nList.getLength();i++) {
				Node node = nList.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					Element element = (Element) node;
					information.add(getTagValue("infocentershopping", element));
					information.add(getTagValue("opentime", element));
					information.add(getTagValue("restdateshopping", element));
				}
			}
			break;
		case "39" :
			for(int i=0;i<nList.getLength();i++) {
				Node node = nList.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					Element element = (Element) node;
					information.add(getTagValue("firstmenu", element));
					information.add(getTagValue("infocenterfood", element));
					information.add(getTagValue("opentimefood", element));
					information.add(getTagValue("restdatefood", element));
				}
			}
			break;
		}
		return information;
	}

	//서브 이미지 가져오기 
	public List<String> getDiffImages(String contentId, String contentTypeId, String numOfRow) throws Exception{
		String imageYN;
		if(contentTypeId.equals("39")) imageYN = "N";
		else imageYN = "Y";

		String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailImage?"
				+ "serviceKey="+serviceKey
				+ "&numOfRows="+numOfRow
				+ "&pageNo=1"
				+ "&MobileOS=ETC"
				+ "&MobileApp=AppTest"
				+ "&contentId="+contentId
				+ "&imageYN="+imageYN
				+ "&subImageYN=Y&";

		DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
		Document doc = dBuilder.parse(url);

		// root tag 
		doc.getDocumentElement().normalize();
		System.out.println("�̹�������  :" + doc.getDocumentElement().getNodeName());

		// �Ľ��� tag
		NodeList nList = doc.getElementsByTagName("item");
		List<String> img_src = new ArrayList<String>();

		for(int i=0;i<nList.getLength();i++) {
			Node node = nList.item(i);
			if(node.getNodeType()==Node.ELEMENT_NODE) {
				Element element = (Element) node;
				img_src.add(getTagValue("smallimageurl", element));
			}
		}
		return img_src;
	}

	// 토탈 카운트
	public int getTotalCount(String contentTypeId, String sigunguCode) throws Exception {
		String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?"
				+ "serviceKey="+serviceKey
				+ "&MobileApp=AppTest"
				+ "&MobileOS=ETC"
				+ "&contentTypeId="+contentTypeId
				+ "&areaCode=39"
				+ "&sigunguCode="+sigunguCode
				+ "&listYN=N";

		DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
		Document doc = dBuilder.parse(url);

		// root tag 
		doc.getDocumentElement().normalize();
		System.out.println("Root element3 :" + doc.getDocumentElement().getNodeName());

		// �Ľ��� tag
		NodeList nList = doc.getElementsByTagName("item");

		//System.out.println("�Ľ��� ����Ʈ �� : "+ nList.getLength());
		ArrayList<Integer> contentIdList = new ArrayList<Integer>();

		//NodeList�� return ������ҵ�
		//������ ���̵� ���� ����Ʈ�� ��ȯ�ϰ� ����� 
		for(int i=0;i<nList.getLength();i++) {
			Node node = nList.item(i);
			if(node.getNodeType()==Node.ELEMENT_NODE) {
				Element element = (Element) node;
				contentIdList.add(Integer.parseInt(getTagValue("totalCnt", element)));
			}
		}

		return contentIdList.get(0);
	}


	//spot 불러오기!
	public List<SpotVO> getInformation (SpotVO spotVO, int totalCount) throws Exception {
		ArrayList<String> contentIdList= getContentIdList(spotVO.getPageNo(),spotVO.getSigunguCode(),spotVO.getContentTypeId(),spotVO.getNumOfRow());
		ArrayList<NodeList> spotInfo = getSpotInfo(contentIdList);
		List<SpotVO> information = new ArrayList<SpotVO>();
		System.out.println("getInformation : "+spotInfo.size());
		for(int i=0; i<spotInfo.size();i++) {
			SpotVO oneSpot = new SpotVO();
			for(int j=0;j<spotInfo.get(i).getLength();j++) {
				Node node = spotInfo.get(i).item(j);
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					Element element = (Element) node;
					oneSpot.setFirstImage2(getTagValue("firstimage2",element));
					if(oneSpot.getFirstImage2()==null) {
						oneSpot.setFirstImage2("/resources/assets/img/spot_images/no_img.png");
					}
					oneSpot.setTitle(getTagValue("title",element));
					if(!spotVO.getContentTypeId().equals("25")){
						oneSpot.setAddr1(getTagValue("addr1",element));
					}
					oneSpot.setOverview(getTagValue("overview",element));
					oneSpot.setContentId(getTagValue("contentid",element));
					oneSpot.setContentTypeId(getTagValue("contenttypeid",element));
					oneSpot.setMapX(getTagValue("mapy",element));
					oneSpot.setMapY(getTagValue("mapx",element));
					oneSpot.setTotalCount(Integer.toString(totalCount));
				}
			}
			information.add(oneSpot);
		}
		return information;
	}

	public List<String> getEachInformation(SpotVO spotVO) throws Exception {
		List<String> information = new ArrayList<String>();
		information = getCommonInfo(spotVO.getContentId(), spotVO.getContentTypeId(), information);
		information = getIntroduceInfo(spotVO.getContentId(), spotVO.getContentTypeId(), information);

		log.info(information);

		return information;
	}
	public List<SpotVO> getKeywordInformation(SpotVO spotVO, String keyword) throws Exception{
		String encodeKeyword = URLEncoder.encode(keyword,"utf-8");
		String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword?"
				+ "serviceKey="+serviceKey
				+ "&MobileApp=AppTest"
				+ "&MobileOS=ETC"
				+ "&pageNo="+spotVO.getPageNo()
				+ "&numOfRows="+spotVO.getNumOfRow()
				+ "&listYN=Y"
				+ "&arrange=P"
				+ "&contentTypeId="
				+ "&areaCode=39"
				+ "&sigunguCode="
				+ "&cat1="
				+ "&cat2="
				+ "&cat3="
				+ "&keyword="+encodeKeyword;

		//keyword UTF-8 Encoding 필요
		DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
		Document doc = dBuilder.parse(url);

		// root tag 
		doc.getDocumentElement().normalize();
		System.out.println("keyword :" + doc.getDocumentElement().getNodeName());
		System.out.println(spotVO + " "+ keyword);
		System.out.println(url);
		// �Ľ��� tag
		NodeList nList = doc.getElementsByTagName("item");
		ArrayList<String> contentIdList = new ArrayList<String>();
		for(int i=0;i<nList.getLength();i++) {
			Node node = nList.item(i);
			if(node.getNodeType()==Node.ELEMENT_NODE) {
				Element element = (Element) node;
				contentIdList.add(getTagValue("contentid", element));
			}
		}
		System.out.println(contentIdList.size());

		String totalCount ="";
		nList = doc.getElementsByTagName("totalCount");
		Node count_node = nList.item(0);
		if(count_node.getNodeType()==Node.ELEMENT_NODE) {
			Element element = (Element) count_node;
			totalCount = getTagValue("totalCount", element);
		}
		System.out.println(totalCount);
		ArrayList<NodeList> spotInfo = getSpotInfo(contentIdList);

		List<SpotVO> information = new ArrayList<SpotVO>();
		for(int i=0; i<spotInfo.size();i++) {
			SpotVO oneSpot = new SpotVO();
			for(int j=0;j<spotInfo.get(i).getLength();j++) {
				Node info_node = spotInfo.get(i).item(j);
				if(info_node.getNodeType()==Node.ELEMENT_NODE) {
					Element element = (Element) info_node;
					oneSpot.setFirstImage2(getTagValue("firstimage2",element));
					if(oneSpot.getFirstImage2()==null) {
						oneSpot.setFirstImage2("/resources/assets/img/spot_images/no_img.png");
					}
					oneSpot.setTitle(getTagValue("title",element));
					if(!getTagValue("addr1",element).equals("25")){
						oneSpot.setAddr1(getTagValue("addr1",element));
					}
					oneSpot.setOverview(getTagValue("overview",element));
					oneSpot.setContentId(getTagValue("contentid",element));
					oneSpot.setContentTypeId(getTagValue("contenttypeid",element));
					oneSpot.setMapX(getTagValue("mapy",element));
					oneSpot.setMapY(getTagValue("mapx",element));
					oneSpot.setTotalCount(totalCount);
				}
			}
			information.add(oneSpot);
		}
		return information;
	}
	public List<SpotVO> getKeywordFromPOI(SpotVO spotVO, String keyword) throws Exception{
		String encodeKeyword = URLEncoder.encode(keyword,"utf-8");
		String requestUrl="http://apis.openapi.sk.com/tmap/pois?"
				+ "format=json"
				+ "&version=1"
				+ "&page="+spotVO.getPageNo()
				+ "&count="+spotVO.getNumOfRow()
				+ "&appKey="+TMAP_KEY
				+ "&searchKeyword="+encodeKeyword
				+ "&reqCoordType=WGS84GEO"
				+ "&resCoordType=WGS84GEO"
				+ "&searchtypCd=A";
		URL url = new URL(requestUrl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
		System.out.println("RESPONSE CODE : "+responseCode);

		Charset charset = Charset.forName("UTF-8");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),charset));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		List<SpotVO> information = fromJsonToSpotInfo(response.toString());
		//		System.out.println("결과  : "+response.toString());
		return information;
	}

	public List<SpotVO> fromJsonToSpotInfo(String jsonData) {
		JSONObject jObject = new JSONObject(jsonData);
		JSONObject searchInfoObj = jObject.getJSONObject("searchPoiInfo");
		String totalCount = searchInfoObj.getNumber("totalCount").toString();
		System.out.println(totalCount);
		JSONObject pois = searchInfoObj.getJSONObject("pois");
		JSONArray poi = pois.getJSONArray("poi");
		List<SpotVO> information = new ArrayList<SpotVO>();
		for(int i=0;i<poi.length();i++) {
			SpotVO oneSpot = new SpotVO();
			JSONObject obj = poi.getJSONObject(i);
			oneSpot.setTitle(obj.getString("name"));
			oneSpot.setMapX(obj.getString("noorLat").toString());
			oneSpot.setMapY(obj.getString("noorLon").toString());
			String addr = obj.getString("upperAddrName") +" "+ obj.getString("middleAddrName")+" "+ obj.getString("lowerAddrName");
			oneSpot.setAddr1(addr);
			oneSpot.setFirstImage2("/resources/assets/img/spot_images/no_img.png");
			oneSpot.setTotalCount(totalCount);
			information.add(oneSpot);
			System.out.println(oneSpot);
		}
		return information;
	}
}

