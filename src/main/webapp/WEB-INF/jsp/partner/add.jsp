<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加客户</title>
</head>
<body>
	<form action="save" method="post">
		<table>
			<tr>
				<td>名称</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td>地址</td>
				<td><input type="text" name="address" /></td>
			</tr>
			<tr>
				<td>备注</td>
				<td><input type="text" name="descp" /></td>
			</tr>
			<tr>
				<td><input type="submit" name="提交" /></td>
			</tr>
		</table>
	</form>
</body>
</html>