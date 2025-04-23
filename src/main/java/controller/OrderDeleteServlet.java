package controller;



import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.OrderDTO;
import service.OrderService;


@WebServlet("/order/delete")
public class OrderDeleteServlet extends HttpServlet {

	private OrderService orderService = new OrderService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String index = req.getParameter("index");
		
		OrderDTO orderDTO = orderService.removeOrder(index);
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/result.jsp");
		req.setAttribute("orderDTO", orderDTO);
		rd.forward(req, resp);
		
	}
	
	
	
}
