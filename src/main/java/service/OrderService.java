package service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException; // 仍然可能被 productDAO 拋出

import dao.OrderDAO;
import dao.ProductDAO;
import model.dto.OrderDTO;
import model.entity.Order;

public class OrderService {
	private OrderDAO orderDAO = new OrderDAO();
	private ProductDAO productDAO = new ProductDAO();


	public Order getOrder(int index) {

		return orderDAO.getOrder(index);
	}

	// 根據訂單項目(item)新增一筆訂單
	
	public OrderDTO addOrder(String item){
		Order order = new Order();
		order.setItem(item);
		
		order.setPrice(productDAO.getProduct(item).getPrice());
		orderDAO.save(order);

		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setMessage("您點了 " + order.getItem() + " 價格:" + order.getPrice() + "元");
		
		return orderDTO;
	}

	// 取得歷史資料 (邏輯不變)
	public List<OrderDTO> getOrderHistory() {
		List<Order> orders = orderDAO.findAll();
		List<OrderDTO> orderDTOs = new ArrayList<>();
		for(Order order : orders) {
			OrderDTO dto = new OrderDTO();
			dto.setMessage("您點了 " + order.getItem() + " 價格:" + order.getPrice() + "元");
			orderDTOs.add(dto);
		}
		return orderDTOs;
	}

	// 刪除一筆訂單 根據 index (字串版本)
	// 注意：此方法現在可能拋出 NumberFormatException
	public OrderDTO removeOrder(String index){

		int idx = Integer.parseInt(index);
		return removeOrder(idx);
	}


	// 刪除一筆訂單 根據 index (整數版本)

	public OrderDTO removeOrder(int index){
		
		orderDAO.remove(index);
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setMessage("index= "+ index +" 資料刪除成功");
		return orderDTO;
	}

	// 修改單筆
	
	public OrderDTO updateOrder(int index, String newItem){
		// 1. 取得新商品的價格 
		Integer newPrice = productDAO.getProduct(newItem).getPrice();
		// 2. 建立新的 Order 物件
		Order updatedOrder = new Order(newItem, newPrice);
		// 3. 在 DAO 中執行更新 
		orderDAO.update(index, updatedOrder);
		// 4. 建立成功回應的 DTO 
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setMessage("訂單 #" + index + " 已成功修改為: " + updatedOrder.getItem() + " 價格:" + updatedOrder.getPrice() + "元");
		return orderDTO;
	}
}