package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.domain.Product;


public class UpdateProductAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		String prodno=(String)request.getParameter("prodNo");
		
		
		System.out.println("이제그만해재미없어"+prodno);
		Product vo=new Product();
		vo.setProdNo(Integer.parseInt(prodno));
		vo.setProdName(request.getParameter("prodName"));
		vo.setProdDetail(request.getParameter("prodDetail"));
		vo.setManuDate(request.getParameter("manuDate"));
		vo.setPrice(Integer.parseInt(request.getParameter("price")));
		vo.setFileName(request.getParameter("fileName"));
		
//		vo.setPhone(request.getParameter("proTranCode"));
		
		ProductService service=new ProductServiceImpl();
		service.updateProduct(vo);
		
		System.out.println("혹시너냐??"+vo);
/*		HttpSession session=request.getSession();
			session.setAttribute("VO",vo);
		*/
			
		
		return "forward:/getProduct.do?prodNo="+prodno;
	}
}