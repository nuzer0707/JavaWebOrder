<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>修改訂單 #${ index }</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/JavaWebOrder/css/buttons.css">
		<style type="text/css">
			body {padding: 20px;font-size: 24px;}
			select {width: 300px;}
		</style>
	</head>
	<body>

		<form class="pure-form" method="post" action="/JavaWebOrder/order/update">
			<fieldset>
				<legend>修改訂單: ${ index }</legend>
				Index:
				<%-- ================================================== --%>
				<%-- == 修改：將 index 改為隱藏欄位，並用 span 顯示 == --%>
				<%-- ================================================== --%>
				<input type="hidden" name="index" value="${ index }" >
				<span class="pure-form-message-inline">訂單編號: ${ index }</span>
				<p />
				品名:
				<%-- ================================================== --%>
				<%-- == 修改：重寫 <select> 部分以動態產生選項並預選 == --%>
				<%-- ================================================== --%>
				<select name="item">
				    <%-- 檢查商品列表是否有效 --%>
                   <!-- <c:if test="${ not empty allProducts }"></c:if>-->
                        <%-- 迭代所有可用商品 --%>
                        <c:forEach var="product" items="${ allProducts }">
                            <%-- 為每個商品建立選項 --%>
                            <%-- 關鍵：如果商品名稱與 currentItem 相符，則添加 'selected' 屬性 --%>
                            <option value="${ product.item }" <c:if test="${ product.item == currentItem }">selected</c:if>>
                                <%-- 顯示商品名稱和價格 --%>
                                ${ product.item } (${ product.price }元)
                            </option>
                        </c:forEach>
                    
                    <%-- 處理商品列表為空的情況 --%>
                   <!-- <c:if test="${ empty allProducts }">
                        <option value="" disabled>-- 無可用商品 --</option>
                    </c:if>-->
				</select>
				<%-- ================================================== --%>
				<p />
				<button type="submit" class="button-success pure-button">確認修改</button>
				<%-- ================================================== --%>
				<%-- == 新增：取消按鈕，連結回訂單列表 == --%>
				<%-- ================================================== --%>
                <a href="/JavaWebOrder/order" class="pure-button">取消</a>
			</fieldset>
		</form>
	</body>
</html>