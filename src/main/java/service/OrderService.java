package service;
import java.util.ArrayList;
import java.util.List;
import dao.OrderDAO;
import model.dto.OrderDTO;
import model.entity.Order;

public class OrderService {
	
	private OrderDAO orderDAO = new OrderDAO();
	
	//新增訂單項目新增一筆訂單並回傳訂單顯示資訊(OrderDTO)
	
	public OrderDTO addOrder(String item) {
		//1.
		Order order = new Order();
		order.setItem(item);
		order.setPrice(100);
		
		orderDAO.save(order);
		//2.
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setMessage("您點了"+order.getItem()+"價格:" + order.getPrice()+"元");
		return orderDTO;
	}
	public List<OrderDTO> getOrderHistory(){
		List<Order> orders = orderDAO.findAll();
		
		List<OrderDTO> orderDTOs = new ArrayList<>();
		
		for(Order order : orders) {
			OrderDTO dto = new OrderDTO();
			dto.setMessage("您點了"+order.getItem()+"價格:" + order.getPrice()+"元");
			orderDTOs.add(dto);
		}
		return orderDTOs;
	}
	
}
