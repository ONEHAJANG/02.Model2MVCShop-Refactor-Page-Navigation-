package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.domain.Product;


public class UpdateProductViewAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
				
		int prodno = Integer.parseInt(request.getParameter("ProdNo"));
		
		ProductService service=new ProductServiceImpl();
		
		
		Product vo = service.getProduct(prodno);
		System.out.println("/getproductAction.do :" +vo);
		
		request.setAttribute("vo", vo);
		
		return "forward:/product/updateProductView.jsp";
	}
}