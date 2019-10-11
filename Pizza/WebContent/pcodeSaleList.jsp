<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="song.*" %>
<%
	List<PizzaVo> pcodeSaleList = (List<PizzaVo>) request.getAttribute("pcodeSaleList");
%>

<html>
<body>
	<table>
		<tr>
			<th>피자코드</th>
			<th>피자 명</th>
			<th>충 매출액</th>
		</tr>
		<tr>
		<%for(PizzaVo vo : pcodeSaleList){ %>
			<td><%=vo.getPcode() %></td>
			<td><%=vo.getPname() %></td>
			<td><%=vo.getSumcost() %></td>
		</tr>
		<%} %>
	</table>
</body>
</html>