package com.koreait.tourapp.controller.place;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.koreait.tourapp.model.domain.Culture;

public class CultureHandler extends DefaultHandler{
	
	//실행부가 어디를 지나치고 있는지를 표시할 논리값들 선언
	private boolean isAddr1;
	private boolean isFirstimage;
	private boolean isMapx;
	private boolean isMapy;
	private boolean isTitle;
	
	ArrayList<Culture> list;
	Culture culture;
	
	
	//시작태그
	public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
		System.out.print("<"+tag+">");
		if(tag.equals("items")) { //vo를 담을 list형을 준비!!
			list = new ArrayList<Culture>();
		}else if(tag.equals("item")) {
			culture = new Culture();			
		}else if(tag.equals("addr1")) {
			isAddr1 = true;			
		}else if(tag.equals("firstimage")) {
			isFirstimage= true;			
		}else if(tag.equals("mapx")) {
			 isMapx= true;			
		}else if(tag.equals("mapy")) {
			isMapy= true;		
		}else if(tag.equals("title")) {
			isTitle= true;
		}
	}
	
	//태그 사이 문자열 발견
	public void characters(char[] ch, int start, int length) throws SAXException {
		String data = new String(ch,start,length);
		System.out.print(data);
		if(isAddr1) {
			culture.setAddr1(data);
		}else if(isFirstimage) {
			culture.setFirstimage(data);
		}else if(isMapx) {
			culture.setMapx(Double.parseDouble(data));
		}else if(isMapy) {
			culture.setMapy(Double.parseDouble(data));
		}else if(isTitle) {
			culture.setTitle(data);
		}
	}
	
	//닫는 태그
	public void endElement(String uri, String localName, String tag) throws SAXException {
		System.out.println("</"+tag+">");
	if(tag.equals("addr1")) {
			isAddr1 = false;		
		}else if(tag.equals("firstimage")) {
			isFirstimage= false;				
		}else if(tag.equals("mapx")) {
			 isMapx= false;				
		}else if(tag.equals("mapy")) {
			isMapy=false;		
		}else if(tag.equals("title")) {
			isTitle= false;	
		}else	if(tag.equals("item")) {
			//완성된 Culture VO를 list에 담기
			list.add(culture);
		}
		
	}
	
	//문서 종료 태그
	public void endDocument() throws SAXException {
		System.out.println("담겨진 결과 수는 "+list.size());
	}
	
}
