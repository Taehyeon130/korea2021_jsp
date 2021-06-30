package com.koreait.tourapp.util;



public class FileManager {
	//확장자만 추출하기
	public static String getExt(String path) { //경로를 넘겨받아 확장자만 추출
		//test.kkk.jpg
		return path.substring(path.lastIndexOf(".")+1, path.length());
	
	}
//	public static void main(String[] args) {
//		System.out.println(getExt("text.kkk.jpg"));
//	}
}
