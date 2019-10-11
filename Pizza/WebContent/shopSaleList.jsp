<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="song.*" %>
<%
	List<PizzaVo> shopSaleLis = (List<PizzaVo>) request.getAttribute("shopSaleList");
%>

<html>
<body>
	<table>
		<tr>
			<th>지점코드</th>
			<th>지점 명</th>
			<th>충 매출액</th>
		</tr>
		<tr>
		<%for(PizzaVo vo : shopSaleLis){ %>
			<td><%=vo.getScode() %></td>
			<td><%=vo.getSname() %></td>
			<td><%=vo.getSumcost() %></td>
		</tr>
		<%} %>
	</table>
</body>
</html>