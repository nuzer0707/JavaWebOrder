<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/JavaWebOrder/css/buttons.css">
		<style type="text/css">
		body{padding: 20px;font-size: 24px;}
		select{width: 300px;}
		</style>
	</head>
	<body>
		<form class="pure-form pure-form-aligned" method="post" action="/JavaWebOrder/order">
			<fieldset>
				<legend>訂單</legend>
				<select name="item">
					<option value="牛肉麵">牛肉麵</option>
					<option value="陽春麵">陽春麵</option>
					<option value="番茄麵">番茄麵</option>
				</select>
				<p />
				<button type="submit" class="pure-button button-success" >送出訂單</button>
			</fieldset>
		</form>
		<form class="pure-form pure-form-aligned" method="get" action="/JavaWebOrder/order">
			<button type="submit" class="pure-button button-success button-secondary" >查看歷史資料</button>
		</form>
		
		
	</body>
</html>