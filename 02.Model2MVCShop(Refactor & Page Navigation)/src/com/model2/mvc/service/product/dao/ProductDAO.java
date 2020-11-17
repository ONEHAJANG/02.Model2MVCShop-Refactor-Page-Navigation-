package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;


public class ProductDAO {
	
	public ProductDAO(){
	}

	public void insertProduct(Product productVO) throws Exception {
		System.out.println(":: insertProduct start");
		Connection con = DBUtil.getConnection();

		String sql = "INSERT INTO product VALUES (seq_product_prod_no.nextval,?,?,?,?,?,sysdate)";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, productVO.getProdName());
		stmt.setString(2, productVO.getProdDetail());
		stmt.setString(3, productVO.getManuDate());
		stmt.setInt(4, productVO.getPrice());
		stmt.setString(5, productVO.getFileName());
		stmt.executeUpdate();
		
		con.close();
		System.out.println( ":: insertProduct end");
	}
		
	public Product findProduct(int prodno) throws Exception {
		
		System.out.println(":: findProduct start");
		
		Connection con = DBUtil.getConnection();

		String sql = "SELECT * FROM product WHERE prod_no=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, prodno);

		ResultSet rs = stmt.executeQuery();
		
		System.out.println(":: findProduct end");
		
		Product productVO = null;
		while (rs.next()) {
			productVO = new Product();
			productVO.setProdNo(Integer.parseInt(rs.getString("PROD_NO")));
			productVO.setProdName(rs.getString("PROD_NAME"));
			productVO.setProdDetail(rs.getString("PROD_DETAIL"));
			productVO.setManuDate(rs.getString("MANUFACTURE_DAY"));
			productVO.setPrice(Integer.parseInt(rs.getString("PRICE")));
			productVO.setFileName(rs.getString("IMAGE_FILE"));
			productVO.setRegDate(rs.getDate("REG_DATE"));
//			productVO.setProTranCode(rs.getString("PROTRANCODE"));
		}
		
		con.close();
	
		return productVO;
	}

	//==================조회====================
	
	
	public HashMap<String,Object> getProductList(Search searchVO) throws Exception {
		
		System.out.println(":: getProduct start");
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT * FROM product ";
		if (searchVO.getSearchCondition() != null) {
			if (searchVO.getSearchCondition().equals("0")) {
				sql += " WHERE prod_no LIKE '%" + searchVO.getSearchKeyword()
						+ "%'";
			} else if (searchVO.getSearchCondition().equals("1")) {
				sql += " WHERE prod_name LIKE '%" + searchVO.getSearchKeyword()
						+ "%'";
			}
		}
		sql += " ORDER BY prod_no";

		PreparedStatement stmt = 
			con.prepareStatement(	sql,
														ResultSet.TYPE_SCROLL_INSENSITIVE,
														ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = stmt.executeQuery();

		System.out.println(":: ProductDAO - getProductList");
		
		rs.last();
		int total = rs.getRow();
		System.out.println("로우의 수:" + total);

		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("totalCount", new Integer(total));

		rs.absolute(searchVO.getCurrentPage() * searchVO.getPageSize() - searchVO.getPageSize()+1);
		System.out.println("searchVO.getPage():" + searchVO.getCurrentPage());
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageSize());

		ArrayList<Product> list = new ArrayList<Product>();
		if (total > 0) {
			for (int i = 0; i < searchVO.getPageSize(); i++) {
				Product vo = new Product();
				vo.setProdNo(Integer.parseInt(rs.getString("Prod_No")));
				vo.setProdName(rs.getString("Prod_Name"));
				vo.setProdDetail(rs.getString("Prod_Detail"));
				vo.setManuDate(rs.getString("Manufacture_Day"));
				vo.setPrice(Integer.parseInt(rs.getString("Price")));
				vo.setFileName(rs.getString("Image_File"));
				vo.setRegDate(rs.getDate("Reg_Date"));

				list.add(vo);
				if (!rs.next())
					break;
			}
		}
		System.out.println("list.size() : "+ list.size());
		map.put("list", list);
		System.out.println("map().size() : "+ map.size());

		con.close();
			
		return map;
	}


	public void updateProduct(Product productVO) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "UPDATE product SET prod_name=?,prod_detail=?,manufacture_day=?,price=?,image_file=?,reg_date=? WHERE prod_no=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, productVO.getProdName());
		stmt.setString(2, productVO.getProdDetail());
		stmt.setString(3, productVO.getManuDate());
		stmt.setInt(4, productVO.getPrice());
		stmt.setString(5, productVO.getFileName());
		stmt.setDate(6, productVO.getRegDate());
		stmt.setInt(7, productVO.getProdNo());
//		stmt.setString(5, productVO.getPROTRANCODE());
		stmt.executeUpdate();
		
		con.close();
	}
}