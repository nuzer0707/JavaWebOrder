package dao;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import model.entity.Order;


public class OrderDAO {

	// 利用 List 來模擬資料庫/資料表 	
	
	private static List<Order> orders = new CopyOnWriteArrayList<>();
	
	//存入譯筆資訊
	public void save(Order order) {
		orders.add(order);
	}
	
	//取得所有歷史資訊
	public List<Order> findAll() {
		
		return orders;
	}
	//刪除一筆資訊
	public void remove(int index) {
		orders.remove(index);
	}
	
	//修改一筆資訊
	public void update(int index ,Order newOrder) {
		orders.set(index,newOrder);
		
	}
	
	//取得單筆資訊
	public Order getOrder(int index) {
		return orders.get(index);
		
	}
}
