package com.model2.mvc.service.product.impl;

import java.util.HashMap;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.domain.Product;

public class ProductServiceImpl implements ProductService {
	
	private ProductDAO productDAO;

	public ProductServiceImpl() {
		productDAO = new ProductDAO();
	}

	@Override
	public void addProduct(Product productVO) throws Exception {
		productDAO.insertProduct(productVO);

	}

	@Override
	public Product getProduct(int prodno) throws Exception {
		// TODO Auto-generated method stub
		return productDAO.findProduct(prodno);
	}

//	@Override
	public HashMap<String, Object> getProductList(Search searchVO) throws Exception {
		return productDAO.getProductList(searchVO);
	}
	
	public void updateProduct(Product productVO) throws Exception {
		productDAO.updateProduct(productVO);
	} 
}

