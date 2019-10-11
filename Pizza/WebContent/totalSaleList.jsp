<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="song.*" %>
<%
	List<PizzaVo> totalSaleList = (List<PizzaVo>) request.getAttribute("totalSaleList");
%>

<html>
<body>
	<table>
		<tr>
			<th>매출전표번호</th>
			<th>지점</th>
			<th>판매일자</th>
			<th>피자코드</th>
			<th>피자명</th>
			<th>판매수량</th>
			<th>매출액</th>
		</tr>
		<tr>
		<%for(PizzaVo vo : totalSaleList){ %>
			<td><%=vo.getSaleno() %></td>
			<td><%=vo.getScode() %>-<%=vo.getSname() %></td>
			<td><%=vo.getSaledate() %></td>
			<td><%=vo.getPcode() %></td>
			<td><%=vo.getPname() %></td>
			<td><%=vo.getAmount() %></td>
			<td><%=vo.getSumcost() %></td>
		</tr>
		<%} %>
	</table>
</body>
</html>