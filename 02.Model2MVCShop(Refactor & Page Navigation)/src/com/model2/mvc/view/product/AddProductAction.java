package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.domain.Product;

public class AddProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Product vo=new Product();
//		vo.setProdNo(request.getParameter("prodNo"));
		vo.setProdName(request.getParameter("prodName"));
		vo.setProdDetail(request.getParameter("prodDetail"));
		vo.setManuDate(request.getParameter("ManuDate"));
		vo.setPrice(Integer.parseInt(request.getParameter("price")));
		vo.setFileName(request.getParameter("fileName"));
		
//		vo.setPhone(request.getParameter("proTranCode"));
		
		System.out.println(vo);
		
		ProductService service=new ProductServiceImpl();
		service.addProduct(vo);
		
		request.setAttribute("vo", vo);
		
		return "forward:/product/productView.jsp";
		
	}

}
