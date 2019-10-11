package song;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//지점별 매출 현황
@WebServlet("/shopSaleList")
public class shopSaleList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<PizzaVo> shopSaleList = DAO.shopSaleList();
		
		request.setAttribute("shopSaleList", shopSaleList);
		
		request.setAttribute("title", "지점별 매출 현황");
		request.setAttribute("view", "shopSaleList.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	}

}
