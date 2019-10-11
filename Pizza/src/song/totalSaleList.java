package song;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//통합매출 조회
@WebServlet("/totalSaleList")
public class totalSaleList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<PizzaVo> totalSaleList = DAO.totalSaleList();
		
		request.setAttribute("totalSaleList", totalSaleList);
		
		request.setAttribute("title", "통합매출현황조회");
		request.setAttribute("view", "totalSaleList.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	
	}

}
