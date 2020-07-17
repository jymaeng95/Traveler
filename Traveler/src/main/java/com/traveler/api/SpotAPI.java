package com.traveler.api;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.traveler.domain.SpotVO;

@Service
public class SpotAPI {
		final String serviceKey ="SHUTUe8Ya9tRYNvxndPl0GuDMGD%2F8T4d2LFqklaI9yoGzpnlZPEmo3uUsQ6BGhsVr%2F8vQZFAhkPg%2Fc4kGh1%2Big%3D%3D";
//		final String serviceKey ="WPuoTd67PSQd4jXck%2FRkGCLmjVLTCkJWVcBKcZk36xiTy6meeE%2FuWi4OKVduoXWijK8jCUvaYXhYHnqSEkWlPw%3D%3D";
	//	final String serviceKey ="gr8r1Nu%2FamiYcpoKwNRrDhgCWT8T3dJPxG1%2F2MuEzrCiSq0m4%2B8%2BUqf5LqJpVfIBWrgndZHrRpZy27PUCT21rQ%3D%3D";
	//	final String serviceKey ="iv8Bz7xZDYyuYRnhmBekQj5GV732OijidErVpJets0Bw9rMM6FEWPF3t4Ow%2FIJ091nOnUnXFq%2FFjas8TzW13pA%3D%3D";
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
	public ArrayList<String> getContetnIdList(String pageNo, String sigunguCode,String contentTypeId) throws Exception {
		String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?"
				+ "serviceKey="+serviceKey
				+ "&pageNo="+pageNo
				+ "&numOfRows=10"
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

	//���� ���� ���� 
	public NodeList getCommonInfo(String contentId,String contentTypeId) throws Exception {
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
		return nList;
	}
	//�� ���� ���� 
	public NodeList getIntroduceInfo(String contentId,String contentTypeId) throws Exception {
		String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?"
				+ "serviceKey="+serviceKey
				+ "&numOfRows=10"
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

		return nList;
	}
	
	//�������� �̹��� �������� 
	public List<String> getDiffImages(String contentId, String contentTypeId) throws Exception{
		String imageYN;
		if(contentTypeId.equals("39")) imageYN = "N";
		else imageYN = "Y";
		
		String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailImage?"
				+ "serviceKey="+serviceKey
				+ "&numOfRows=10"
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
	
	//  �ñ��� ���� ����
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
			ArrayList<String> contentIdList= getContetnIdList(spotVO.getPageNo(),spotVO.getSigunguCode(),spotVO.getContentTypeId());
			ArrayList<NodeList> spotInfo = getSpotInfo(contentIdList);
			List<SpotVO> information = new ArrayList<SpotVO>();
			
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
						oneSpot.setTotalCount(Integer.toString(totalCount));
					}
				}
				information.add(oneSpot);
			}
			return information;
		}
}
