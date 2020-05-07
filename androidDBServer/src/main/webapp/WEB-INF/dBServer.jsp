<%@page import="dataStruct.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% List<MemberVO> memberList = (List<MemberVO>)request.getAttribute("memberList");%>

<%
	for(int i=0; i<memberList.size(); i++){
		System.out.println(memberList.get(i).getMem_name());
	}
%>
