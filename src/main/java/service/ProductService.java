package service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dao.ProductDAO;
import model.dto.ProductDTO;
import model.entity.Product;

public class ProductService {
	
	private ProductDAO productDAO  = new ProductDAO();
	
	public List<ProductDTO> findAll(){
			
		List<Product> products = productDAO.findAll();
		
		List<ProductDTO> productDTOs = new ArrayList<>();
		for(Product product: products) {
			productDTOs.add(new ProductDTO(product.getItem(),product.getPrice()));
		}
		return productDTOs;
	}
	
	public ProductDTO getProductDTO(String item) {
		Product product = productDAO.getProduct(item);
		return new ProductDTO(product.getItem(),product.getPrice());
		
	}
	
	public Integer getPrice(String message) {
		return productDAO.findAll().stream()
								   .filter(p -> message.contains(p.getItem()))
								   .findFirst()
								   .get()
								   .getPrice();
	}
	
	
}
