<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="song.*" %>
<%
	List<PizzaVo> scodeList = (List<PizzaVo>)request.getAttribute("scodeList");
	List<PizzaVo> pcodeList = (List<PizzaVo>)request.getAttribute("pcodeList");
	String saleno = (String) request.getAttribute("saleno");
%>
<html>
<body>
<form id="frm" action="pReg" method="post" onsubmit='return che();'>
	<table>
		<tr>
			<th>매출전표번호</th>
			<td><input  type="text" name="saleno" value="<%=saleno %>" readonly></td>
		</tr>
		<tr>
			<th>지점코드</th>
			<td>
				<select name="scode">
					<option value="">지점선택</option>
					<%for(PizzaVo vo : scodeList) { %>
					<option value="<%=vo.getScode() %>">[<%=vo.getScode() %>]<%=vo.getSname() %></option>
					<%} %>
				</select>
			</td>
		</tr>
		<tr>
			<th>피자일자</th>
			<td><input type="date" name="saledate"></td>
		</tr>
		<tr>
			<th>피자코드</th>
			<td>
				<select name="pcode">
				<option value="">피자선택</option>
				<%for(PizzaVo vo : pcodeList){ %>
				<option value="<%=vo.getPcode() %>">[<%=vo.getPcode() %>]<%=vo.getPname() %></option>
				<%} %>
				</select>
			</td>
		</tr>
		<tr>
			<th>판매수량</th>
			<td><input type="number" name="amount"></td>
		</tr>
		<tr>
			<th><input type="submit" value="전표등록"></th>
			<th><input type="reset" value="다시쓰기"></th>
		</tr>
	</table>
</form>
</body>
</html>
<script>
	function che(){
		if(frm.scode.value === null ||frm.scode.value === '' ){
			alert('지점코드를 선택하세요');
			return false;
		}
		if(frm.saledate.value === null ||frm.saledate.value === '' ){
			alert('판매일자를 선택하세요');
			return false;
		}
		if(frm.pcode.value === null ||frm.pcode.value === '' ){
			alert('피자코드를 선택하세요');
			return false;
		}
		if(frm.amount.value === null ||frm.amount.value == '' ){
			alert('판매수량을 선택하세요');
			return false;
		}
	}

</script>