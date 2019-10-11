<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="song.*" %>
<%
	String title = (String)request.getAttribute("title");
	String view = (String)request.getAttribute("view");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=title %></title>
</head>
<body>
<header><h1>(과정평가형 2018-12)피자전문점 판매 관리 프로그램 ver1.0</h1></header>
	<menu>
		<a href="pReg">매출전표 등록</a>
		<a href="totalSaleList">통합매출조회</a>
		<a href="shopSaleList">지점별매출현황</a>
		<a href="pcodeSaleList">상품별매출현황</a>
		<a href="index.jsp">홈으로</a>
	</menu>
	<section>
		<jsp:include page="<%=view %>"></jsp:include>
	</section>
	<footer>HRDKOREA Copyright&copy;2018 All rights reserved.Human Resources Development Service of Korea</footer>

</body>
</html>