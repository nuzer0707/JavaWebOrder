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

	// 根據索引取得 Order 物件 (此方法本來就沒有 try-catch)
	public Order getOrder(int index) throws IndexOutOfBoundsException {
		// 注意：如果 getOrder 可能因找不到而回傳 null 而不是拋出例外，
		// 則呼叫端需要進行 null 檢查。
		// 但基於 CopyOnWriteArrayList 的行為，它會拋出 IndexOutOfBoundsException。
		return orderDAO.getOrder(index);
	}

	// 根據訂單項目(item)新增一筆訂單
	// 注意：此方法現在可能會拋出 NoSuchElementException (如果 productDAO.getProduct 找不到商品)
	public OrderDTO addOrder(String item) throws NoSuchElementException {
		Order order = new Order();
		order.setItem(item);
		// 如果 productDAO.getProduct 找不到商品，此處會拋出例外
		order.setPrice(productDAO.getProduct(item).getPrice());
		orderDAO.save(order);

		// 只有在沒有例外發生時，才會執行到這裡
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
	public OrderDTO removeOrder(String index) throws NumberFormatException, IndexOutOfBoundsException {
		// Integer.parseInt 可能拋出 NumberFormatException
		int idx = Integer.parseInt(index);
		// removeOrder(int) 可能拋出 IndexOutOfBoundsException
		return removeOrder(idx);
	}


	// 刪除一筆訂單 根據 index (整數版本)
	// 注意：此方法現在可能拋出 IndexOutOfBoundsException
	public OrderDTO removeOrder(int index) throws IndexOutOfBoundsException {
		// orderDAO.remove 可能拋出 IndexOutOfBoundsException
		orderDAO.remove(index);
		// 只有在沒有例外發生時，才會執行到這裡
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setMessage("index= "+ index +" 資料刪除成功");
		return orderDTO;
	}

	// 修改單筆
	// 注意：此方法現在可能拋出 NoSuchElementException 或 IndexOutOfBoundsException
	public OrderDTO updateOrder(int index, String newItem) throws NoSuchElementException, IndexOutOfBoundsException {
		// 1. 取得新商品的價格 (可能拋出 NoSuchElementException)
		Integer newPrice = productDAO.getProduct(newItem).getPrice();

		// 2. 建立新的 Order 物件
		Order updatedOrder = new Order(newItem, newPrice);

		// 3. 在 DAO 中執行更新 (可能拋出 IndexOutOfBoundsException)
		orderDAO.update(index, updatedOrder);

		// 4. 建立成功回應的 DTO (只有在沒有例外發生時執行)
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setMessage("訂單 #" + index + " 已成功修改為: " + updatedOrder.getItem() + " 價格:" + updatedOrder.getPrice() + "元");
		return orderDTO;
	}
}