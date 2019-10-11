package song;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//매출전표등록
@WebServlet("/pReg")
public class pRegSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<PizzaVo> scodeList = DAO.scodeList();
		List<PizzaVo> pcodeList = DAO.pcodeList();
		String saleno = DAO.saleno();
		System.out.println(saleno);
		
		request.setAttribute("scodeList", scodeList);
		request.setAttribute("pcodeList", pcodeList);
		request.setAttribute("saleno", saleno);
		request.setAttribute("title", "매출전표등록");
		request.setAttribute("view", "pReg.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String saleno = request.getParameter("saleno");
		String scode = request.getParameter("scode");
		String saledate = request.getParameter("saledate");
		String pcode = request.getParameter("pcode");
		String amount = request.getParameter("amount");
		
		System.out.println("saleno:"+saleno);
		System.out.println("scode:"+scode);
		System.out.println("saledate:"+saledate);
		System.out.println("pcode:"+pcode);
		System.out.println("amount:"+amount);
		
		PizzaVo vo = new PizzaVo();
		
		vo.setSaleno(saleno);
		vo.setScode(scode);
		vo.setSaledate(saledate);
		vo.setPcode(pcode);
		vo.setAmount(amount);
		
		int result = DAO.pReg(vo);
	
		if(result == 0) {
			System.out.println("매출전표등록 실패");
			doGet(request,response);
		}
		if(result != 0) {
			System.out.println("매출전표등록 성공");
			response.sendRedirect("pReg");
		}
	}

}
