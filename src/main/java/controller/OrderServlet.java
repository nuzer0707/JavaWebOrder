package controller;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.OrderService;
import model.dto.OrderDTO;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
	
	private OrderService orderService = new OrderService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List(OrderDTO) orderDTOs = orderService.getOrderHistory();
		
		RequestDispatcher rd= req.getRequestDispatcher("/WEB-INF/history.jsp");
		req.setAttribute("orderDTOs", orderDTOs);
		rd.forward(req, resp);
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
		
	}

	
}
