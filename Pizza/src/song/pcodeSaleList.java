package song;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//상품별 매출 현황
@WebServlet("/pcodeSaleList")
public class pcodeSaleList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		List<PizzaVo> pcodeSaleList = DAO.pcodeSaleList();
		
		request.setAttribute("pcodeSaleList", pcodeSaleList);
		
		request.setAttribute("title", "상품별 매출 현황");
		request.setAttribute("view", "pcodeSaleList.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);

	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	}

}
