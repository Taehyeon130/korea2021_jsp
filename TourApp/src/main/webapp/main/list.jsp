<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.List"%>
<%@page import="com.koreait.tourapp.model.store.dao.StoreDAO"%>
<%@page import="com.koreait.tourapp.model.store.dao.MybatisStoreDAO"%>
<%@ page  contentType="text/html;charset=UTF-8"%>
<%!
	StoreDAO storeDAO = new MybatisStoreDAO();
%>
<%
	//등록된 관광명소 및 맛집 정보 가져오기
	List storeList = storeDAO.selectAll();
	Map<String, List> map = new HashMap<String,List>();	
	map.put("item",storeList);
	
	//클라이언트에게  json문자열로 전송하자!!
	Gson gson = new Gson();
	String str = gson.toJson(map); //java --> json string
	out.print(str);
	
%>