package controller;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException; // 需要處理來自 Service 的例外

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.dto.OrderDTO;
import model.dto.ProductDTO;
import model.entity.Order;
import service.OrderService;
import service.ProductService;

@WebServlet("/order/update")
public class OrderUpdateServlet extends HttpServlet{

	private OrderService orderService = new OrderService();
	private ProductService productService = new ProductService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String indexStr = req.getParameter("index");
		// 重導到指定 jsp 並帶上 index
		int index = Integer.parseInt(indexStr);

		Order currentOrder = orderService.getOrder(index);
		String currentItem = currentOrder.getItem(); 
		List<ProductDTO> allProducts = productService.findAll();

		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/update.jsp");
		req.setAttribute("index", index);
		req.setAttribute("currentItem", currentItem);
        req.setAttribute("allProducts", allProducts);
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String indexStr = req.getParameter("index");
		String item = req.getParameter("item");
		
		int index = Integer.parseInt(indexStr);

		OrderDTO orderDTO = orderService.updateOrder(index, item);
		// --- 只有在 updateOrder 沒有拋出例外時，才會執行到這裡 ---
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/result.jsp");
		req.setAttribute("orderDTO", orderDTO);
		rd.forward(req, resp);
	}
}