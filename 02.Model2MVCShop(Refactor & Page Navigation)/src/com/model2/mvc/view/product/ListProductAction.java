package com.model2.mvc.view.product;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;


public class ListProductAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		Search searchVO=new Search();
		String menu = request.getParameter("menu"); 
		System.out.println("ListproductAction 20Line::"+searchVO);
		System.out.println("ListproductAction ::"+menu);
		
		int currentPage=1;
		if(request.getParameter("currentPage") != null && request.getParameter("currentPage") != "") {
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
			
		}
		
		searchVO.setCurrentPage(currentPage);	
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		
		int pageSize = Integer.parseInt( getServletContext().getInitParameter("pageSize"));
		int pageUnit  =  Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
		searchVO.setPageSize(pageSize);
		
		
		ProductService service=new ProductServiceImpl();
		HashMap<String,Object> map=service.getProductList(searchVO);
		
		Page resultPage	= 
				new Page( currentPage, ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println("ListUserAction ::"+resultPage);
		

		request.setAttribute("map", map);
		request.setAttribute("searchVO", searchVO);
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("menu", menu);
	
		//여기다가 if문 하기
		
		return "forward:/product/listProduct.jsp";
	}

}
