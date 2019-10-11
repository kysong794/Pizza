package song;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	
	public static Connection getConnection() throws Exception{
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe","pizza2","hkit2019");
		System.out.println("DB 연결");
		return con;
	}
	public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//지점 코드 리스트
	public static List<PizzaVo> scodeList() {
		List<PizzaVo> list = new ArrayList<PizzaVo>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select scode,sname "
					+" from tbl_shop_01 ";
		
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PizzaVo vo = new PizzaVo();
				vo.setScode(rs.getString("scode"));
				vo.setSname(rs.getString("sname"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}	
		return list;
	}
	//피자 코드 리스트
	public static List<PizzaVo> pcodeList() {
		List<PizzaVo> list = new ArrayList<PizzaVo>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select pcode,pname "
					+" from tbl_pizza_01 ";
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PizzaVo vo = new PizzaVo();
				vo.setPcode(rs.getString("pcode"));
				vo.setPname(rs.getString("pname"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}	
		return list;
	}
	
	//매출전표번호 자동생성
	public static String saleno() {
		String result = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select nvl(max(saleno),0)+1 as saleno from tbl_salelist_01 ";
		
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				result = rs.getString("saleno");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}
		return result;
	}
	
	//매출전표 등록
	public static int pReg(PizzaVo vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " insert into tbl_salelist_01(saleno,scode,saledate,pcode,amount) "
					+" VALUES ((select nvl(max(saleno),0)+1 from tbl_salelist_01),?,?,?,?)";
		
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, vo.getScode());
			ps.setString(2, vo.getSaledate());
			ps.setString(3, vo.getPcode());
			ps.setString(4, vo.getAmount());
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,null);
		}
		return result;
	}
	//통합 매출 현황 조회
	public static List<PizzaVo> totalSaleList() {
		List<PizzaVo> totalSaleList = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select a.saleno,a.scode,b.sname,to_char(a.saledate,'YYYY-MM-DD')as saledate,a.pcode,c.pname,a.amount,to_char(a.amount*c.cost,'fml999,999,999') as sumcost "
					+" from tbl_salelist_01 a "
					+" inner join tbl_shop_01 b on a.scode = b.scode "
					+" inner join tbl_pizza_01 c on a.pcode = c.pcode "
					+" order by a.saleno DESC ";
		
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PizzaVo vo = new PizzaVo();
				vo.setSaleno(rs.getString("saleno"));
				vo.setScode(rs.getString("scode"));
				vo.setSname(rs.getString("sname"));
				vo.setSaledate(rs.getString("saledate"));
				vo.setPcode(rs.getString("pcode"));
				vo.setPname(rs.getString("pname"));
				vo.setAmount(rs.getString("amount"));
				vo.setSumcost(rs.getString("sumcost"));
				totalSaleList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}
		return totalSaleList;
	}
	//지점별 매출 현황
	public static List<PizzaVo> shopSaleList(){
		List<PizzaVo> shopSaleList = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select a.scode,a.sname,to_char(b.amount*c.cost,'fml999,999,999') as sumcost "
					+" from tbl_shop_01 a "
					+" inner join tbl_salelist_01 b on a.scode = b.scode "
					+" inner join tbl_pizza_01 c on b.pcode = c.pcode "
					+" order by a.scode DESC ";
		
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PizzaVo vo = new PizzaVo();
				vo.setScode(rs.getString("scode"));
				vo.setSname(rs.getString("sname"));
				vo.setSumcost(rs.getString("sumcost"));
				shopSaleList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}
		return shopSaleList;
	}
	//상품별 매출 현황
	public static List<PizzaVo> pcodeSaleList(){
		List<PizzaVo> pcodeSaleList = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select a.pcode,a.pname,to_char(b.amount*a.cost,'fml999,999,999') as sumcost "
					+" from tbl_pizza_01 a "
					+" inner join tbl_salelist_01 b on a.pcode = b.pcode "
					+" order by a.pcode desc ";
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PizzaVo vo = new PizzaVo();
				vo.setPcode(rs.getString("pcode"));
				vo.setPname(rs.getString("pname"));
				vo.setSumcost(rs.getString("sumcost"));
				pcodeSaleList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}
		return pcodeSaleList;
	}
}

