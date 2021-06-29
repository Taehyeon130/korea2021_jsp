package com.koreait.tourapp.controller.place;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.koreait.tourapp.model.domain.Culture;


public class PlaceServlet extends HttpServlet{
	SAXParserFactory factory;
	SAXParser saxParser;
	CultureHandler handler; 
	
	@Override
	public void init() throws ServletException {
		factory = SAXParserFactory.newInstance();
		try {
			saxParser = factory.newSAXParser(); //파서 생성
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String result = loadData();
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		
		//xml 파싱을 위한 객체는 자바에서 이미 지원
		//요청하기 위한 파라미터 구성
		String ServiceKey="hl4BZLiOs3K8cuJVVrDAj8tf7pZ09ywZT2to%2BGtx1NDjRi%2FPGZPAUvriQqZFmeCV5BbYqkeao2JLr%2Bl4RF3%2FnA%3D%3D";
        StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "="+ServiceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*현재 페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*IOS(아이폰),AND(안드로이드),WIN(원도우폰), ETC*/
        urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8")); /*서비스명=어플명*/
        urlBuilder.append("&" + URLEncoder.encode("arrange","UTF-8") + "=" + URLEncoder.encode("A", "UTF-8")); /*(A=제목순, B=조회순, C=수정일순, D=생성일순, E=거리순)*/
        urlBuilder.append("&" + URLEncoder.encode("contentTypeId","UTF-8") + "=" + URLEncoder.encode("15", "UTF-8")); /*관광타입(관광지, 숙박 등) ID*/
        urlBuilder.append("&" + URLEncoder.encode("mapX","UTF-8") + "=" + URLEncoder.encode("126.981611", "UTF-8")); /*GPS X좌표(WGS84 경도 좌표)*/
        urlBuilder.append("&" + URLEncoder.encode("mapY","UTF-8") + "=" + URLEncoder.encode("37.568477", "UTF-8")); /*GPS Y좌표(WGS84 위도 좌표)*/
        urlBuilder.append("&" + URLEncoder.encode("radius","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*거리 반경(단위m), Max값 20000m=20Km*/
        urlBuilder.append("&" + URLEncoder.encode("listYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*목록 구분 (Y=목록, N=개수)*/
        urlBuilder.append("&" + URLEncoder.encode("modifiedtime","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*콘텐츠 수정일*/
        
        //Http 요청을 위한 코드 
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        
        //첫번째 방법으로 호출하여 응답정보 구성
        String str=getJson3(conn);
        out.print(str);
        
        conn.disconnect();
	}
	
	//xml문자열을 파싱하여 자바객체로 변환한 후, 다시 json으로 변환하고 이 json을  json문자열로 클라이언트에게 응답
	public String getJson1(HttpURLConnection conn) {
        //요청에 의한 응답정보 가져오기
        //스트림을 이용한 파싱 
		String str=null;
        try {
			saxParser.parse(conn.getInputStream(), handler = new CultureHandler());
			//파싱이 종료되었고, 핸들러가 보유한  list를 접근해보기 
			//ArrayList  --> JSON으로 변환하여 클라이언트인 웹브라우저에 보내자!!!
			
			//자바의 객체를 JSON 문자열로 변환하는 과정을 개발자가 일일이 처리하지말고, Gson과 같은 라이브러리를 이용해보자
			//Map<String, List> map = new HashMap<String, List>();
			//map.put("items", handler.list);
			//Gson gson = new Gson();
			//str=gson.toJson(map);
			
			//out.print(str);
			
			StringBuilder sb=new StringBuilder();

			sb.append("{");
			sb.append("\"items\":[");
			for(int i=0;i<handler.list.size();i++) {
				Culture culture=handler.list.get(i); //VO 추출!!
				sb.append("{");
				sb.append("\"addr1\":\""+culture.getAddr1()+"\",");
				sb.append("\"firstimage\":\""+culture.getFirstimage()+"\",");
				sb.append("\"mapx\":"+culture.getMapx()+",");
				sb.append("\"mapy\":"+culture.getMapy()+",");
				sb.append("\"title\":\""+culture.getTitle()+"\"");
				
				if(i<handler.list.size()-1) {//size -1 보다 작을때까지 찍자
					sb.append("},");
				}else {
					sb.append("}");
				}
			}
			sb.append("]");
			sb.append("}");
			
			str=sb.toString();
			
			//out.print(sb.toString()); //클라이언트에게 응답시 사용할 컨텐츠 구성
			
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	//
	public String getJson2(HttpURLConnection conn) {
		String str=null;
        try {
			saxParser.parse(conn.getInputStream(), handler = new CultureHandler());
			//자바의 객체를 JSON 문자열로 변환하는 과정을 개발자가 일일이 처리하지말고, Gson과 같은 라이브러리를 이용해보자
			Map<String, List> map = new HashMap<String, List>();
			map.put("items", handler.list);
			Gson gson = new Gson();
			str=gson.toJson(map);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public String getJson3(HttpURLConnection conn) {
		
		StringBuilder sb = new StringBuilder();
        try {
			BufferedReader rd;
			if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			    rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
			    rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			String line;
			while ((line = rd.readLine()) != null) {
			    sb.append(line);
			}
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        //xml 문자열을 자동을로 json 객체로 변환해보자!!
        
        JSONObject json=XML.toJSONObject(sb.toString()); //xml 스트링을 json 객체로 변환!!
        JSONObject res=(JSONObject)json.get("response"); //response key 가 가리키는 json 추출
        JSONObject body=(JSONObject)res.get("body");//body key 가 가리키는 json 추출
        JSONObject items=(JSONObject)body.get("items"); //items key가 가리키는 json 추출 
        //JSONArray item=(JSONArray)items.get("item"); //item key가 가리키는 json array 추출 
        //System.out.println("관광 아이템의 수는 "+item.length());
        return items.toString();
 	}
	
}

