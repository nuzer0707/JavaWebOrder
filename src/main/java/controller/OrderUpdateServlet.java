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
		// 不再捕捉 NumberFormatException, IndexOutOfBoundsException
		String indexStr = req.getParameter("index");
		// 如果 indexStr 為 null 或非數字，parseInt 會拋出 NumberFormatException
		int index = Integer.parseInt(indexStr);

		// 如果 index 超出範圍，orderService.getOrder 會拋出 IndexOutOfBoundsException
		Order currentOrder = orderService.getOrder(index);

		// !! 重要：如果 getOrder 在找不到時回傳 null 而不是拋出例外，這裡需要檢查 !!
		// if (currentOrder == null) {
		//     // 處理找不到訂單的情況，例如拋出例外或重導向
		//     resp.sendError(HttpServletResponse.SC_NOT_FOUND, "找不到訂單，索引: " + index);
		//     return;
		// }
		String currentItem = currentOrder.getItem(); // 如果 currentOrder 是 null，這裡會拋 NullPointerException

		// productService.findAll() 也可能因內部錯誤拋出例外
		List<ProductDTO> allProducts = productService.findAll();

		// --- 只有在前面沒有拋出例外時，才會執行到這裡 ---
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

		// 不再捕捉 NumberFormatException
		// 如果 indexStr 為 null 或非數字，parseInt 會拋出 NumberFormatException
		int index = Integer.parseInt(indexStr);

		OrderDTO orderDTO = null;
		// 不再捕捉來自 orderService 的 NoSuchElementException 或 IndexOutOfBoundsException
		// 如果 updateOrder 拋出例外，它會傳播到容器
		orderDTO = orderService.updateOrder(index, item);

		// --- 只有在 updateOrder 沒有拋出例外時，才會執行到這裡 ---
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/result.jsp");
		req.setAttribute("orderDTO", orderDTO);
		rd.forward(req, resp);
	}
}